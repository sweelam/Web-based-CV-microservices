package com.me.test;

import com.web.MyProfileApplication;
import com.web.service.MyInfoService;
import com.web.service.MyJobsService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


//@SpringBootTest
//@ContextConfiguration(classes = {MyProfileApplication.class})
public class JobTest {
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
    @Disabled
    public void checkJobs() {
        List jobs = jobsService.getJobInfo(1);
        assertNotNull(jobs);
    }
}
