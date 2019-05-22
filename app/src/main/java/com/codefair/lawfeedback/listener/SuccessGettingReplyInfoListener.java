package com.codefair.lawfeedback.listener;

import android.view.View;

import com.codefair.lawfeedback.model.ReplyListItem;

public interface SuccessGettingReplyInfoListener {
    void onSuccessGetReplyInfo(ReplyListItem replyListItem, int position, View view);
}
