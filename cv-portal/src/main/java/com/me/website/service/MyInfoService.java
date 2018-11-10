package com.me.website.service;

import com.me.website.ApplicationUtil.ApiErrorHandling;
import com.me.website.common.vo.UserInfoVo;
import com.me.website.model.entity.MyInfoEntity;
import com.me.website.model.entity.MyJobsEntity;
import com.me.website.model.repository.MyInfoRepo;
import com.me.website.model.repository.UserSkillsRepo;
import com.me.website.model.vo.MyInfoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

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
        if(null == myInfoRepo.findById(id).get())
            return null;
        else
            return myInfoRepo.findById(id).get().getFullName();
    }

    /**
     *
     * @param id
     * @return
     */
    public List<MyJobsEntity> getJobInfo(Integer id) {
        return  myInfoRepo.findById(id).get().getMyJobsList();
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

            MyInfoEntity myInfoEntity =  myInfoRepo.findById(id).get();
            if (null != myInfoEntity) {
                myInfoVO = new MyInfoVO();
                myInfoVO.setFullName(myInfoEntity.getFullName());
                myInfoVO.setEmail(myInfoEntity.getEmail());
                myInfoVO.setMobile(myInfoEntity.getMobile());

                List<MyJobsEntity> myJobsEntity = myJobsService.myJobsRepo.findAll();
                if (null != myJobsEntity) {
                    myInfoVO.setJobList(myJobsEntity);
                }else
                    throw new ApiErrorHandling();
            }

            return myInfoVO;
    }

    /**
     *
     * @param id
     * @return
     */
    public MyInfoEntity getInfoDetailsById(Integer id) {

        MyInfoEntity myInfoEntity = myInfoRepo.findById(id).get();

        return myInfoEntity;
    }

    public List<Map<String , Object>> getUserSkills() {
        List<Map<String , Object>> res = new LinkedList<>();

        this.userSkillsRepo.findAll()
                .stream()
                .forEach(t -> {
                    Map<String , Object> skills  = new HashMap<>();

                    skills.put("id" , t.getId());
                    skills.put("desc" , t.getSkills());

                    res.add(skills);
                });

        return res;
    }

    /**
     *
     * @param userInfoVo
     * @throws Exception
     */
    public void saveMyInfo(UserInfoVo userInfoVo) throws Exception {
        MyInfoEntity myInfoEntity = new MyInfoEntity();

        String x = myInfoRepo.count() + 1 + "";

        myInfoEntity.setId(Integer.parseInt(x));
        myInfoEntity.setFullName(userInfoVo.getFullname());
        myInfoEntity.setEmail(userInfoVo.getEmail());
        myInfoEntity.setAddress(userInfoVo.getAddress());
        myInfoEntity.setMobile(userInfoVo.getMobile());

        this.myInfoRepo.save(myInfoEntity);

        this.myJobsService.saveJobData(userInfoVo, Integer.parseInt(x));
    }
}
