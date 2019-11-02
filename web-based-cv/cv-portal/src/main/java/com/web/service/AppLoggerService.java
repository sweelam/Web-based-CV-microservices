package com.web.service;

import com.web.model.entity.AppLoggerEntity;
import com.web.model.repository.AppLoggerRepo;
import com.web.utils.common.dto.RequestVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AppLoggerService {
    private AppLoggerRepo appLoggerRepo;

    public AppLoggerService(AppLoggerRepo appLoggerRepo) {
        this.appLoggerRepo = appLoggerRepo;
    }

    @Async
    public void saveRequest(RequestVo requestVo) {
        try {
            AppLoggerEntity loggerEntity = new AppLoggerEntity();
            loggerEntity.setRequestPath(requestVo.getRequestPath());
            loggerEntity.setRequestHeader(requestVo.getRequestHeader());
            loggerEntity.setRequestMethod(requestVo.getRequestMethod());
            loggerEntity.setRequestToken(requestVo.getHeaderToken());
            this.appLoggerRepo.save(loggerEntity);
        } catch(Exception e) {
            log.error("Error while intercepting ", e);
        }
    }
}
