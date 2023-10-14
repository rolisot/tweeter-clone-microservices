package com.lisot.identityserver.service;

import com.lisot.identityserver.config.CustomUserDetails;
import com.lisot.identityserver.entity.UserCredential;
import com.lisot.identityserver.repository.UserCredencialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserCredencialRepository userCredencialRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserCredential> credential = userCredencialRepository.findByUserName(username);
        return credential.map(CustomUserDetails::new).orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }
}
