package com.codefair.lawfeedback.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.codefair.lawfeedback.R;
import com.codefair.lawfeedback.listener.SuccessGettingReplyInfoListener;
import com.codefair.lawfeedback.listener.SuccessGettingReplyListListener;
import com.codefair.lawfeedback.listener.SuccessWriteReplyListener;
import com.codefair.lawfeedback.model.ReplyListItem;
import com.codefair.lawfeedback.model.ReplyListViewAdapter;
import com.codefair.lawfeedback.model.WrtieReplyTO;
import com.codefair.lawfeedback.network.RetrofitManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class ReplyViewActivity extends AppCompatActivity implements SuccessWriteReplyListener, SwipeRefreshLayout.OnRefreshListener, SuccessGettingReplyListListener, SuccessGettingReplyInfoListener {

    private SwipeRefreshLayout replySwipeRefreshLayout;
    private List<ReplyListItem> replyListItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_view);

        replySwipeRefreshLayout = findViewById(R.id.replySwipeLayout);
        replySwipeRefreshLayout.setOnRefreshListener(this);

        RetrofitManager.getInstance().setOnSuccessWriteReplyListener(this);
        RetrofitManager.getInstance().setOnSuccessGettingReplyListListener(this);
        RetrofitManager.getInstance().setOnSuccessGettingReplyInfoListener(this);

        boolean isRelatedUser = getIntent().getBooleanExtra("isRelatedUser", false);
        boolean isRelatedView = getIntent().getBooleanExtra("isRelatedView", false);
        RetrofitManager.getInstance().getReplyList(getIntent().getLongExtra("articleId", 0L), isRelatedView);
        if (isRelatedView) {
            if (!isRelatedUser) {
                findViewById(R.id.writeReplyLayout).setVisibility(View.GONE);
            }
        } else {
            if (isRelatedUser) {
                findViewById(R.id.writeReplyLayout).setVisibility(View.GONE);
            }
        }

        findViewById(R.id.writeReplyBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long articleId = getIntent().getLongExtra("articleId", 0L);
                long userId = getIntent().getLongExtra("userId", 0L);
                EditText replyContent = findViewById(R.id.writeReplyContent);
                RetrofitManager.getInstance().writeReply(articleId, new WrtieReplyTO(userId, replyContent.getText().toString()));
            }
        });
    }

    @Override
    protected void onDestroy() {
        RetrofitManager.getInstance().removeSuccessGettingReplyListListener();
        RetrofitManager.getInstance().removeSuccessWriteReplyListener();
        RetrofitManager.getInstance().removeSuccessGettingReplyInfoListener();
        super.onDestroy();
    }

    @Override
    public void onSuccessWriteReply() {
        long articleId = getIntent().getLongExtra("articleId", 0L);
        boolean isRelatedView = getIntent().getBooleanExtra("isRelatedView", false);
        RetrofitManager.getInstance().getReplyList(articleId, isRelatedView);
    }

    @Override
    public void onOKResponse(Response<List<ReplyListItem>> response) {
        List<ReplyListItem> responseList = response.body();
        replyListItemList = new ArrayList<>();

        ReplyListItem maxGoodItem = null, secondMaxGoodItem = null;
        boolean maxGoodExist = false, secondGoodExist = false;
        if (responseList.size() > 0) {
            maxGoodItem = responseList.get(0);
            maxGoodExist = true;
        }
        if (responseList.size() > 1) {
            secondMaxGoodItem = responseList.get(1);
            secondGoodExist = true;
        }
        for (int i = 1; i < responseList.size(); i++) {
            ReplyListItem replyListItem = responseList.get(i);
            if (maxGoodItem.getGood() < replyListItem.getGood()) {
                secondMaxGoodItem = maxGoodItem;
                maxGoodItem = replyListItem;
            } else if (secondMaxGoodItem.getGood() < replyListItem.getGood()) {
                secondMaxGoodItem = replyListItem;
            }
        }
        if (maxGoodItem != null) {
            replyListItemList.add(maxGoodItem);
        }
        if (secondMaxGoodItem != null) {
            replyListItemList.add(secondMaxGoodItem);
        }

        replyListItemList.addAll(responseList);
        ListView replyListView = findViewById(R.id.replyListView);
        replyListView.setAdapter(new ReplyListViewAdapter(replyListItemList, maxGoodExist, secondGoodExist));
    }

    @Override
    public void onRefresh() {
        long articleId = getIntent().getLongExtra("articleId", 0L);
        boolean isRelatedView = getIntent().getBooleanExtra("isRelatedView", false);
        RetrofitManager.getInstance().getReplyList(articleId, isRelatedView);
        replySwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onSuccessGetReplyInfo(ReplyListItem replyListItem, int position, View view) {
        this.replyListItemList.get(position).setGood(replyListItem.getGood());
        this.replyListItemList.get(position).setBad(replyListItem.getBad());

        TextView goodTextView = view.findViewById(R.id.goodTextInReplyListItem);
        TextView badTextView = view.findViewById(R.id.badTextInReplyListItem);

        goodTextView.setText(String.valueOf(replyListItem.getGood()));
        badTextView.setText(String.valueOf(replyListItem.getBad()));
    }
}
