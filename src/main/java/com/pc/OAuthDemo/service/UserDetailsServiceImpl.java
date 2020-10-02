package com.pc.OAuthDemo.service;


import com.pc.OAuthDemo.model.AuthUser;
import com.pc.OAuthDemo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AuthUser> optUser = userRepo.findByUserName(username);
        if(optUser.isPresent()){
            AuthUser authUser = optUser.get();

            List<SimpleGrantedAuthority> roles = authUser.getRoles()
                    .stream()
                    .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                    .collect(Collectors.toList());
            return new User(authUser.getUserName(), authUser.getPassword(), roles);
        }

        throw new UsernameNotFoundException("Unable to find user:"+username);
    }
}
