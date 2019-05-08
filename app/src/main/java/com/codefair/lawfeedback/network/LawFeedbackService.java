package com.codefair.lawfeedback.network;

import com.codefair.lawfeedback.model.ArticleListItem;
import com.codefair.lawfeedback.model.Job;
import com.codefair.lawfeedback.model.LoginDTO;
import com.codefair.lawfeedback.model.User;
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
//
//    @POST("/users/{id}/cells")
//    Call<JsonObject> createCell(@Path("id") Long userId, @Body DiaryCell body);
//
//    @POST("/users/{id}/sync")
//    Call<List<DiaryCell>> sync(@Path("id") Long userId, @Body SyncData body);
//
//    @POST("/users/{id}/config")
//    Call<JsonObject> setConfig(@Path("id") Long userId, @Body Config body);
}
