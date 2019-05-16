package com.codefair.lawfeedback.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.codefair.lawfeedback.R;
import com.codefair.lawfeedback.listener.SuccessGettingArticleInfoListener;
import com.codefair.lawfeedback.model.ArticleInfo;
import com.codefair.lawfeedback.network.RetrofitManager;

import retrofit2.Response;

public class ArticleViewActivity extends AppCompatActivity implements SuccessGettingArticleInfoListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_view);

        RetrofitManager.getInstance().setOnSuccessGettingArticleInfoListener(this);
        RetrofitManager.getInstance().getArticleInfo(getIntent().getLongExtra("articleId", 0L));
    }

    @Override
    protected void onDestroy() {
        RetrofitManager.getInstance().removeSuccessGettingArticleInfoListener();
        super.onDestroy();
    }

    @Override
    public void onOKResponse(Response<ArticleInfo> response) {
        //TODO
    }
}
