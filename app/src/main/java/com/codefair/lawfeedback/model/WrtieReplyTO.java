package com.codefair.lawfeedback.model;

public class WrtieReplyTO {

    private Long articleId;
    private Long userId;
    private String replyContent;

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

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

    public WrtieReplyTO(Long articleId, Long userId, String replyContent) {
        this.articleId = articleId;
        this.userId = userId;
        this.replyContent = replyContent;
    }
}
