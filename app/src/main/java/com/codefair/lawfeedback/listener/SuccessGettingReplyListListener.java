package com.codefair.lawfeedback.listener;

import com.codefair.lawfeedback.model.ReplyListItem;

import java.util.List;

import retrofit2.Response;

public interface SuccessGettingReplyListListener {
    void onOKResponse(Response<List<ReplyListItem>> response);
}
