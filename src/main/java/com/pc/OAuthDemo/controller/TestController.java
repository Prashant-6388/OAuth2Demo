package com.pc.OAuthDemo.controller;

import com.pc.OAuthDemo.dto.UserDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {
    @RequestMapping("/api/users/")
    public @ResponseBody List<UserDto> profiles()
    {
        //Build some dummy data to return for testing
        UserDto user1 = new UserDto();
        user1.setUserName("Test User 1");
        user1.setEmail("TestUser1@test.com");
        user1.setMobile("123456789");
        user1.setPassword("testUser1");

        UserDto user2 = new UserDto();
        user2.setUserName("Test User 2");
        user2.setEmail("TestUser2@test.com");
        user2.setMobile("1234567890");
        user2.setPassword("testUser2");


        List<UserDto> usersList = new ArrayList();
        usersList.add(user1);
        usersList.add(user2);

        return usersList;
    }
}
