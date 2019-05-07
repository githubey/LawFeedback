package com.codefair.lawfeedback.listener;

import com.codefair.lawfeedback.model.ArticleListItem;

import java.util.List;

import retrofit2.Response;

public interface SuccessGettingArticleListListener {
    void onOKResponse(Response<List<ArticleListItem>> response);
}
