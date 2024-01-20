package com.student.system.security.service;

import com.student.system.security.dto.AuthenticationResponse;
import com.student.system.security.dto.SignInRequest;
import com.student.system.security.dto.SignUpRequest;
import com.student.system.security.dto.TokenRequest;
import com.student.system.security.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    UserDetails loadUserByUsername(String username);
    User signUp(SignUpRequest signUpDto);
    AuthenticationResponse signIn(SignInRequest loginDto);
    AuthenticationResponse refreshToken(TokenRequest request);
}
