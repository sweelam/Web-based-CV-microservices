package com.web.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.ApplicationUtil.ApiErrorHandling;
import com.web.cv.logging.Loggable;
import com.web.model.entity.MyInfoEntity;
import com.web.model.entity.MyJobsEntity;
import com.web.model.vo.MyInfoVO;
import com.web.service.MyInfoService;
import com.web.utils.common.BusinessException;
import com.web.utils.common.dto.UserInfoVo;

@RestController
@CrossOrigin
@RequestMapping(value = "/account")
public class MyInfoController {
    @Autowired
    private MyInfoService myInfoService;


    @GetMapping(value = "/fullName")
    @Loggable
    public String getAccountName(@RequestParam(value = "accId", required = true) Integer accId) {
        return myInfoService.getFullName(accId);
    }

    @GetMapping(value = "/fullName/{accId}")
    @Loggable
    public String getProfileName(@PathVariable(value = "accId") Integer accId) {
        String name = myInfoService.getFullName(accId);
        if (null == name)
            throw new ApiErrorHandling();
        return name;
    }

    @GetMapping(value = "/job/info/{accId}")
    public List<MyJobsEntity> getJobInfo(@PathVariable(value = "accId") Integer accId) {
        return myInfoService.getJobInfo(accId);
    }


    @RequestMapping(value = "/job/{accId}",
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
        	List<Map<String, Object>> userSkills = this.myInfoService.getUserSkills();
        	
            return (null != userSkills && userSkills .size() > 0) ?
                new ResponseEntity<List<Map<String, Object>>>(userSkills, HttpStatus.OK) :
                	new ResponseEntity<>(HttpStatus.NOT_FOUND);
            
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/info/data", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void saveUserInfo(@RequestBody @Valid UserInfoVo userInfoVo, Errors error) throws Exception {
        if(error.hasErrors())
        	throw new BusinessException("Missing inputs!", "Inputs are not correct", HttpStatus.NOT_ACCEPTABLE.name(), HttpStatus.NOT_ACCEPTABLE.value());
        
    	this.myInfoService.saveMyInfo(userInfoVo);
    }

    @RequestMapping(value = "/info/data/{accId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<MyInfoVO> getUserInfo(@PathVariable("accId") Integer accId) {
        return new ResponseEntity<MyInfoVO>(this.myInfoService.getInfoById(accId), HttpStatus.OK);
    }
}
