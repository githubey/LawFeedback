package com.codefair.lawfeedback.model;

import java.util.Set;

public class ArticleListItem {

    private Long id;
    private Long userId;
    private String title;
    private String summary;
    private Set<Long> jobIdSet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Set<Long> getJobIdSet() {
        return jobIdSet;
    }

    public void setJobIdSet(Set<Long> jobIdSet) {
        this.jobIdSet = jobIdSet;
    }
}
