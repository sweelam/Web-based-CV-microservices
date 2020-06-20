package com.auth.test;

import com.web.AuthServiceApplication;
import com.web.cv.auth.model.repository.UserRepo;
import com.web.cv.auth.model.vo.UserPrincipale;
import com.web.cv.auth.service.impl.UserAuthImpl;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ContextConfiguration(classes = {AuthServiceApplication.class})
@DataJpaTest
public class UserTest {
    private UserAuthImpl userAuth;

    @Mock
    UserRepo userRepo;

    @Before
    public void init() {
        userAuth = new UserAuthImpl(userRepo);
    }

    @Test
    @Ignore
    public void userDetailsServiceExist() {
        UserDetailsService ud = userAuth.getUserDetails();
        assertNotNull(ud);
        UserPrincipale userPrincipale = (UserPrincipale) ud;
        assertNotEquals("", userPrincipale.getUsername());
        verify(userRepo, times(1)).findByUsername(any());
    }

    @Test
    @Ignore
    public void findByUsername() {
        assertEquals(userRepo.findByUsername(any()), Optional.empty());
    }

    @Test
    public void getUserDetailsTest() {
    }
}
