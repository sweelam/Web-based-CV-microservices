package com.web.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.web.model.vo.UserExperienceVO;
import com.web.proxy.AssetProxy;
import com.web.service.MyJobsService;
import com.web.util.Response;
import lombok.extern.slf4j.Slf4j;
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
import com.web.utils.common.BusinessException;
import com.web.utils.common.dto.UserInfoVo;

//import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping(value = "/account")
@Slf4j
public class MyInfoController {
    private MyInfoService infoService;
    private MyJobsService jobsService;
    private AssetProxy assetProxy;

    public MyInfoController(MyInfoService infoService, MyJobsService jobsService, AssetProxy assetProxy) {
        this.infoService = infoService;
        this.jobsService = jobsService;
        this.assetProxy = assetProxy;
    }

    @GetMapping(value = "/fullName")
    @Loggable
//    @ApiOperation(value="Account Name", notes="Account Page Title")
    public String getAccountName(@RequestParam(value = "accId", required = true) Integer accId) {
        return infoService.getFullName(accId);
    }

    @GetMapping(value = "/fullName/{accId}")
    @Loggable
    public String getProfileName(@PathVariable(value = "accId") Integer accId) throws RuntimeException {
        return infoService.getFullName(accId);
    }

    @GetMapping(value = "/job/info/{accId}")
//    @ApiOperation(value="Job Information", notes="Display available job information")
    public List<MyJobsEntity> getJobInfo(@PathVariable(value = "accId") Integer accId) {
        return jobsService.getJobInfo(accId);
    }


    @RequestMapping(value = "/job/{accId}", method = RequestMethod.PUT)
//    @ApiOperation(value="Job Updater", notes="Update job description")
    public void updateJob(@PathVariable("accId") int accId, @PathVariable("desc") String jobDesc) {
        jobsService.updateJob(accId, jobDesc);
    }


    @GetMapping(value = "/info/details/{accId}")
    public ResponseEntity<MyInfoEntity> getInfoDetailsByaccId(@PathVariable("accId") Integer accId) {
        return new ResponseEntity<>(infoService.getInfoDetailsById(accId), HttpStatus.OK);
    }

    @GetMapping(value = "/info/skills/{accId}")
//    @ApiOperation(value="User Skills", notes="List of user's skills")
    @Loggable
    public ResponseEntity<List<Map<String, Object>>> getAllSkills(@PathVariable("accId") int accId) {
        try {
        	List<Map<String, Object>> userSkills = this.infoService.getUserSkills(accId);
        	
            return (null != userSkills && userSkills .size() > 0) ?
                new ResponseEntity<>(userSkills, HttpStatus.OK) :
                	new ResponseEntity<>(HttpStatus.NOT_FOUND);
            
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/info/data")
    public ResponseEntity saveUserInfo(@RequestBody @Valid UserInfoVo userInfoVo, Errors error) throws Exception {
        if(error.hasErrors())
        	throw new BusinessException("Missing inputs!", "Inputs are not correct", HttpStatus.NOT_ACCEPTABLE.name(),
                    HttpStatus.NOT_ACCEPTABLE.value());
        
    	boolean savedSuccessfully = this.infoService.saveMyInfo(userInfoVo);
    	return savedSuccessfully ? ResponseEntity.status(HttpStatus.CREATED).build() : ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
    }

    @GetMapping(value = "/info/data/{accId}")
    public ResponseEntity<MyInfoVO> getUserInfo(@PathVariable("accId") Integer accId) {
        return new ResponseEntity<>(this.infoService.getInfoById(accId), HttpStatus.OK);
    }

    @PostMapping(value = "/info/experience")
    public ResponseEntity<String> saveExperience(@RequestBody UserExperienceVO userExperience) {
        this.infoService.saveNewExperience(userExperience);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/kl")
    public String get() {
        return assetProxy.getSysLogos();
    }
}
