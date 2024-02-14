package com.CodeWithBhargav.service;

import com.CodeWithBhargav.dto.AuthDto;
import com.CodeWithBhargav.exception.common.InvalidUserException;
import com.CodeWithBhargav.exception.common.ResourceNotFoundException;
import com.CodeWithBhargav.model.AppUser;
import com.CodeWithBhargav.model.Role;
import com.CodeWithBhargav.repository.RoleRepository;
import com.CodeWithBhargav.repository.UserRepository;
import com.CodeWithBhargav.request.LoginRequest;
import com.CodeWithBhargav.request.RegisterRequest;
import com.CodeWithBhargav.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private AuthDto authDto;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthResponse register(RegisterRequest registerRequest) {
        AppUser appUser = authDto.mapToAppUser(registerRequest);
        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
        if ("VENDOR".equals(registerRequest.getRole())){
            appUser.setRoles(roleRepository.findByName(Role.VENDOR));

        }else {
            appUser.setRoles(roleRepository.findByName(Role.USER));
        }
        appUser = userRepository.save(appUser);
        return authDto.mapToAuthResponse(appUser);
    }

    public AuthResponse login(LoginRequest loginRequest) {
        AppUser appUser = userRepository
                .findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new InvalidUserException("Invalid user credentials"));

        if (!bCryptPasswordEncoder.matches(loginRequest.getPassword(), appUser.getPassword())) {
            throw new InvalidUserException("Invalid password");
        }

        return authDto.mapToAuthResponse(appUser);
    }

    public List<AppUser> findAll() {
        return userRepository.findAll();
    }

    public AppUser findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("user", "id", id));
    }
}
