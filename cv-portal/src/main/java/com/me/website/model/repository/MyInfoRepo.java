package com.me.website.model.repository;

import com.me.website.model.entity.MyInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyInfoRepo extends JpaRepository<MyInfoEntity, Integer>{

}
