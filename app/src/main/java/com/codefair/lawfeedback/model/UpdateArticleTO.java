package com.codefair.lawfeedback.model;

public class UpdateArticleTO {

    private Long jobId;
    private Long good;
    private Long bad;

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Long getGood() {
        return good;
    }

    public void setGood(Long good) {
        this.good = good;
    }

    public Long getBad() {
        return bad;
    }

    public void setBad(Long bad) {
        this.bad = bad;
    }

    public UpdateArticleTO(Long jobId, Long good, Long bad) {
        this.jobId = jobId;
        this.good = good;
        this.bad = bad;
    }
}
