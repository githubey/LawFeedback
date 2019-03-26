package com.codefair.lawfeedback.listener;

import com.codefair.lawfeedback.model.Job;

import java.util.List;

import retrofit2.Response;

public interface SuccessGetttingJobListListener {
    void onOKResponse(Response<List<Job>> response);
}
