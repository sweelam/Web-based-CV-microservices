package com.web.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.model.entity.AppLoggerEntity;

@Repository
public interface AppLoggerRepo extends JpaRepository<AppLoggerEntity, Long> {

}
