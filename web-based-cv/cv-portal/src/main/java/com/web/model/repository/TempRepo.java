package com.web.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.model.entity.TempEntity;

@Repository
public interface TempRepo extends JpaRepository<TempEntity , Integer> {

    public TempEntity findById(int id);

}
