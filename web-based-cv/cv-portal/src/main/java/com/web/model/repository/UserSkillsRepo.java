package com.web.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.model.entity.UserSkillsEntity;

@Repository
public interface UserSkillsRepo extends JpaRepository<UserSkillsEntity, Integer> {
	public List<UserSkillsEntity> findByUserId(int userId);
}
