package com.web.service;

import com.web.model.entity.MyInfoEntity;
import com.web.model.entity.MyJobsEntity;
import com.web.model.repository.MyInfoRepo;
import com.web.model.repository.MyJobsRepo;
import com.web.utils.common.dto.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MyJobsService {
    private MyJobsRepo myJobsRepo;
    private MyInfoRepo myInfoRepo;

    public MyJobsService(MyJobsRepo myJobsRepo, MyInfoRepo myInfoRepo) {
        this.myJobsRepo = myJobsRepo;
        this.myInfoRepo = myInfoRepo;
    }

    public String getJobDescription(int id) {
        Optional<MyJobsEntity> jobEntity = myJobsRepo.findById(id);
        return jobEntity.isPresent() ? jobEntity.get().getJobDescription() : "";
    }

    public String getJobTitle(Integer id) {
        StringBuffer jobTitle = new StringBuffer("");

        myJobsRepo.findById(id).ifPresent(t -> {
            jobTitle.append(t.getTitle());
        });

        return !jobTitle.toString().equals("") ? jobTitle.toString() : "Senior Software Engineer";
    }

    public List getAllJobs() {
        return myJobsRepo.findAll();
    }

    /**
     * @param id
     * @return
     */
    public List<MyJobsEntity> getJobInfo(Integer id) {
        Optional<MyInfoEntity> myInfo = myInfoRepo.findById(id);
        return myInfo.isPresent() ? myInfo.get().getMyJobsList() : Collections.emptyList();
    }


    public void updateJob(int id, String desc) {
        updateJobDesc(id, desc);
    }

    /**
     * @param id
     * @return
     */
    public Date getJobStartDate(Integer id) {
        Optional<MyJobsEntity> job = myJobsRepo.findById(id);
        return job.isPresent() ? job.get().getStartDate() : null;
    }

    /**
     * @param id
     * @return
     */
    public Date getJobEndDate(Integer id) {
        Optional<MyJobsEntity> job = myJobsRepo.findById(id);
        return job.isPresent() ? job.get().getEndDate() : null;
    }

    public MyJobsEntity saveJobData(UserInfoVo userInfoVo, int infoRef) {
        MyJobsEntity myJobsEntity = new MyJobsEntity();

        var id = (int) myJobsRepo.count() + 1;

        myJobsEntity.setId(id);
        myJobsEntity.setTitle(userInfoVo.getTitle());
        myJobsEntity.setStartDate(userInfoVo.getFrom());
        myJobsEntity.setEndDate(userInfoVo.getTo());
        myJobsEntity.setJobDescription(userInfoVo.getJobDesc());
        myJobsEntity.setInfoRef(infoRef);

        return this.myJobsRepo.save(myJobsEntity);
    }

    public void updateJobDesc(int id, String jobDesc) {
        try {
            Optional<MyJobsEntity> myJobsEntity = myJobsRepo.findById(id);

            myJobsEntity.ifPresent(t -> {
                t.setJobDescription(jobDesc);
                myJobsRepo.save(t);
            });

        } catch (Exception e) {
            log.error("Error while updating job desc : ", e);
        }
    }


    public MyInfoEntity addInfo(MyInfoEntity info) {
        return myInfoRepo.save(info);
    }

}
