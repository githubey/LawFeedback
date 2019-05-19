package com.codefair.lawfeedback.network;

import com.codefair.lawfeedback.model.ArticleInfo;
import com.codefair.lawfeedback.model.ArticleListItem;
import com.codefair.lawfeedback.model.Job;
import com.codefair.lawfeedback.model.LoginDTO;
import com.codefair.lawfeedback.model.UpdateArticleTO;
import com.codefair.lawfeedback.model.User;
import com.codefair.lawfeedback.model.WriteArticleTO;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface LawFeedbackService {
    @Headers({"Content-Type:application/json"})

    @GET("/jobs")
    Call<List<Job>> getJobList();

    @POST("/users/law_maker")
    Call<JsonObject> registerLawmaker(@Body User user);

    @POST("/users/normal")
    Call<JsonObject> registerNormal(@Body User user);

    @POST("/login")
    Call<JsonObject> login(@Body LoginDTO loginDTO);

    @GET("/articles")
    Call<List<ArticleListItem>> getArticleList();

    @POST("/articles")
    Call<JsonObject> writeArticle(@Body WriteArticleTO writeArticleTO);

    @GET("/articles/{id}")
    Call<ArticleInfo> getArticleInfo(@Path("id") Long articleId);

    @PUT("/articles/{id}")
    Call<ArticleInfo> updateArticle(@Path("id") Long articleId, @Body UpdateArticleTO updateArticleTO);
}
