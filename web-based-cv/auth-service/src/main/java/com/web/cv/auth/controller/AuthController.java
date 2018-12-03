package com.web.cv.auth.controller;

import java.util.Map;

import com.web.cv.auth.model.vo.UserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import com.web.cv.auth.service.UserAuthService;
import com.web.utils.common.BusinessException;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class AuthController {
    private UserAuthService userAuthService;

    @Autowired
    public AuthController(UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    @RequestMapping(value = "/login-form",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> login(@RequestBody @Valid UserCredentials body, Errors errors) throws BusinessException {

        if (errors.hasErrors())
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

        Map<String , Object> res = userAuthService.isUserExist(body);

        return !res.get("res").toString().equals("/welcome") ?
                new ResponseEntity<>(res, HttpStatus.OK) : new ResponseEntity<>(res, HttpStatus.NOT_FOUND);

    }

}
