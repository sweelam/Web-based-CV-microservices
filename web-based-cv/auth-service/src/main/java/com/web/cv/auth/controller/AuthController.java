package com.web.cv.auth.controller;

import com.web.cv.auth.model.vo.UserCredentials;
import com.web.cv.auth.service.UserAuthService;
import com.web.cv.logging.Loggable;
import com.web.utils.common.BusinessException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/user")
@RefreshScope
public class AuthController {
    private UserAuthService userAuthService;

    public AuthController(@Qualifier("userAuthImpl") UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    @PostMapping(value = "/login-form", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Loggable
    public ResponseEntity<Map<String, Object>> login(@RequestBody @Valid UserCredentials body, Errors errors)
            throws BusinessException {
        if (errors.hasErrors())
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

        boolean exist = userAuthService.isUserExist(body);
		Map<String, Object> res = new HashMap();
		if (exist) {
			long userId = userAuthService.getUserId(body.getUsername());
			res.put("userId", userId);
		} else
			throw new BusinessException("Username or password is not correct!", "/welcome", HttpStatus.UNAUTHORIZED.name(),
					HttpStatus.UNAUTHORIZED.value());

        return ResponseEntity.ok(res);
    }

}
