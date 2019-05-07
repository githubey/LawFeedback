package com.codefair.lawfeedback.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.codefair.lawfeedback.R;
import com.codefair.lawfeedback.listener.SuccessGettingArticleListListener;
import com.codefair.lawfeedback.model.ArticleListItem;
import com.codefair.lawfeedback.network.RetrofitManager;

import java.util.List;
import java.util.logging.Logger;

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
    }

    @Override
    protected void onDestroy() {
        RetrofitManager.getInstance().removeSuccessGettingArticleListListener();
        super.onDestroy();
    }
}
