package com.codefair.lawfeedback.model;

import android.content.Context;
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

    public ReplyListViewAdapter(List<ReplyListItem> replyListItemList) {
        this.replyListItemList = replyListItemList;
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

        ReplyListItem replyListItem = replyListItemList.get(position);
        replyTextView.setText(replyListItem.getContent());
        jobTextView.setText(replyListItem.getJobName());

        return convertView;
    }
}