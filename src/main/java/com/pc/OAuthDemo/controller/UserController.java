package com.pc.OAuthDemo.controller;

import com.pc.OAuthDemo.dto.UserDto;
import com.pc.OAuthDemo.model.AuthUser;
import com.pc.OAuthDemo.repository.UserRepo;
import com.pc.OAuthDemo.repository.UserRoleRepository;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/oauth/users")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserRoleRepository userRoleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthUser register(@RequestBody UserDto userDto){
        AuthUser authUser = new ObjectMapper().convertValue(userDto, AuthUser.class);
        authUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        authUser.setRoles(Collections.singletonList(userRoleRepo.findByRoleNameContaining("USER")));

        Optional<AuthUser> optUser = userRepo.findByUserNameOrEmail(userDto.getUserName(),userDto.getEmail());
        if(!optUser.isPresent())
            return userRepo.save(authUser);
        throw new RuntimeException("user already exists");
    }
}
