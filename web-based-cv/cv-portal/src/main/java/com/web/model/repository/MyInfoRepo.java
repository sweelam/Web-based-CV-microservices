package com.web.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.model.entity.MyInfoEntity;

public interface MyInfoRepo extends JpaRepository<MyInfoEntity, Integer>{

}
