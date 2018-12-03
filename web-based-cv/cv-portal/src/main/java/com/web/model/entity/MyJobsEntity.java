package com.web.model.entity;


import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "my_jobs")
public class MyJobsEntity {
    private int id;
    private String title;
    private Date startDate;
    private Date endDate;
    private String company;
    private String jobDescription;

    @Column(name = "info_ref")
    @JoinColumn(name = "id", referencedColumnName = "id")
    private int infoRef;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 400)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "start_date", nullable = true)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "end_date", nullable = true)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "company", nullable = true, length = 400)
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Basic
    @Column(name = "job_description")
    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public int getInfoRef() {
        return infoRef;
    }

    public void setInfoRef(int info_ref) {
        this.infoRef = info_ref;
    }
}
