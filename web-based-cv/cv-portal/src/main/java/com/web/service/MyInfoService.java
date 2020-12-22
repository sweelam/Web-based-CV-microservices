package com.web.service;


import com.web.model.entity.MyInfoEntity;
import com.web.model.entity.MyJobsEntity;
import com.web.model.entity.UserSkillsEntity;
import com.web.model.repository.MyInfoRepo;
import com.web.model.repository.MyJobsRepo;
import com.web.model.repository.UserSkillsRepo;
import com.web.model.vo.MyInfoVO;
import com.web.model.vo.UserExperienceVO;
import com.web.util.ApiErrorHandling;
import com.web.utils.common.JsonUtils;
import com.web.utils.common.dto.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class MyInfoService {
    private final MyInfoRepo myInfoRepo;
    private final MyJobsService myJobsService;
    private final UserSkillsRepo userSkillsRepo;
    private final MyJobsRepo myJobsRepo;
    private final RedisTemplate redisTemplate;

    public MyInfoService(MyInfoRepo myInfoRepo, MyJobsService myJobsService,
                         UserSkillsRepo userSkillsRepo, MyJobsRepo myJobsRepo,
                         RedisTemplate redisTemplate) {
        this.myInfoRepo = myInfoRepo;
        this.myJobsService = myJobsService;
        this.userSkillsRepo = userSkillsRepo;
        this.myJobsRepo = myJobsRepo;
        this.redisTemplate = redisTemplate;
    }

    public String getFullName(Integer id) throws RuntimeException {
        Optional<MyInfoEntity> info = myInfoRepo.findById(id);

        if (info.isPresent() && !StringUtils.isEmpty(info.get().getFullName()))
            return info.get().getFullName();
        else
            throw new ApiErrorHandling();
    }

    public MyInfoVO getInfoById(Integer id) {
        MyInfoVO myInfoVO = new MyInfoVO();
        Optional<MyInfoEntity> myInfoEntity = myInfoRepo.findById(id);
        myInfoEntity.ifPresent(k -> buildInfoVo(k, myInfoVO));
        return myInfoVO;
    }

    private MyInfoVO buildInfoVo(MyInfoEntity myInfoEntity, MyInfoVO myInfoVO) {
        myInfoVO.setFullName(myInfoEntity.getFullName());
        myInfoVO.setEmail(myInfoEntity.getEmail());
        myInfoVO.setMobile(myInfoEntity.getMobile());
        List<MyJobsEntity> myJobsEntityList = myJobsService.getAllJobs();
        if (!CollectionUtils.isEmpty(myJobsEntityList))
            myInfoVO.setJobList(myJobsEntityList);
        return myInfoVO;
    }


    public MyInfoEntity getInfoDetailsById(Integer id) {
        Optional<MyInfoEntity> userInfo = myInfoRepo.findById(id);
        return userInfo.orElse(null);
    }

    public List<Map<String, Object>> getUserSkills(int userId) {
        final String SKILL_KEY = "user-skills-" + userId;
        String response = getFromRedis(SKILL_KEY);
        if (!StringUtils.isEmpty(response))
            return JsonUtils.toObject(JsonUtils.parse(response), List.class);

        List<Map<String, Object>> res = new LinkedList<>();
        List<UserSkillsEntity> userSkills = this.userSkillsRepo.findByUserId(userId);
        if (null != userSkills && userSkills.size() > 0)
            userSkills.stream().forEach(t -> {
                Map<String, Object> skills = new HashMap<>();
                skills.put("userSkills", t);
                res.add(skills);
            });
        addToRedis(SKILL_KEY, JsonUtils.toJson(res).getAsJsonArray().toString());
        return !res.isEmpty() ? res : Collections.emptyList();
    }

    private String getFromRedis(final String key) {
        if (redisTemplate.hasKey(key)) {
            return  (String) redisTemplate.opsForValue().get(key);
        }
        return "";
    }

    private void addToRedis(String key, String value) {
        new Thread(() -> redisTemplate.opsForValue().set(key, value)).start();
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
