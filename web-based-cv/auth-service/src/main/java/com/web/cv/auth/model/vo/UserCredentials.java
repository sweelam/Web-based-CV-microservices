package com.web.cv.auth.model.vo;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserCredentials {
    @NotNull(message="username is required")
    private String username;
    @NotNull(message="password is required")
    private String password;
}