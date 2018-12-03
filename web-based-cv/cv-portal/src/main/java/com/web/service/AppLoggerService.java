package com.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.model.entity.AppLoggerEntity;
import com.web.model.repository.AppLoggerRepo;
import com.web.utils.common.dto.RequestVo;

@Service
public class AppLoggerService {
    @Autowired
    private AppLoggerRepo appLoggerRepo;

    /**
     *
     * @param requestVo
     */
    public void saveRequest(RequestVo requestVo) {
        try {
            AppLoggerEntity loggerEntity = new AppLoggerEntity();

            loggerEntity.setRequestPath(requestVo.getRequestPath());
            loggerEntity.setRequestHeader(requestVo.getRequestHeader());
            loggerEntity.setRequestMethod(requestVo.getRequestMethod());
            loggerEntity.setRequestToken(requestVo.getHeaderToken());

            this.appLoggerRepo.save(loggerEntity);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
