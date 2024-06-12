package com.inviguardprojectbe.services;


import com.inviguardprojectbe.models.LoginDto;
import com.inviguardprojectbe.models.RegisterDto;
import com.inviguardprojectbe.models.entities.User;
import com.inviguardprojectbe.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(LoginDto loginDto) {

        User user = userRepository.findOneByEmail(loginDto.getEmail());

        if(user == null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "invalid credentials");
        }

        boolean isPassCorrect;
        try {
            isPassCorrect = hashPassword(loginDto.getPassword()).equals(user.getPasswordHash());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        if(!isPassCorrect){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "invalid credentials");
        }

        return user;
    }

    public User register(RegisterDto registerDto) {

        String passwordHash;
        try {
            passwordHash = hashPassword(registerDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        User user = new User();
        user.setFullName(registerDto.getFullName());
        user.setEmail(registerDto.getEmail());
        user.setPhoneNumber(registerDto.getPhoneNumber());
        user.setPasswordHash(passwordHash);
        user.setOrganization(false);

        userRepository.save(user);

        return user;
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest m= MessageDigest.getInstance("MD5");
        m.update(password.getBytes(),0,password.length());
        return new BigInteger(1,m.digest()).toString(16);
    }
}
