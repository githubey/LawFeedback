package com.codefair.lawfeedback.model;

import java.util.List;

public class WriteArticleTO {

    private Long userId;
    private String title;
    private String content;
    private List<Long> jobIdList;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Long> getJobIdList() {
        return jobIdList;
    }

    public void setJobIdList(List<Long> jobIdList) {
        this.jobIdList = jobIdList;
    }

    public WriteArticleTO(Long userId, String title, String content, List<Long> jobIdList) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.jobIdList = jobIdList;
    }
}
