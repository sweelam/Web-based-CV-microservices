package com.web.cv.auth.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import com.web.cv.auth.model.vo.UserCredentials;
import com.web.cv.auth.service.UserAuthService;
import com.web.cv.logging.Loggable;
import com.web.utils.common.BusinessException;

@RestController
@CrossOrigin
@RequestMapping("/user")
@RefreshScope
public class AuthController {
	private UserAuthService userAuthService;

	@Value("${name}")
	private String name;

	public AuthController(UserAuthService userAuthService) {
		this.userAuthService = userAuthService;
	}

	@GetMapping("authTest")
	public String getAuth() {
		return name;
	}

	@PostMapping(value = "/login-form", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@Loggable
	public ResponseEntity<Map<String, Object>> login(@RequestBody @Valid UserCredentials body, Errors errors)
			throws BusinessException {
		if (errors.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

		Map<String, Object> res = userAuthService.isUserExist(body);

		return !res.get("res").toString().equals("/welcome") ? new ResponseEntity<>(res, HttpStatus.OK)
				: new ResponseEntity<>(res, HttpStatus.NOT_FOUND);

	}

}
