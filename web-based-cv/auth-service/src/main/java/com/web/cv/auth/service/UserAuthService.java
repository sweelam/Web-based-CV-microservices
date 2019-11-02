package com.web.cv.auth.service;

import com.web.cv.auth.model.vo.UserCredentials;
import com.web.utils.common.BusinessException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserAuthService {
	UserDetailsService getUserDetails();
	boolean isUserExist(UserCredentials userCredentials) throws BusinessException;
	long getUserId(String username);
}
