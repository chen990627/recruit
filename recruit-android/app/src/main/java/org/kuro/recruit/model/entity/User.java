package org.kuro.recruit.model.entity;

import java.io.Serializable;

public class User implements Serializable {

    private String id;

    private String mobile;

    private Integer gender;

    private String genderText;

    private String name;

    private String avatar;

    // 离职-随时到岗
    private String jobState;

    private String selfDescribe;

    private String clientId;

    private Integer state;

    private String stateText;

    private String birthday;

    private String createTime;

    private String updateTime;

    public User() {
    }

    public User(String mobile, String name, String avatar, String jobState, String selfDescribe) {
        this.mobile = mobile;
        this.name = name;
        this.avatar = avatar;
        this.jobState = jobState;
        this.selfDescribe = selfDescribe;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getGenderText() {
        return genderText;
    }

    public void setGenderText(String genderText) {
        this.genderText = genderText;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getJobState() {
        return jobState;
    }

    public void setJobState(String jobState) {
        this.jobState = jobState;
    }

    public String getSelfDescribe() {
        return selfDescribe;
    }

    public void setSelfDescribe(String selfDescribe) {
        this.selfDescribe = selfDescribe;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getStateText() {
        return stateText;
    }

    public void setStateText(String stateText) {
        this.stateText = stateText;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
