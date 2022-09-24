package org.kuro.recruit.model.entity;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

    private String id;

    private String mobile;

    private String nickname;

    private String avatar;

    // 离职-随时到岗
    private String jobState;

    private String selfDescribe;

    private String clientId;

    private Integer state;

    private String stateText;

    private Date createTime;

    private Date updateTime;

    public User() {
    }

    public User(String mobile, String nickname, String avatar, String jobState, String selfDescribe) {
        this.mobile = mobile;
        this.nickname = nickname;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @NonNull
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", mobile='" + mobile + '\'' +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", jobState='" + jobState + '\'' +
                ", selfDescribe='" + selfDescribe + '\'' +
                ", clientId='" + clientId + '\'' +
                ", state=" + state +
                ", stateText='" + stateText + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
