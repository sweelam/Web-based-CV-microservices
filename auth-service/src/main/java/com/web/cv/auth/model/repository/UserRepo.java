package com.web.cv.auth.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.cv.auth.model.entity.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);

}
