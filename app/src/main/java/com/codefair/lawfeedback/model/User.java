package com.codefair.lawfeedback.model;

public class User {

    private String email;
    private String password;
    private String name;
    private String nickname;
    private Long jobId;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public static User createLawmakerUserModel(String email, String password, String name, Long jobId) {
        User user = new User();

        user.email = email;
        user.password = password;
        user.name = name;
        user.jobId = jobId;

        return user;
    }

    public static User createNormalUserModel(String email, String password, String nickname, Long jobId) {
        User user = new User();

        user.email = email;
        user.password = password;
        user.nickname = nickname;
        user.jobId = jobId;

        return user;
    }

    @Override
    public String toString() {
        return String.format("email[%s], password[%s], name[%s], nickname[%s], jobId[%d]", email, password, name, nickname, jobId);
    }
}
