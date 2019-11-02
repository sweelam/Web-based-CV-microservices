package com.me.test;

import com.web.MyProfileApplication;
import com.web.service.MyInfoService;
import com.web.service.MyJobsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {MyProfileApplication.class})
public class JobTest {
    @Autowired
    private MyInfoService myInfoService;
    @Autowired
    private MyJobsService jobsService;

    @Test
    public void checkJob() {
        String fullName = myInfoService.getFullName(1);
        assertNotNull(fullName);
        assertNotEquals(fullName, "");
        assertEquals(fullName, "Mohamed Sweelam");
    }

    @Test
    public void checkJobs() {
        List jobs = jobsService.getJobInfo(1);
        assertNotNull(jobs);
    }
}
