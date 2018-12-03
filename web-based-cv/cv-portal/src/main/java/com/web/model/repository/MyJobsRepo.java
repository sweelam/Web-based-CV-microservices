package com.web.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.model.entity.MyJobsEntity;

@Repository
public interface MyJobsRepo extends JpaRepository<MyJobsEntity, Integer> {

}
