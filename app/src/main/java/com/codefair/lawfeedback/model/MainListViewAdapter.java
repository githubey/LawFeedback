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

public class MainListViewAdapter extends BaseAdapter {

    private List<ArticleListItem> articleItemList = new ArrayList<ArticleListItem>();

    public MainListViewAdapter(List<ArticleListItem> articleItemList) {
        this.articleItemList = articleItemList;
    }

    @Override
    public int getCount() {
        return articleItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return articleItemList.get(position);
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
            convertView = inflater.inflate(R.layout.main_listview_item, parent, false);
        }

        TextView titleTextView = convertView.findViewById(R.id.titleTextViewInMainListItem);
        TextView summaryTextView = convertView.findViewById(R.id.summaryTextViewInMainListItem);

        ArticleListItem articleListItem = articleItemList.get(position);
        titleTextView.setText(articleListItem.getTitle());
        summaryTextView.setText(articleListItem.getSummary());

        return convertView;
    }

    public void addItem(Long id, Long userId, String title, String summary) {
        ArticleListItem articleListItem = new ArticleListItem();
        articleListItem.setId(id);
        articleListItem.setUserId(userId);
        articleListItem.setTitle(title);
        articleListItem.setSummary(summary);
        articleItemList.add(articleListItem);
    }
}