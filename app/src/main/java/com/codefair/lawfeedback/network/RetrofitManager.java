package com.codefair.lawfeedback.network;

import android.util.Log;
import android.widget.Toast;

import com.codefair.lawfeedback.GlobalApplication;
import com.codefair.lawfeedback.R;
import com.codefair.lawfeedback.listener.SuccessGetttingJobListListener;
import com.codefair.lawfeedback.listener.SuccessLoginListener;
import com.codefair.lawfeedback.listener.SuccessRegisterationLawmakerListListener;
import com.codefair.lawfeedback.listener.SuccessRegisterationNormalListener;
import com.codefair.lawfeedback.model.Job;
import com.codefair.lawfeedback.model.LoginDTO;
import com.codefair.lawfeedback.model.User;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

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
    private SuccessGetttingJobListListener mSuccessGetttingJobListListener;
    private SuccessRegisterationLawmakerListListener mSuccessRegisterationLawmakerListener;
    private SuccessRegisterationNormalListener mSuccessRegisterationNormalListener;
    private SuccessLoginListener mSuccessLoginListener;

    private RetrofitManager() {
        retrofit = new Retrofit.Builder().baseUrl(requestURL).addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create())).build();
        service = retrofit.create(LawFeedbackService.class);
    }

    public static RetrofitManager getInstance() {
        if (retrofitManager == null) retrofitManager = new RetrofitManager();
//        userId = GlobalApplication.getGlobalContext().getSharedPreferences("user", MODE_PRIVATE).getLong("userId", -1);
        return retrofitManager;
    }

    public void setOnSuccessGettingJobListListener(SuccessGetttingJobListListener mSuccessGetttingJobListListener) {
        this.mSuccessGetttingJobListListener = mSuccessGetttingJobListListener;
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

    public void removeSuccessGettingJobListListener() {
        this.mSuccessGetttingJobListListener = null;
    }

    public void removeSuccessRegisterationLawmakerListener() {
        this.mSuccessRegisterationLawmakerListener = null;
    }

    public void removeSuccessRestrationNormalListener() {
        this.mSuccessRegisterationNormalListener = null;
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
                    if (mSuccessGetttingJobListListener != null) {
                        Log.i(TAG, methodName + ": okResponse");
                        mSuccessGetttingJobListListener.onOKResponse(response);
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
                    Log.i(TAG, methodName + ": loginSuccess, user_id = " + userId);
                    if (mSuccessLoginListener != null) {
                        mSuccessLoginListener.onSuccessLogin(userId);
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
}
