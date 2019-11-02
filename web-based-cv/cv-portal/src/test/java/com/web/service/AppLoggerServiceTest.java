package com.web.service;


import com.web.MyProfileApplication;
import com.web.model.entity.AppLoggerEntity;
import com.web.model.repository.AppLoggerRepo;
import com.web.utils.common.dto.RequestVo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {MyProfileApplication.class})
public class AppLoggerServiceTest {
    @Mock
    AppLoggerRepo loggerRepo;

    AppLoggerService loggerService;
    @Before
    public void init() {
        loggerService = new AppLoggerService(loggerRepo);
    }

    @Test
    public void testSaveRequest() {
        loggerService.saveRequest(new RequestVo());
        verify(loggerRepo, times(1)).save(any(AppLoggerEntity.class));
    }

}
