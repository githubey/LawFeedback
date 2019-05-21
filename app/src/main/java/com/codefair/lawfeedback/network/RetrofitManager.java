package com.codefair.lawfeedback.network;

import android.util.Log;
import android.widget.Toast;

import com.codefair.lawfeedback.GlobalApplication;
import com.codefair.lawfeedback.R;
import com.codefair.lawfeedback.listener.SuccessGettingArticleInfoListener;
import com.codefair.lawfeedback.listener.SuccessGettingArticleListListener;
import com.codefair.lawfeedback.listener.SuccessGettingJobListListener;
import com.codefair.lawfeedback.listener.SuccessGettingReplyListListener;
import com.codefair.lawfeedback.listener.SuccessLoginListener;
import com.codefair.lawfeedback.listener.SuccessRegisterationLawmakerListListener;
import com.codefair.lawfeedback.listener.SuccessRegisterationNormalListener;
import com.codefair.lawfeedback.listener.SuccessWriteArticleListener;
import com.codefair.lawfeedback.listener.SuccessWriteReplyListener;
import com.codefair.lawfeedback.model.ArticleInfo;
import com.codefair.lawfeedback.model.ArticleListItem;
import com.codefair.lawfeedback.model.Job;
import com.codefair.lawfeedback.model.LoginDTO;
import com.codefair.lawfeedback.model.ReplyListItem;
import com.codefair.lawfeedback.model.UpdateArticleTO;
import com.codefair.lawfeedback.model.User;
import com.codefair.lawfeedback.model.WriteArticleTO;
import com.codefair.lawfeedback.model.WrtieReplyTO;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    private static String TAG = "Retrofit";
    //    final private String requestURL = "http://ec2-13-125-234-29.ap-northeast-2.compute.amazonaws.com:8080";
    final private String requestURL = "http://10.0.2.2:8080";
    private static RetrofitManager retrofitManager;
    private Retrofit retrofit;
    private LawFeedbackService service;
    private SuccessGettingJobListListener mSuccessGettingJobListListener;
    private SuccessRegisterationLawmakerListListener mSuccessRegisterationLawmakerListener;
    private SuccessRegisterationNormalListener mSuccessRegisterationNormalListener;
    private SuccessLoginListener mSuccessLoginListener;
    private SuccessGettingArticleListListener mSuccessGettingArticleListListener;
    private List<SuccessWriteArticleListener> mSuccessWriteArticleListenerList = new ArrayList<>();
    private SuccessGettingArticleInfoListener mSuccessGettingArticleInfoListener;
    private SuccessWriteReplyListener mSuccessWriteReplyListener;
    private SuccessGettingReplyListListener mSuccessGettingReplyListListener;

    private RetrofitManager() {
        retrofit = new Retrofit.Builder().baseUrl(requestURL).addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create())).build();
        service = retrofit.create(LawFeedbackService.class);
    }

    public static RetrofitManager getInstance() {
        if (retrofitManager == null) retrofitManager = new RetrofitManager();
//        userId = GlobalApplication.getGlobalContext().getSharedPreferences("user", MODE_PRIVATE).getLong("userId", -1);
        return retrofitManager;
    }

    public void setOnSuccessGettingJobListListener(SuccessGettingJobListListener mSuccessGettingJobListListener) {
        this.mSuccessGettingJobListListener = mSuccessGettingJobListListener;
    }

    public void setOnSuccessRegisterationLawmakerListener(SuccessRegisterationLawmakerListListener mSuccessRegisterationLawmakerListener) {
        this.mSuccessRegisterationLawmakerListener = mSuccessRegisterationLawmakerListener;
    }

    public void setOnSuccessRegisterationNormalListener(SuccessRegisterationNormalListener mSuccessRegisstrationNormalListener) {
        this.mSuccessRegisterationNormalListener = mSuccessRegisstrationNormalListener;
    }

    public void setOnSuccessLoginListener(SuccessLoginListener mSuccessLoginListener) {
        this.mSuccessLoginListener = mSuccessLoginListener;
    }

    public void setOnSuccessGettingArticleListListener(SuccessGettingArticleListListener mSuccessGettingArticleListListener) {
        this.mSuccessGettingArticleListListener = mSuccessGettingArticleListListener;
    }

    public void addOnSuccessWriteArticleListener(SuccessWriteArticleListener mSuccessWriteArticleListener) {
        this.mSuccessWriteArticleListenerList.add(mSuccessWriteArticleListener);
    }

    public void setOnSuccessGettingArticleInfoListener(SuccessGettingArticleInfoListener mSuccessGettingArticleInfoListener) {
        this.mSuccessGettingArticleInfoListener = mSuccessGettingArticleInfoListener;
    }

    public void setOnSuccessWriteReplyListener(SuccessWriteReplyListener mSuccessWriteReplyListener) {
        this.mSuccessWriteReplyListener = mSuccessWriteReplyListener;
    }

    public void setOnSuccessGettingReplyListListener(SuccessGettingReplyListListener mSuccessGettingReplyListListener) {
        this.mSuccessGettingReplyListListener = mSuccessGettingReplyListListener;
    }

    public void removeSuccessGettingJobListListener() {
        this.mSuccessGettingJobListListener = null;
    }

    public void removeSuccessRegisterationLawmakerListener() {
        this.mSuccessRegisterationLawmakerListener = null;
    }

    public void removeSuccessRestrationNormalListener() {
        this.mSuccessRegisterationNormalListener = null;
    }

    public void removeSuccessGettingArticleListListener() {
        this.mSuccessGettingArticleListListener = null;
    }

    public void removeSuccessWriteArticleListener(SuccessWriteArticleListener mSuccessWriteArticleListener) {
        this.mSuccessWriteArticleListenerList.remove(mSuccessWriteArticleListener);
    }

    public void removeSuccessGettingArticleInfoListener() {
        this.mSuccessGettingArticleInfoListener = null;
    }

    public void removeSuccessGettingReplyListListener() {
        this.mSuccessGettingReplyListListener = null;
    }

    public void removeSuccessWriteReplyListener() {
        this.mSuccessWriteReplyListener = null;
    }

    public void removeSuccessLoginListener() {
        this.mSuccessLoginListener = null;
    }

    private void logForErrorResponse(int errorCode, String errorMessage, String methodName) {
        Log.e(TAG, methodName + " Error Code: " + errorCode);
        Log.e(TAG, methodName + ": " + errorMessage);
    }

    private void logForFailureConnection(String errorMessage, String methodName) {
        Toast.makeText(GlobalApplication.getGlobalContext(), R.string.error_message_for_network_unavailable, Toast.LENGTH_LONG).show();
        Log.e(TAG, methodName + ": " + errorMessage);
    }

    public void getJobList() {
        final String methodName = "getJobList";
        Call<List<Job>> req = service.getJobList();
        req.enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                if (response.isSuccessful()) {
                    if (mSuccessGettingJobListListener != null) {
                        Log.i(TAG, methodName + ": okResponse");
                        mSuccessGettingJobListListener.onOKResponse(response);
                    }
                } else {
                    logForErrorResponse(response.code(), response.errorBody().toString(), methodName);
                }
            }

            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
                String errorMessage = t.getMessage();
                logForFailureConnection(errorMessage, methodName);
            }
        });
    }

    public void registerLawmaker(User user) {
        final String methodName = "registerLawmaker";
        Call<JsonObject> req = service.registerLawmaker(user);
        req.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    if (mSuccessRegisterationLawmakerListener != null) {
                        mSuccessRegisterationLawmakerListener.onSuccessRegister();
                    }
                } else {
                    logForErrorResponse(response.code(), response.errorBody().toString(), methodName);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                logForFailureConnection(t.getMessage(), methodName);
            }
        });
    }

    public void registerNormal(User user) {
        final String methodName = "registerNormal";
        Call<JsonObject> req = service.registerNormal(user);
        req.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    if (mSuccessRegisterationNormalListener != null) {
                        mSuccessRegisterationNormalListener.onSuccessRegister();
                    }
                } else {
                    logForErrorResponse(response.code(), response.errorBody().toString(), methodName);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                logForFailureConnection(t.getMessage(), methodName);
            }
        });
    }

    public void login(LoginDTO loginDTO) {
        final String methodName = "login";
        Call<JsonObject> req = service.login(loginDTO);
        req.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(GlobalApplication.getGlobalContext(), R.string.login_success_message, Toast.LENGTH_LONG).show();
                    Long userId = response.body().get("userId").getAsLong();
                    Long jobId = response.body().get("jobId").getAsLong();
                    String name = response.body().get("name").getAsString();
                    Log.i(TAG, methodName + ": loginSuccess, user_id = " + userId);
                    if (mSuccessLoginListener != null) {
                        mSuccessLoginListener.onSuccessLogin(userId, jobId, name);
                    }
                } else {
                    Toast.makeText(GlobalApplication.getGlobalContext(), R.string.login_fail_message, Toast.LENGTH_LONG).show();
                    logForErrorResponse(response.code(), response.errorBody().toString(), methodName);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                logForFailureConnection(t.getMessage(), methodName);
            }
        });
    }

    public void getArticleList() {
        final String methodName = "getArticleList";
        Call<List<ArticleListItem>> req = service.getArticleList();
        req.enqueue(new Callback<List<ArticleListItem>>() {
            @Override
            public void onResponse(Call<List<ArticleListItem>> call, Response<List<ArticleListItem>> response) {
                if (response.isSuccessful()) {
                    if (mSuccessGettingArticleListListener != null) {
                        Log.i(TAG, methodName + ": okResponse");
                        mSuccessGettingArticleListListener.onOKResponse(response);
                    }
                } else {
                    logForErrorResponse(response.code(), response.errorBody().toString(), methodName);
                }
            }

            @Override
            public void onFailure(Call<List<ArticleListItem>> call, Throwable t) {
                String errorMessage = t.getMessage();
                logForFailureConnection(errorMessage, methodName);
            }
        });
    }

    public void writeArticle(WriteArticleTO writeArticleTO) {
        final String methodName = "writeArticle";
        Call<JsonObject> req = service.writeArticle(writeArticleTO);
        req.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(GlobalApplication.getGlobalContext(), R.string.write_article_success_message, Toast.LENGTH_LONG).show();
                    if (mSuccessWriteArticleListenerList.size() > 0) {
                        for (SuccessWriteArticleListener mSuccessWriteArticleListener : mSuccessWriteArticleListenerList) {
                            mSuccessWriteArticleListener.onSuccessWriteArticle();
                        }
                    }
                } else {
                    Toast.makeText(GlobalApplication.getGlobalContext(), R.string.write_article_fail_message, Toast.LENGTH_LONG).show();
                    logForErrorResponse(response.code(), response.errorBody().toString(), methodName);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                logForFailureConnection(t.getMessage(), methodName);
            }
        });
    }

    public void getArticleInfo(Long articleId) {
        final String methodName = "getArticleInfo";
        Call<ArticleInfo> req = service.getArticleInfo(articleId);
        req.enqueue(new Callback<ArticleInfo>() {
            @Override
            public void onResponse(Call<ArticleInfo> call, Response<ArticleInfo> response) {
                if (response.isSuccessful()) {
                    if (mSuccessGettingArticleInfoListener != null) {
                        Log.i(TAG, methodName + ": okResponse");
                        mSuccessGettingArticleInfoListener.onOKResponse(response);
                    }
                } else {
                    logForErrorResponse(response.code(), response.errorBody().toString(), methodName);
                }
            }

            @Override
            public void onFailure(Call<ArticleInfo> call, Throwable t) {
                String errorMessage = t.getMessage();
                logForFailureConnection(errorMessage, methodName);
            }
        });
    }

    public void updateArticle(Long articleId, UpdateArticleTO updateArticleTO) {
        final String methodName = "updateArticle";
        Call<ArticleInfo> req = service.updateArticle(articleId, updateArticleTO);
        req.enqueue(new Callback<ArticleInfo>() {
            @Override
            public void onResponse(Call<ArticleInfo> call, Response<ArticleInfo> response) {
                if (response.isSuccessful()) {
                    if (mSuccessGettingArticleInfoListener != null) {
                        Log.i(TAG, methodName + ": okResponse");
                        mSuccessGettingArticleInfoListener.onOKResponse(response);
                    }
                } else {
                    logForErrorResponse(response.code(), response.errorBody().toString(), methodName);
                }
            }

            @Override
            public void onFailure(Call<ArticleInfo> call, Throwable t) {
                String errorMessage = t.getMessage();
                logForFailureConnection(errorMessage, methodName);
            }
        });
    }

    public void writeReply(Long articleId, WrtieReplyTO wrtieReplyTO) {
        final String methodName = "writeReply";
        Call<JsonObject> req = service.writeReply(articleId, wrtieReplyTO);
        req.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(GlobalApplication.getGlobalContext(), R.string.write_reply_success_message, Toast.LENGTH_LONG).show();
                    if (mSuccessWriteReplyListener != null) {
                        mSuccessWriteReplyListener.onSuccessWriteReply();
                    }
                } else {
                    Toast.makeText(GlobalApplication.getGlobalContext(), R.string.write_reply_fail_message, Toast.LENGTH_LONG).show();
                    logForErrorResponse(response.code(), response.errorBody().toString(), methodName);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                logForFailureConnection(t.getMessage(), methodName);
            }
        });
    }

    public void getReplyList(long articleId, boolean isRelatedView) {
        final String methodName = "getReplyList";
        Call<List<ReplyListItem>> req = service.getReplyList(articleId, isRelatedView);
        req.enqueue(new Callback<List<ReplyListItem>>() {
            @Override
            public void onResponse(Call<List<ReplyListItem>> call, Response<List<ReplyListItem>> response) {
                if (response.isSuccessful()) {
                    if (mSuccessGettingReplyListListener != null) {
                        Log.i(TAG, methodName + ": okResponse");
                        mSuccessGettingReplyListListener.onOKResponse(response);
                    }
                } else {
                    logForErrorResponse(response.code(), response.errorBody().toString(), methodName);
                }
            }

            @Override
            public void onFailure(Call<List<ReplyListItem>> call, Throwable t) {
                String errorMessage = t.getMessage();
                logForFailureConnection(errorMessage, methodName);
            }
        });
    }
}
