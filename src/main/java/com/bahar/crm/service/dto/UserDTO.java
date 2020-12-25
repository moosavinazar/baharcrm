package com.bahar.crm.service.dto;

import com.bahar.crm.domain.Role;

import java.time.Instant;
import java.util.Set;

public class UserDTO {

    private Long id;

    private String username;

    private String password;

    private String email;

    private String cellPhone;

    private Instant expired;

    private boolean locked;

    private boolean credential;

    private boolean enabled;

    private Set<Role> roles;

    public UserDTO() {
    }

    public UserDTO(Long id,
                   String username,
                   String password,
                   String email,
                   String cellPhone,
                   Instant expired,
                   boolean locked,
                   boolean credential,
                   boolean enabled,
                   Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.cellPhone = cellPhone;
        this.expired = expired;
        this.locked = locked;
        this.credential = credential;
        this.enabled = enabled;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public Instant getExpired() {
        return expired;
    }

    public void setExpired(Instant expired) {
        this.expired = expired;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isCredential() {
        return credential;
    }

    public void setCredential(boolean credential) {
        this.credential = credential;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}
