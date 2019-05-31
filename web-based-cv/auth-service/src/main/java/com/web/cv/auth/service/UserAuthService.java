package com.web.cv.auth.service;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.web.cv.auth.model.entity.UserEntity;
import com.web.cv.auth.model.repository.UserRepo;
import com.web.cv.auth.model.vo.UserCredentials;
import com.web.cv.auth.model.vo.UserPrincipale;
import com.web.utils.common.BusinessException;

public interface UserAuthService {
	public UserDetailsService getUserDetails();
	public Map<String, Object> isUserExist(UserCredentials userCredentials) throws BusinessException;
}
