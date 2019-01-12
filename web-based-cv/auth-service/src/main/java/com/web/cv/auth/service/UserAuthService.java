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

		return (name) -> {
			Optional<UserEntity> user = this.userRepo.findByUsername(name);
			return user.isPresent() ? new UserPrincipale(user.get()) : null;
		};

	}

	/**
	 * @author sweelam
	 * @param userCredentials
	 * @return
	 * @throws BusinessException
	 */
	public Map<String, Object> isUserExist(UserCredentials userCredentials) throws BusinessException {
		UserPrincipale userPrincipale = null;
		Map<String, Object> res = new TreeMap<>();

		UserDetailsService userDetails = this.getUserDetails();

		if (null != userDetails)
			userPrincipale = (UserPrincipale) userDetails.loadUserByUsername(userCredentials.getUsername());
		else
			throw new BusinessException("User not found", "/welcome", HttpStatus.NOT_FOUND.name(),
					HttpStatus.NOT_FOUND.value());

		boolean checkpw = BCrypt.checkpw(userCredentials.getPassword(), userPrincipale.getPassword());

		
		if (checkpw) {
			res.put("res", "/login");
			Optional<UserEntity> user = this.userRepo.findByUsername(userCredentials.getUsername());
			res.put("userId", user.isPresent() ? user.get().getUserId() : -1);
		} else
			throw new BusinessException("Username or password is not correct!", "/welcome", HttpStatus.NOT_FOUND.name(),
					HttpStatus.NOT_FOUND.value());

		return res;
	}
}
