package com.me.website.service;

import com.me.website.common.vo.UserInfoVo;
import com.me.website.model.entity.MyInfoEntity;
import com.me.website.model.entity.MyJobsEntity;
import com.me.website.model.repository.MyJobsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

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
