package com.me.website.model.repository;

import com.me.website.model.entity.AppLoggerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppLoggerRepo extends JpaRepository<AppLoggerEntity, Long> {

}
