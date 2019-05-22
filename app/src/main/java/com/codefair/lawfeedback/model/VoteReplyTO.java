package com.codefair.lawfeedback.model;

public class VoteReplyTO {
    private Integer good;
    private Integer bad;

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

    public VoteReplyTO(Integer good, Integer bad) {
        this.good = good;
        this.bad = bad;
    }
}
