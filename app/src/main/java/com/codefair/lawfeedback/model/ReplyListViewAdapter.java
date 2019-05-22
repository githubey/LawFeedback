package com.codefair.lawfeedback.model;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.codefair.lawfeedback.R;

import java.util.ArrayList;
import java.util.List;

public class ReplyListViewAdapter extends BaseAdapter {

    private List<ReplyListItem> replyListItemList = new ArrayList<>();
    private boolean maxGoodExist;
    private boolean secondGoodExist;

    public ReplyListViewAdapter(List<ReplyListItem> replyListItemList, boolean maxGoodExist, boolean secondGoodExist) {
        this.replyListItemList = replyListItemList;
        this.maxGoodExist = maxGoodExist;
        this.secondGoodExist = secondGoodExist;
    }

    @Override
    public int getCount() {
        return replyListItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return replyListItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.reply_listview_item, parent, false);
        }

        TextView replyTextView = convertView.findViewById(R.id.replyTextViewInReplyListItem);
        TextView jobTextView = convertView.findViewById(R.id.jobTextViewInReplyListItem);
        TextView goodTextView = convertView.findViewById(R.id.goodTextInReplyListItem);
        TextView badTextView = convertView.findViewById(R.id.badTextInReplyListItem);

        final ReplyListItem replyListItem = replyListItemList.get(position);
        replyTextView.setText(replyListItem.getContent());
        jobTextView.setText(replyListItem.getJobName());
        goodTextView.setText(String.valueOf(replyListItem.getGood()));
        badTextView.setText(String.valueOf(replyListItem.getBad()));

        goodTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                Log.i("ReplyListViewAdapter", "replyId: " + replyListItem.getId());
                Log.i("ReplyListViewAdapter", "articleId: " + replyListItem.getArticleId());
                Log.i("ReplyListViewAdapter", "good: " + replyListItem.getGood());
                Log.i("ReplyListViewAdapter", "bad: " + replyListItem.getBad());
            }
        });

        badTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });

        View maxGoodReply = convertView.findViewById(R.id.starReplyInReplyListItem);
        if ((maxGoodExist && position == 0) || (secondGoodExist && position == 1)) {
            maxGoodReply.setVisibility(View.VISIBLE);
        } else {
            maxGoodReply.setVisibility(View.INVISIBLE);
        }

        return convertView;
    }
}
