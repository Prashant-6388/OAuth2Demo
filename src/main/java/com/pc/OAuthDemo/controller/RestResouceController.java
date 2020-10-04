package com.pc.OAuthDemo.controller;

import com.pc.OAuthDemo.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RestResouceController {
    @RequestMapping("/api/users/me")
    public ResponseEntity<UserDto> profile()
    {
        //Build some dummy data to return for testing
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = user.getUsername() + "@pcworld.com";

        UserDto profile = new UserDto();
        profile.setUserName(user.getUsername());
        profile.setEmail(email);

        return ResponseEntity.ok(profile);
    }
}
