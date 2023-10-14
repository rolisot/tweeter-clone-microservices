package com.lisot.identityserver.service;

import com.lisot.identityserver.entity.UserCredential;
import com.lisot.identityserver.repository.UserCredencialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserCredencialRepository userCredencialRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public String saveUser(UserCredential userCredential){
        userCredential.setPassword(passwordEncoder.encode(userCredential.getPassword()));
        userCredencialRepository.save(userCredential);
        return "User added successfully";
    }

    public String generateToken(String userName){
        return jwtService.generateToken(userName);
    }

    public void validateToken(String token){
        jwtService.validateToken(token);
    }
}
