package com.web.service;


import com.web.model.repository.MyJobsRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.model.entity.MyInfoEntity;
import com.web.model.entity.MyJobsEntity;
import com.web.model.entity.UserSkillsEntity;
import com.web.model.repository.MyInfoRepo;
import com.web.model.repository.UserSkillsRepo;
import com.web.model.vo.MyInfoVO;
import com.web.util.ApiErrorHandling;
import com.web.utils.common.dto.UserInfoVo;

import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.Date;

@Service
@Slf4j
public class MyInfoService {
	private MyInfoRepo myInfoRepo;
	private MyJobsService myJobsService;
	private UserSkillsRepo userSkillsRepo;
	private MyJobsRepo myJobsRepo;

	@Autowired
	public MyInfoService(MyJobsService myJobsService, MyInfoRepo myInfoRepo,
						 UserSkillsRepo userSkillsRepo, MyJobsRepo myJobsRepo) {
		this.myInfoRepo = myInfoRepo;
		this.myJobsService = myJobsService;
		this.userSkillsRepo = userSkillsRepo;
		this.myJobsRepo = myJobsRepo;
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	public String getFullName(Integer id) throws RuntimeException{
		Optional<MyInfoEntity> info = myInfoRepo.findById(id);
		
		if(info.isPresent() && 
				(null != info.get().getFullName() || !info.get().getFullName().trim().equals("")))
			return info.get().getFullName();
		else
            throw new ApiErrorHandling();
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

	public void saveNewExperience(Map<String,Object> userExperience) {

		String jobTItle = (String) userExperience.get("jobTitle");
		String company = (String) userExperience.get("company");
		String details = (String) userExperience.get("jobDesc");
		String from = (String) userExperience.get("from");
		String to = (String) userExperience.get("to");


		try {
			MyJobsEntity job = new MyJobsEntity();
			job.setTitle(jobTItle);
			job.setJobDescription(details);
			job.setStartDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse(from).getTime()));
			job.setEndDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse(to).getTime()));
			job.setCompany(company);

			this.myJobsRepo.save(job);
		}catch (Exception e) {
			log.error("Error while saving new Experience ", e);
		}
	}
}
