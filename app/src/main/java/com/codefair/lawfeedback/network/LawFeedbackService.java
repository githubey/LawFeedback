package com.codefair.lawfeedback.network;

import com.codefair.lawfeedback.model.ArticleListItem;
import com.codefair.lawfeedback.model.Job;
import com.codefair.lawfeedback.model.LoginDTO;
import com.codefair.lawfeedback.model.User;
import com.codefair.lawfeedback.model.WriteArticleTO;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

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

}
