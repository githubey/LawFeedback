package com.codefair.lawfeedback.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.codefair.lawfeedback.R;
import com.codefair.lawfeedback.listener.SuccessGettingArticleListListener;
import com.codefair.lawfeedback.listener.SuccessWriteArticleListener;
import com.codefair.lawfeedback.model.ArticleListItem;
import com.codefair.lawfeedback.model.MainListViewAdapter;
import com.codefair.lawfeedback.network.RetrofitManager;

import java.util.List;

import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SuccessGettingArticleListListener, SwipeRefreshLayout.OnRefreshListener, MainListViewAdapter.ListImageClickListener, SuccessWriteArticleListener {

    private SwipeRefreshLayout mainSwipeLayout;
    private List<ArticleListItem> articleItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RetrofitManager.getInstance().setOnSuccessGettingArticleListListener(this);
        RetrofitManager.getInstance().getArticleList();

        mainSwipeLayout = findViewById(R.id.mainSwipeLayout);
        mainSwipeLayout.setOnRefreshListener(this);

        FloatingActionButton mainFloatingBtn = findViewById(R.id.mainFloatingBtn);
        if (!getIntent().getStringExtra("userName").equals("")) {
            mainFloatingBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, WriteArticleActivity.class);
                    intent.putExtra("userId", getIntent().getLongExtra("userId", 0L));
                    startActivity(intent);
                }
            });
        } else {
            FrameLayout layout = findViewById(R.id.activity_main);
            layout.findViewById(R.id.mainFloatingBtn).setVisibility(View.GONE);
        }

        RetrofitManager.getInstance().addOnSuccessWriteArticleListener(this);
    }

    @Override
    public void onOKResponse(Response<List<ArticleListItem>> response) {
        articleItemList = response.body();
        ListView mainListView = findViewById(R.id.mainListView);
        mainListView.setAdapter(new MainListViewAdapter(articleItemList, this));
        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO
            }
        });
    }

    @Override
    protected void onDestroy() {
        RetrofitManager.getInstance().removeSuccessGettingArticleListListener();
        RetrofitManager.getInstance().removeSuccessWriteArticleListener(this);
        super.onDestroy();
    }

    @Override
    public void onRefresh() {
        RetrofitManager.getInstance().getArticleList();
        mainSwipeLayout.setRefreshing(false);
    }

    @Override
    public void onListImageClick(int position, boolean isRelated) {
        //TODO
    }

    @Override
    public void onSuccessWriteArticle() {
        RetrofitManager.getInstance().getArticleList();
    }
}
