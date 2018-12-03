package com.web.cv.auth.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.cv.auth.model.entity.AppLoggerEntity;

@Repository
public interface AppLoggerRepo extends JpaRepository<AppLoggerEntity, Long> {

}
