package com.web.model.vo;

import java.util.List;

import com.web.model.entity.MyJobsEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MyInfoVO {
    private int id;
    private String fullName;
    private String mobile;
    private String jobTitle;
    private String email;
    private String dateOfBirth;
    private String jobStartDate;
    private String jobEndDate;
    private List<MyJobsEntity> jobList;
}
