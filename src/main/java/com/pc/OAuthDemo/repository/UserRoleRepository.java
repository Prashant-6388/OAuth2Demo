package com.pc.OAuthDemo.repository;

import com.pc.OAuthDemo.model.AuthRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<AuthRole, Long> {
    AuthRole findByRoleNameContaining(String roleName);
}
