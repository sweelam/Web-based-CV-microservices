package com.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.web.model.entity.AppLoggerEntity;
import com.web.model.repository.AppLoggerRepo;
import com.web.utils.common.dto.RequestVo;

@Service
public class AppLoggerService {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AppLoggerRepo appLoggerRepo;

    /**
     *
     * @param requestVo
     */
    @Async
    public void saveRequest(RequestVo requestVo) {
        try {
        	LOG.info(new Thread().getName());
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
