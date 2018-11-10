package com.web.cv.auth.service;

import com.web.cv.auth.common.ApplicationErrorResponse;
import com.web.cv.auth.model.entity.UserEntity;
import com.web.cv.auth.model.repository.UserRepo;
import com.web.cv.auth.model.vo.UserCredentials;
import com.web.cv.auth.model.vo.UserPrincipale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;

@Service
public class UserAuthService {
    @Autowired
    private final UserRepo userRepo;

    public UserAuthService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public UserDetailsService getUserDetails() {

        UserDetailsService userDetailsService = (name) -> {
            UserEntity user = this.userRepo.findByUsername(name);

            if(null == user)
                throw new UsernameNotFoundException("Cannot find user with username : " + name);

            return new UserPrincipale(user);
        };

        return userDetailsService;
    }

    public Map<String, Object> isUserExist(UserCredentials userCredentials) {
        UserPrincipale userPrincipale = null;
        Map<String, Object> res = new TreeMap<>();


        try {
            userPrincipale = (UserPrincipale) this.getUserDetails().loadUserByUsername(userCredentials.getUsername());
        } catch(UsernameNotFoundException ex) {
            res.put("res", "/welcome");
            res.put("meesage", "User not found");
            return res;
        }

        boolean checkpw = BCrypt.checkpw(userCredentials.getPassword(), userPrincipale.getPassword());

        if (checkpw) {
            res.put("res", "/login");
            res.put("userId", this.userRepo.findByUsername(userCredentials.getUsername()).getUserId());
        } else {
            res.put("res", "/welcome");
            res.put("meesage", "Username or password is not correct!");
        }

        return res;
    }
}
