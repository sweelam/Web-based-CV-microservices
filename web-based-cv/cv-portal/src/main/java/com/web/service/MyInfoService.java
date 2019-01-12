package com.web.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.model.entity.MyInfoEntity;
import com.web.model.entity.MyJobsEntity;
import com.web.model.entity.UserSkillsEntity;
import com.web.model.repository.MyInfoRepo;
import com.web.model.repository.UserSkillsRepo;
import com.web.model.vo.MyInfoVO;
import com.web.utils.common.dto.UserInfoVo;

@Service
public class MyInfoService {
	Logger LOG = LoggerFactory.getLogger(this.getClass());

	private MyInfoRepo myInfoRepo;
	private MyJobsService myJobsService;
	private UserSkillsRepo userSkillsRepo;

	@Autowired
	public MyInfoService(MyJobsService myJobsService, MyInfoRepo myInfoRepo, UserSkillsRepo userSkillsRepo) {
		this.myInfoRepo = myInfoRepo;
		this.myJobsService = myJobsService;
		this.userSkillsRepo = userSkillsRepo;
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	public String getFullName(Integer id) {
		Optional<MyInfoEntity> info = myInfoRepo.findById(id);
		return info.isPresent() ? info.get().getFullName() : null;
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	public List<MyJobsEntity> getJobInfo(Integer id) {
		Optional<MyInfoEntity> myInfo = myInfoRepo.findById(id); 
		return myInfo.isPresent() ? myInfo.get().getMyJobsList() : null;
	}

	public void updateJob(int id, String desc) {
		myJobsService.updateJobDesc(id, desc);
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	public MyInfoVO getInfoById(Integer id) {
		MyInfoVO myInfoVO = null;

		Optional<MyInfoEntity> myInfoEntity = myInfoRepo.findById(id);
		if (myInfoEntity.isPresent()) {

			myInfoVO = new MyInfoVO();
			myInfoVO.setFullName(myInfoEntity.get().getFullName());
			myInfoVO.setEmail(myInfoEntity.get().getEmail());
			myInfoVO.setMobile(myInfoEntity.get().getMobile());

			List<MyJobsEntity> myJobsEntity = myJobsService.myJobsRepo.findAll();
			if (null != myJobsEntity && myJobsEntity.size() > 0)
				myInfoVO.setJobList(myJobsEntity);

		}

		return myInfoVO;
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	public MyInfoEntity getInfoDetailsById(Integer id) {
		Optional<MyInfoEntity> userInfo = myInfoRepo.findById(id);
		return userInfo.isPresent() ? userInfo.get() : null;
	}

	
	/**
	 * 
	 * @return
	 */
	public List<Map<String, Object>> getUserSkills(int userId) {
		List<Map<String, Object>> res = new LinkedList<>();
		
		List<UserSkillsEntity> userSkills = this.userSkillsRepo.findByUserId(userId);
		if(null != userSkills && userSkills.size() > 0)
			userSkills.stream().forEach(t -> {
				Map<String, Object> skills = new HashMap<>();
				skills.put("userSkills", t);
				res.add(skills);
			});

		return !res.isEmpty()? res : Collections.emptyList();
	}

	/**
	 *
	 * @param userInfoVo
	 * @throws Exception
	 */
	public void saveMyInfo(UserInfoVo userInfoVo) throws Exception {
		MyInfoEntity myInfoEntity = new MyInfoEntity();

		Integer infoId = Integer.parseInt(myInfoRepo.count() + 1 + "");

		myInfoEntity.setId(infoId);
		myInfoEntity.setFullName(userInfoVo.getFullname());
		myInfoEntity.setEmail(userInfoVo.getEmail());
		myInfoEntity.setAddress(userInfoVo.getAddress());
		myInfoEntity.setMobile(userInfoVo.getMobile());

		this.myInfoRepo.save(myInfoEntity);

		this.myJobsService.saveJobData(userInfoVo, infoId);
	}
}
