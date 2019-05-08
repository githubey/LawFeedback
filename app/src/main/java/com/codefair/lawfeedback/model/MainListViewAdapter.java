package com.codefair.lawfeedback.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codefair.lawfeedback.R;

import java.util.ArrayList;
import java.util.List;

public class MainListViewAdapter extends BaseAdapter {

    public interface ListImageClickListener {
        void onListImageClick(int position, boolean isRelated);
    }

    private List<ArticleListItem> articleItemList = new ArrayList<ArticleListItem>();
    private ListImageClickListener listImageClickListener;

    public MainListViewAdapter(List<ArticleListItem> articleItemList, ListImageClickListener listImageClickListener) {
        this.articleItemList = articleItemList;
        this.listImageClickListener = listImageClickListener;
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

        ImageView relatedReplyImageView = convertView.findViewById(R.id.relatedReplyImageInMainListItem);
        relatedReplyImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listImageClickListener != null) {
                    listImageClickListener.onListImageClick(pos, true);
                }
            }
        });
        ImageView allReplyImageView = convertView.findViewById(R.id.allReplyImageInMainListItem);
        allReplyImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listImageClickListener != null) {
                    listImageClickListener.onListImageClick(pos, false);
                }
            }
        });

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