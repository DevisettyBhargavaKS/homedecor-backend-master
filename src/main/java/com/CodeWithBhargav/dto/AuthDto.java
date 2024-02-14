package com.CodeWithBhargav.dto;

import com.CodeWithBhargav.model.AppUser;
import com.CodeWithBhargav.request.RegisterRequest;
import com.CodeWithBhargav.response.AuthResponse;
import org.springframework.stereotype.Component;

@Component
public class AuthDto {

    public AppUser mapToAppUser(RegisterRequest user) {
        AppUser appUser = new AppUser();
        appUser.setUsername(user.getUsername());
        appUser.setName(user.getName());
        appUser.setPassword(user.getPassword());
        return appUser;
    }


    public AuthResponse mapToAuthResponse(AppUser appUser) {
        AuthResponse authResponse = new AuthResponse();
        authResponse.setId(appUser.getId());
        authResponse.setName(appUser.getName());
        authResponse.setUsername(appUser.getUsername());
        authResponse.setRole(appUser.getRoles().getName());
        return authResponse;
    }
}
