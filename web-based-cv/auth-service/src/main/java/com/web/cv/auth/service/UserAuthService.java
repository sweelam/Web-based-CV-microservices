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

@Service
public class UserAuthService {
	private final UserRepo userRepo;

	@Autowired
	public UserAuthService(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	public UserDetailsService getUserDetails() {

		UserDetailsService userDetailsService = (name) -> {
			Optional<UserEntity> user = this.userRepo.findByUsername(name);
			return new UserPrincipale(user.get());
		};

		return userDetailsService;
	}

	public Map<String, Object> isUserExist(UserCredentials userCredentials) throws BusinessException {
		UserPrincipale userPrincipale = null;
		Map<String, Object> res = new TreeMap<>();

		try {

			userPrincipale = (UserPrincipale) this.getUserDetails().loadUserByUsername(userCredentials.getUsername());

		} catch (Exception ex) {
			throw new BusinessException("User not found", "/welcome", 
					HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value());
		}

		boolean checkpw = BCrypt.checkpw(userCredentials.getPassword(), userPrincipale.getPassword());

		if (checkpw) {
			res.put("res", "/login");
			res.put("userId", this.userRepo.findByUsername(userCredentials.getUsername()).get().getUserId());
		} else
			throw new BusinessException("Username or password is not correct!", "/welcome",
					HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value());

		return res;
	}
}
