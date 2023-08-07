package com.karen.gersgarage.config;


import com.karen.gersgarage.model.Client;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserUserDetails implements UserDetails {

    private String name;
    private String email;
    private String password;
    private int idClients;

    private List<GrantedAuthority> authorities;

    public UserUserDetails(Client user) {
        this.name = user.getFirstName();
        this.idClients = user.getIdClients();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.authorities = Arrays.stream(user.getProfile().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getEmail() {
        return email;
    }

    public int getIdClients() {
        return idClients;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getName() {
        return name;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}