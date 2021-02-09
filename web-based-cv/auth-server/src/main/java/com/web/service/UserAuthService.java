package com.web.service;

import com.web.model.UserEntity;
import com.web.model.UserPrincipale;
import com.web.repositories.UserRepo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthService implements UserDetailsService {
    private final UserRepo userRepo;

    public UserAuthService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetailsService userDetails = (name) -> {
            Optional<UserEntity> user = this.userRepo.findByUsername(name);
            return user.map(UserPrincipale::new).orElse(null);
        };

        if (null == userDetails)
            throw new UsernameNotFoundException("User not found");

        UserPrincipale userPrincipale = (UserPrincipale) userDetails.loadUserByUsername(username);
        return User.withUsername(userPrincipale.getUsername())
                .password(userPrincipale.getPassword())
                .authorities("READ_PRIVILEGE", "WRITE_PRIVILEGE")
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
