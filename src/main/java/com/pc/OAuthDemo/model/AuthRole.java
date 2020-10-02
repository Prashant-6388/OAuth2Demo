package com.pc.OAuthDemo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="auth_role")
public class AuthRole {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
