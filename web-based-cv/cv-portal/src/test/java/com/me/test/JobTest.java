package com.me.test;

import com.infra.InfraSupport;
import com.web.MyProfileApplication;
import com.web.model.entity.MyInfoEntity;
import com.web.model.entity.MyJobsEntity;
import com.web.service.MyInfoService;
import com.web.service.MyJobsService;
import com.web.utils.common.dto.UserInfoVo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ContextConfiguration(classes = {MyProfileApplication.class})
@ActiveProfiles("test")
public class JobTest extends InfraSupport {
    @Autowired
    private MyInfoService myInfoService;
    @Autowired
    private MyJobsService jobsService;

    @Test
    @Disabled
    public void checkJob() {
        String fullName = myInfoService.getFullName(1);
        assertNotNull(fullName);
        assertNotEquals(fullName, "");
        assertEquals(fullName, "Mohamed Sweelam");
    }

    @Test
    public void checkJobs() {
        UserInfoVo userInfoVo = UserInfoVo.builder()
                .jobDesc("Test job for test code")
                .title("The big tester")
                .age(41)
                .from(new Date(2022, 12, 11))
                .build();

        MyJobsEntity myJobsEntity = jobsService.saveJobData(userInfoVo, 1);


        MyInfoEntity infoEntity = new MyInfoEntity();
        infoEntity.setEmail("msweelam@gmail.com");
        infoEntity.setAddress("Helwan - Egypt");
        infoEntity.setMobile("0503087132");
        infoEntity.setFullName("Mohamed Gamal Sweelam");
        infoEntity.setUserTitle("Al Sair Ale");

        List<MyJobsEntity> myJobsList = infoEntity.getMyJobsList();
        myJobsList.add(myJobsEntity);
        infoEntity.setMyJobsList(myJobsList);

        var jobInfo = jobsService.addInfo(infoEntity);

        // FIXME make the join correctly
        var jobs = jobsService.getJobInfo(0);

        assertNotNull(jobs);
    }



}
