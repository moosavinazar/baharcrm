package com.bahar.crm.service.dto;

import com.bahar.crm.domain.Authority;

import java.util.HashSet;
import java.util.Set;

public class RoleDTO {

    private Long id;

    private String name;

    private Set<Authority> authorities = new HashSet<>();

    public RoleDTO() {
    }

    public RoleDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

}
