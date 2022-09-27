package org.kuro.recruit.model.entity;

import java.io.Serializable;

public class EduExperience implements Serializable {

    private String id;

    private String userId;

    private String edu;

    private String college;

    private String timeQuantum;

    private String major;

    private String createTime;

    private String updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEdu() {
        return edu;
    }

    public void setEdu(String edu) {
        this.edu = edu;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getTimeQuantum() {
        return timeQuantum;
    }

    public void setTimeQuantum(String timeQuantum) {
        this.timeQuantum = timeQuantum;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
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
