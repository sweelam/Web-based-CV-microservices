package com.web.utils.common.dto;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserInfoVo {
    private String fullName;
    private String address;
    private String mobile;
    private String title;
    private Date age;
    private Date from;
    private Date to;
    private String jobDesc;
    private String email;

    @Override
    public String toString() {
        return "UserInfoVo{" +
                "fullname='" + fullName + '\'' +
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
