package com.web.model.vo;

import java.util.List;

import com.web.model.entity.MyJobsEntity;

public class MyInfoVO {

    private int id;
    private String fullName;
    private String mobile;
    private String jobTitle;
    private String email;
    private String dateOfBirth;
    private String jobStartDate;
    private String jobEndDate;

    List<MyJobsEntity> jobList;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getJobStartDate() {
        return jobStartDate;
    }

    public void setJobStartDate(String jobStartDate) {
        this.jobStartDate = jobStartDate;
    }

    public String getJobEndDate() {
        return jobEndDate;
    }

    public void setJobEndDate(String jobEndDate) {
        this.jobEndDate = jobEndDate;
    }

    public List<MyJobsEntity> getJobList() {
        return jobList;
    }

    public void setJobList(List<MyJobsEntity> jobList) {
        this.jobList = jobList;
    }
}
