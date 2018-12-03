package com.auth.test;

import com.web.cv.auth.AuthServiceApplication;
import com.web.cv.auth.service.UserAuthService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ContextConfiguration(classes = {AuthServiceApplication.class})
public class UserTest {

    @Autowired
    private UserAuthService userAuthService;

    @Test
    public void getUserDetailsTest() {
    }
}
