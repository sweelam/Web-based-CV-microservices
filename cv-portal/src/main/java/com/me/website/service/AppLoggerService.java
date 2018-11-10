package com.me.website.service;

import com.me.website.common.vo.RequestVo;
import com.me.website.model.entity.AppLoggerEntity;
import com.me.website.model.repository.AppLoggerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
