package com.web.cv.auth.service.impl;

import com.web.cv.auth.model.entity.UserEntity;
import com.web.cv.auth.model.repository.UserRepo;
import com.web.cv.auth.model.vo.UserCredentials;
import com.web.cv.auth.model.vo.UserPrincipale;
import com.web.cv.auth.service.UserAuthService;
import com.web.utils.common.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userAuthImpl")
public class UserAuthImpl implements UserAuthService {
    private final UserRepo userRepo;

    public UserAuthImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public UserDetailsService getUserDetails() {
        return (name) -> {
            Optional<UserEntity> user = this.userRepo.findByUsername(name);
            return user.map(UserPrincipale::new).orElse(null);
        };
    }

    /**
     * @author sweelam
     * @param userCredentials
     * @return
     * @throws BusinessException
     */
    public boolean isUserExist(UserCredentials userCredentials) throws BusinessException {
        UserPrincipale userPrincipale = null;
        UserDetailsService userDetails = this.getUserDetails();
        if (null != userDetails)
            userPrincipale = (UserPrincipale) userDetails.loadUserByUsername(userCredentials.getUsername());
        else
            throw new BusinessException("User not found", "/welcome", HttpStatus.NOT_FOUND.name(),
                    HttpStatus.NOT_FOUND.value());

        return BCrypt.checkpw(userCredentials.getPassword(), userPrincipale.getPassword());
    }

    @Override
    public long getUserId(String username) {
        Optional<UserEntity> user = this.userRepo.findByUsername(username);
        return user.map(UserEntity::getUserId).orElse(-1L);
    }
}
