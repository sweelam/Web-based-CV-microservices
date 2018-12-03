package com.web.utils.common.dto;

import java.sql.Date;

public class UserInfoVo {
	
    private String fullname;
    private String address;
    private String mobile;
    private String title;
    private Date age;
    private Date from;
    private Date to;
    private String jobDesc;
    
    private String email;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getAge() {
        return age;
    }

    public void setAge(Date age) {
        this.age = age;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "UserInfoVo{" +
                "fullname='" + fullname + '\'' +
                ", address='" + address + '\'' +
                ", mobile='" + mobile + '\'' +
                ", title='" + title + '\'' +
                ", age=" + age +
                ", from=" + from +
                ", to=" + to +
                ", jobDesc='" + jobDesc + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
