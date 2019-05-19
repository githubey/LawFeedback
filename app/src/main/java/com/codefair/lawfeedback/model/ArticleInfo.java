package com.codefair.lawfeedback.model;

import java.util.List;

public class ArticleInfo {

    private Long id;
    private Long user_id;
    private String title;
    private String content;
    private Integer good_ex;
    private Integer bad_ex;
    private List<RelatedJobInfo> relatedJobInfoList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
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

    public Integer getGood_ex() {
        return good_ex;
    }

    public void setGood_ex(Integer good_ex) {
        this.good_ex = good_ex;
    }

    public Integer getBad_ex() {
        return bad_ex;
    }

    public void setBad_ex(Integer bad_ex) {
        this.bad_ex = bad_ex;
    }

    public List<RelatedJobInfo> getRelatedJobInfoList() {
        return relatedJobInfoList;
    }

    public void setRelatedJobInfoList(List<RelatedJobInfo> relatedJobInfoList) {
        this.relatedJobInfoList = relatedJobInfoList;
    }
}
