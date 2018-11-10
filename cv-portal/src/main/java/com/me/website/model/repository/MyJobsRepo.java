package com.me.website.model.repository;

import com.me.website.model.entity.MyJobsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyJobsRepo extends JpaRepository<MyJobsEntity, Integer> {

}
