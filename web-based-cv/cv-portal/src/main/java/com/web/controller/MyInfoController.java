package com.web.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.cv.logging.Loggable;
import com.web.model.entity.MyInfoEntity;
import com.web.model.entity.MyJobsEntity;
import com.web.model.vo.MyInfoVO;
import com.web.service.MyInfoService;
import com.web.util.ApiErrorHandling;
import com.web.utils.common.BusinessException;
import com.web.utils.common.dto.UserInfoVo;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping(value = "/account")
public class MyInfoController {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MyInfoService myInfoService;


    @GetMapping(value = "/fullName")
    @Loggable
    @ApiOperation(value="Account Name", notes="Account Page Title")
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
    @ApiOperation(value="Job Information", notes="Display available job information")
    public List<MyJobsEntity> getJobInfo(@PathVariable(value = "accId") Integer accId) {
        return myInfoService.getJobInfo(accId);
    }


    @RequestMapping(value = "/job/{accId}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value="Job Updater", notes="Update job description")
    public void updateJob(@PathVariable("accId") int accId, @PathVariable("desc") String jobDesc) {
        myInfoService.updateJob(accId, jobDesc);
    }


    @GetMapping(value = "/info/details/{accId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<MyInfoEntity> getInfoDetailsByaccId(@PathVariable("accId") Integer accId) {
        return new ResponseEntity<>(myInfoService.getInfoDetailsById(accId), HttpStatus.OK);
    }

    @GetMapping(value = "/info/skills/{accId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value="User Skills", notes="List of user's skills")
    public ResponseEntity<List<Map<String, Object>>> getAllSkills(@PathVariable("accId") int accId) {
        try {
        	List<Map<String, Object>> userSkills = this.myInfoService.getUserSkills(accId);
        	
            return (null != userSkills && userSkills .size() > 0) ?
                new ResponseEntity<>(userSkills, HttpStatus.OK) :
                	new ResponseEntity<>(HttpStatus.NOT_FOUND);
            
        } catch (Exception e) {
            LOGGER.error("Error in User skills API : ",  e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/info/data", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void saveUserInfo(@RequestBody @Valid UserInfoVo userInfoVo, Errors error) throws Exception {
        if(error.hasErrors())
        	throw new BusinessException("Missing inputs!", "Inputs are not correct", HttpStatus.NOT_ACCEPTABLE.name(), HttpStatus.NOT_ACCEPTABLE.value());
        
    	this.myInfoService.saveMyInfo(userInfoVo);
    }

    @GetMapping(value = "/info/data/{accId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<MyInfoVO> getUserInfo(@PathVariable("accId") Integer accId) {
        return new ResponseEntity<>(this.myInfoService.getInfoById(accId), HttpStatus.OK);
    }
}
