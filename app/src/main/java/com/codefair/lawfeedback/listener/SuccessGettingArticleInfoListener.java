package com.codefair.lawfeedback.listener;

import com.codefair.lawfeedback.model.ArticleInfo;

import retrofit2.Response;

public interface SuccessGettingArticleInfoListener {
    void onOKResponse(Response<ArticleInfo> response);
}
