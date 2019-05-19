package com.codefair.lawfeedback.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.codefair.lawfeedback.R;
import com.codefair.lawfeedback.listener.SuccessGettingArticleInfoListener;
import com.codefair.lawfeedback.model.ArticleInfo;
import com.codefair.lawfeedback.model.RelatedJobInfo;
import com.codefair.lawfeedback.network.RetrofitManager;

import java.util.List;

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
        TextView articleViewTitle = findViewById(R.id.articleViewTitle);
        TextView articleViewContent = findViewById(R.id.articleViewContent);
        TextView articleViewAllGoodText = findViewById(R.id.articleViewAllGoodText);
        TextView articleViewAllBadText = findViewById(R.id.articleViewAllBadText);
        TextView articleViewJob1Text = findViewById(R.id.articleViewJob1Text);
        TextView articleViewJob1GoodText = findViewById(R.id.articleViewJob1GoodText);
        TextView articleViewJob1BadText = findViewById(R.id.articleViewJob1BadText);
        TextView articleViewJob2Text = findViewById(R.id.articleViewJob2Text);
        TextView articleViewJob2GoodText = findViewById(R.id.articleViewJob2GoodText);
        TextView articleViewJob2BadText = findViewById(R.id.articleViewJob2BadText);

        ArticleInfo articleInfo = response.body();
        assert articleInfo != null;
        articleViewTitle.setText(articleInfo.getTitle());
        articleViewContent.setText(articleInfo.getContent());
        articleViewAllGoodText.setText(String.valueOf(articleInfo.getGood_ex()));
        articleViewAllBadText.setText(String.valueOf(articleInfo.getBad_ex()));

        List<RelatedJobInfo> relatedJobInfoList = articleInfo.getRelatedJobInfoList();
        View articleViewJob1Layout = findViewById(R.id.articleViewJob1Layout);
        View articleViewJob2Layout = findViewById(R.id.articleViewJob2Layout);
        if (relatedJobInfoList != null && relatedJobInfoList.size() > 0) {
            articleViewJob1Layout.setVisibility(View.VISIBLE);
            articleViewJob1Text.setText(relatedJobInfoList.get(0).getJobName());
            articleViewJob1GoodText.setText(String.valueOf(relatedJobInfoList.get(0).getGood()));
            articleViewJob1BadText.setText(String.valueOf(relatedJobInfoList.get(0).getBad()));
            if (relatedJobInfoList.size() > 1) {
                articleViewJob2Layout.setVisibility(View.VISIBLE);
                articleViewJob2Text.setText(relatedJobInfoList.get(1).getJobName());
                articleViewJob2GoodText.setText(String.valueOf(relatedJobInfoList.get(1).getGood()));
                articleViewJob2BadText.setText(String.valueOf(relatedJobInfoList.get(1).getBad()));
            }
        }
    }
}
