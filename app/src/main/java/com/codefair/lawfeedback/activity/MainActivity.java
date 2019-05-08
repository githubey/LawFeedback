package com.codefair.lawfeedback.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.codefair.lawfeedback.R;
import com.codefair.lawfeedback.listener.SuccessGettingArticleListListener;
import com.codefair.lawfeedback.model.ArticleListItem;
import com.codefair.lawfeedback.model.MainListViewAdapter;
import com.codefair.lawfeedback.network.RetrofitManager;

import java.util.List;

import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SuccessGettingArticleListListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RetrofitManager.getInstance().setOnSuccessGettingArticleListListener(this);
        RetrofitManager.getInstance().getArticleList();
    }

    @Override
    public void onOKResponse(Response<List<ArticleListItem>> response) {
        List<ArticleListItem> articleItemList = response.body();
        ListView mainListView = findViewById(R.id.mainListView);
        mainListView.setAdapter(new MainListViewAdapter(articleItemList));
    }

    @Override
    protected void onDestroy() {
        RetrofitManager.getInstance().removeSuccessGettingArticleListListener();
        super.onDestroy();
    }
}
