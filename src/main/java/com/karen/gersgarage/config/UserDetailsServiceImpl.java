package com.karen.gersgarage.config;


import com.karen.gersgarage.model.Client;
import com.karen.gersgarage.services.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClientRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Client> user = Optional.ofNullable(userRepository.findByEmail(username));

        return user.map(UserUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("User not found"));

    }

}