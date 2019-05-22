package com.codefair.lawfeedback.model;

public class WrtieReplyTO {

    private Long userId;
    private String replyContent;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public WrtieReplyTO(Long userId, String replyContent) {
        this.userId = userId;
        this.replyContent = replyContent;
    }
}
