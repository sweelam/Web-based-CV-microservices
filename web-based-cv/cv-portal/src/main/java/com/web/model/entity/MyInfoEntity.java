package com.web.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "my_info", schema = "sweprofile")
public class MyInfoEntity {
    private int id;
    private String fullName;
    private String mobile;
    private String email;
    private String dateOfBirth;
    private String address;
    private String userTitle;
    List<MyJobsEntity> myJobsList;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "FULL_NAME", nullable = false, length = 400)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Basic
    @Column(name = "mobile", nullable = true, length = 400)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 400)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "date_of_birth", nullable = true, length = 400)
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @OneToMany(mappedBy = "infoRef", targetEntity = MyJobsEntity.class, fetch = FetchType.EAGER)
    public List<MyJobsEntity> getMyJobsList() {
        return myJobsList;
    }

    public void setMyJobsList(List<MyJobsEntity> myJobsEntityList) {
        this.myJobsList = myJobsEntityList;
    }

    @Basic
    @Column(name="address", length=400)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name="user_title", length = 400)
    public String getUserTitle() {
        return userTitle;
    }

    public void setUserTitle(String userTitle) {
        this.userTitle = userTitle;
    }
}
