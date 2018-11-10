package com.me.website.controller;

import com.me.website.ApplicationUtil.ApiErrorHandling;
import com.me.website.common.ApiExceptionVO;
import com.me.website.common.vo.UserInfoVo;
import com.me.website.model.entity.MyInfoEntity;
import com.me.website.model.entity.MyJobsEntity;
import com.me.website.model.vo.MyInfoVO;
import com.me.website.service.MyInfoService;
import com.me.website.service.MyJobsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "/account")
public class MyInfoController {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MyInfoService myInfoService;

    @Autowired
    private MyJobsService jobsService;

    @GetMapping(value = "/fullName")
    public String getAccountName(@RequestParam(value = "accId", required = true) Integer accId) {
        return myInfoService.getFullName(accId);
    }

    @GetMapping(value = "/fullName/{accId}")
    public String getProfileName(@PathVariable(value = "accId") Integer accId) {
        String name = myInfoService.getFullName(accId);
        if (null == name)
            throw new ApiErrorHandling();
        return name;
    }

    @GetMapping(value = "/job/info/{accId}")
    public List<MyJobsEntity> getJobInfo(@PathVariable(value = "accId", required = true) Integer accId) {
        return myInfoService.getJobInfo(accId);
    }


    @RequestMapping(value = "/update/job/{accId}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void updateJob(@PathVariable("accId") int accId, @PathVariable("desc") String jobDesc) {
        myInfoService.updateJob(accId, jobDesc);
    }


    @GetMapping(value = "/info/details/{accId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<MyInfoEntity> getInfoDetailsByaccId(@PathVariable("accId") Integer accId) {
        return new ResponseEntity<MyInfoEntity>(myInfoService.getInfoDetailsById(accId), HttpStatus.OK);
    }

    @GetMapping(value = "/info/skills", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Map<String, Object>>> getAllSkills() {
        try {
            if (null != this.myInfoService.getUserSkills())
                return new ResponseEntity<List<Map<String, Object>>>(this.myInfoService.getUserSkills(), HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/info/data", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void saveUserInfo(@RequestBody UserInfoVo userInfoVo) throws Exception {
        this.myInfoService.saveMyInfo(userInfoVo);
    }

    @RequestMapping(value = "/info/data/{accId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<MyInfoVO> getUserInfo(@PathVariable("accId") Integer accId) {
        return new ResponseEntity<MyInfoVO>(this.myInfoService.getInfoById(accId), HttpStatus.OK);
    }
}
