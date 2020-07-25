package com.web.service;


import com.google.gson.JsonObject;
import com.web.model.repository.MyJobsRepo;
import com.web.model.vo.UserExperienceVO;
import com.web.utils.common.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.web.model.entity.MyInfoEntity;
import com.web.model.entity.MyJobsEntity;
import com.web.model.entity.UserSkillsEntity;
import com.web.model.repository.MyInfoRepo;
import com.web.model.repository.UserSkillsRepo;
import com.web.model.vo.MyInfoVO;
import com.web.util.ApiErrorHandling;
import com.web.utils.common.dto.UserInfoVo;
import springfox.documentation.spring.web.json.Json;

import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.Date;

@Service
@Slf4j
public class MyInfoService {
    private final MyInfoRepo myInfoRepo;
    private final MyJobsService myJobsService;
    private final UserSkillsRepo userSkillsRepo;
    private final MyJobsRepo myJobsRepo;
    private final RedisTemplate redisTemplate;

    public MyInfoService(MyInfoRepo myInfoRepo, MyJobsService myJobsService, UserSkillsRepo userSkillsRepo, MyJobsRepo myJobsRepo, RedisTemplate redisTemplate) {
        this.myInfoRepo = myInfoRepo;
        this.myJobsService = myJobsService;
        this.userSkillsRepo = userSkillsRepo;
        this.myJobsRepo = myJobsRepo;
        this.redisTemplate = redisTemplate;
    }

    public String getFullName(Integer id) throws RuntimeException {
        Optional<MyInfoEntity> info = myInfoRepo.findById(id);

        if (info.isPresent() &&
                (!StringUtils.isEmpty(info.get().getFullName()) || !"".equalsIgnoreCase(info.get().getFullName().trim())))
            return info.get().getFullName();
        else
            throw new ApiErrorHandling();
    }

    public MyInfoVO getInfoById(Integer id) {
        MyInfoVO myInfoVO = null;

        Optional<MyInfoEntity> myInfoEntity = myInfoRepo.findById(id);
        if (myInfoEntity.isPresent()) {

            myInfoVO = new MyInfoVO();
            myInfoVO.setFullName(myInfoEntity.get().getFullName());
            myInfoVO.setEmail(myInfoEntity.get().getEmail());
            myInfoVO.setMobile(myInfoEntity.get().getMobile());

            List<MyJobsEntity> myJobsEntity = myJobsService.getAllJobs();
            if (null != myJobsEntity && myJobsEntity.size() > 0)
                myInfoVO.setJobList(myJobsEntity);

        }

        return myInfoVO;
    }


    public MyInfoEntity getInfoDetailsById(Integer id) {
        Optional<MyInfoEntity> userInfo = myInfoRepo.findById(id);
        return userInfo.isPresent() ? userInfo.get() : null;
    }

    public List<Map<String, Object>> getUserSkills(int userId) {
        final String SKILL_KEY = "user-skills-" + userId;
        if (redisTemplate.hasKey(SKILL_KEY)) {
            String response = (String) redisTemplate.opsForValue().get(SKILL_KEY);
            return JsonUtils.toObject(JsonUtils.parse(response), List.class);
        }
        List<Map<String, Object>> res = new LinkedList<>();
        List<UserSkillsEntity> userSkills = this.userSkillsRepo.findByUserId(userId);
        if (null != userSkills && userSkills.size() > 0)
            userSkills.stream().forEach(t -> {
                Map<String, Object> skills = new HashMap<>();
                skills.put("userSkills", t);
                res.add(skills);
            });
        redisTemplate.opsForValue().set(SKILL_KEY, JsonUtils.toJson(res).getAsJsonArray().toString());
        return !res.isEmpty() ? res : Collections.emptyList();
    }

    public boolean saveMyInfo(UserInfoVo userInfoVo) {
        Integer infoId = Long.valueOf(myInfoRepo.count() + 1).intValue();
        MyInfoEntity myInfoEntity = new MyInfoEntity();
        try {
            myInfoEntity.setId(infoId);
            myInfoEntity.setFullName(userInfoVo.getFullName());
            myInfoEntity.setEmail(userInfoVo.getEmail());
            myInfoEntity.setAddress(userInfoVo.getAddress());
            myInfoEntity.setMobile(userInfoVo.getMobile());
            this.myInfoRepo.save(myInfoEntity);
            this.myJobsService.saveJobData(userInfoVo, infoId);
            return true;
        } catch (Exception e) {
            log.error("Cannot save info {}", ExceptionUtils.getMessage(e));
            return false;
        }
    }

    public void saveNewExperience(UserExperienceVO userExperience) {
        try {

            MyJobsEntity job = new MyJobsEntity();
            job.setTitle(userExperience.getJobTitle());
            job.setJobDescription(userExperience.getDetails());
            if (null != userExperience.getFrom())
                job.setStartDate(
                        new Date(
                                new SimpleDateFormat("yyyy-MM-dd")
                                        .parse(userExperience.getFrom()).getTime()));
            if (null != userExperience.getTo())
                job.setEndDate(
                        new Date(
                                new SimpleDateFormat("yyyy-MM-dd")
                                        .parse(userExperience.getTo()).getTime()));
            job.setCompany(userExperience.getCompany());

            this.myJobsRepo.save(job);
        } catch (Exception e) {
            log.error("Error while saving new Experience {}", ExceptionUtils.getStackTrace(e));
        }
    }
}
