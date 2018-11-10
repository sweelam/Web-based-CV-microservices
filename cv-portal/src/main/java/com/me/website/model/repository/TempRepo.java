package com.me.website.model.repository;

import com.me.website.model.entity.TempEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TempRepo extends JpaRepository<TempEntity , Integer> {

    public TempEntity findById(int id);

}
