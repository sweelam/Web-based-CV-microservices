package com.web.service;


import com.web.MyProfileApplication;
import com.web.model.entity.AppLoggerEntity;
import com.web.model.repository.AppLoggerRepo;
import com.web.utils.common.dto.RequestVo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
//@ContextConfiguration(classes = {MyProfileApplication.class})
public class AppLoggerServiceTest {
    @Mock
    static AppLoggerRepo loggerRepo;

    static AppLoggerService loggerService;
    @BeforeAll
    public static void init() {
        loggerService = new AppLoggerService(loggerRepo);
    }

    @Test
    @Disabled
    public void testSaveRequest() {
        loggerService.saveRequest(new RequestVo());
        verify(loggerRepo, times(1)).save(any(AppLoggerEntity.class));
    }

}
