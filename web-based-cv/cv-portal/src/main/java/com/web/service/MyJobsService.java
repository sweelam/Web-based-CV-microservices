package com.web.service;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.model.entity.MyJobsEntity;
import com.web.model.repository.MyJobsRepo;
import com.web.utils.common.dto.UserInfoVo;

@Service
public class MyJobsService {
    @Autowired
    MyJobsRepo myJobsRepo;

    public String getJobDescription(int id) {
        return myJobsRepo.findById(id).get().getJobDescription();
    }

    public String getJobTitle(Integer id) {
        StringBuffer jobTitle = new StringBuffer("");

        myJobsRepo.findById(id).ifPresent(t -> {
            jobTitle.append(t.getTitle());
        });

        return  !jobTitle.toString().equals("") ? jobTitle.toString() :  "Senior Software Engineer";
    }

    /**
     *
     * @param id
     * @return
     */
    public Date getJobStartDate(Integer id) {
        return myJobsRepo.findById(id).isPresent() ? myJobsRepo.findById(id).get().getStartDate() : null;
    }

    /**
     *
     * @param id
     * @return
     */
    public Date getJobEndDate(Integer id) {
        return myJobsRepo.findById(id).isPresent() ? myJobsRepo.findById(id).get().getEndDate() : null;
    }

    public void saveJobData(UserInfoVo userInfoVo, int infoRef) {
        MyJobsEntity myJobsEntity = new MyJobsEntity();

        String id = myJobsRepo.count() + 1 + "";
        
        myJobsEntity.setId(Integer.parseInt(id));
        myJobsEntity.setTitle(userInfoVo.getTitle());
        myJobsEntity.setStartDate(userInfoVo.getFrom());
        myJobsEntity.setEndDate(userInfoVo.getTo());
        myJobsEntity.setJobDescription(userInfoVo.getJobDesc());
        myJobsEntity.setInfoRef(infoRef);

        this.myJobsRepo.save(myJobsEntity);
    }

    public void updateJobDesc(int id, String jobDesc) {
        try {
            Optional<MyJobsEntity> myJobsEntity = myJobsRepo.findById(id);

            myJobsEntity.ifPresent(t -> {
                t.setJobDescription(jobDesc);
                myJobsRepo.save(t);
            });

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
