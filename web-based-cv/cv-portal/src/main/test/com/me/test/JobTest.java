package com.me.test;

import com.me.website.MyProfileApplication;
import com.me.website.service.MyJobsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ContextConfiguration(classes = {MyProfileApplication.class})
public class JobTest {

    @Autowired
    private MyJobsService jobsService;

    @Test
    public void getJob() {
        assertNotEquals(jobsService.getJobTitle(1), "");
    }
}
