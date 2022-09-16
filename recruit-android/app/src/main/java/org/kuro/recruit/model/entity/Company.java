package org.kuro.recruit.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Company implements Serializable {

    private String companyName;
    private String intro;
    private String tag;
    private String jobs;

    public Company(String companyName, String intro, String tag, String jobs) {
        this.companyName = companyName;
        this.intro = intro;
        this.tag = tag;
        this.jobs = jobs;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getJobs() {
        return jobs;
    }

    public void setJobs(String jobs) {
        this.jobs = jobs;
    }

    public static List<Company> list () {
        List<Company> list = new ArrayList<>();

        list.add(new Company("千库科技有限公司", "北京 | 移动互联网 | 500~1000人", "五险一金", "热招：高级交互设计师 等66个岗位"));
        list.add(new Company("千库科技有限公司", "北京 | 移动互联网 | 500~1000人", "五险一金", "热招：高级交互设计师 等66个岗位"));
        list.add(new Company("千库科技有限公司", "北京 | 移动互联网 | 500~1000人", "五险一金", "热招：高级交互设计师 等66个岗位"));
        list.add(new Company("千库科技有限公司", "北京 | 移动互联网 | 500~1000人", "五险一金", "热招：高级交互设计师 等66个岗位"));
        list.add(new Company("千库科技有限公司", "北京 | 移动互联网 | 500~1000人", "五险一金", "热招：高级交互设计师 等66个岗位"));
        list.add(new Company("千库科技有限公司", "北京 | 移动互联网 | 500~1000人", "五险一金", "热招：高级交互设计师 等66个岗位"));

        return list;
    }
}
