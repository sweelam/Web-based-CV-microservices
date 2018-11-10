package com.me.website.model.repository;

import com.me.website.model.entity.UserSkillsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSkillsRepo extends JpaRepository<UserSkillsEntity, Integer> {

}
