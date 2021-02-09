package com.web.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class UserCredentials {
    @NotNull(message="username is required")
    private String username;
    @NotNull(message="password is required")
    private String password;
}