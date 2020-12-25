package com.bahar.crm.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import javax.persistence.*;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.*;

@Entity
@Table(name = "sar_user")
public class User implements UserDetails, OAuth2User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @SequenceGenerator(name = "user_generator", sequenceName = "user_seq", initialValue = 5)
    private Long id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "cell_phone", unique = true)
    private String cellPhone;

    @Column(name = "expired", nullable = false)
    private Instant expired = ZonedDateTime.now().plusYears(1L).toInstant();

    @Column(name = "locked", nullable = false)
    private boolean locked;

    @Column(name = "credential", nullable = false)
    private boolean credential;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(Long id,
                String username,
                String password,
                String email,
                String cellPhone,
                Instant expired,
                boolean locked,
                boolean credential,
                boolean enabled) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.cellPhone = cellPhone;
        this.expired = expired;
        this.locked = locked;
        this.credential = credential;
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return null;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return null;
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

    @Override
    public boolean isAccountNonExpired() {
        return (expired.isBefore(ZonedDateTime.now().toInstant()));
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isLocked();
    }

    public boolean isCredential() {
        return credential;
    }

    public void setCredential(boolean credential) {
        this.credential = credential;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredential();
    }

    @Override
    public boolean isEnabled() {
        return false;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Role role: roles
             ) {
            grantedAuthorities.addAll(role.getAuthorities());
        }
        return grantedAuthorities;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return new HashMap<>();
    }
}
