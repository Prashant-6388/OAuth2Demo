package com.pc.OAuthDemo.repository;

import com.pc.OAuthDemo.model.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<AuthUser, Long> {

    Optional<AuthUser> findByUserName(String name);

    Optional<AuthUser> findByUserNameOrEmail(String name, String email);
}
