package com.codefair.lawfeedback.model;

public class UpdateArticleTO {

    private Long jobId;
    private Integer good;
    private Integer bad;

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Integer getGood() {
        return good;
    }

    public void setGood(Integer good) {
        this.good = good;
    }

    public Integer getBad() {
        return bad;
    }

    public void setBad(Integer bad) {
        this.bad = bad;
    }

    public UpdateArticleTO(Long jobId, Integer good, Integer bad) {
        this.jobId = jobId;
        this.good = good;
        this.bad = bad;
    }
}
