package com.student.system.security.service.impl;

import com.student.system.security.dto.AuthenticationResponse;
import com.student.system.security.dto.SignInRequest;
import com.student.system.security.dto.SignUpRequest;
import com.student.system.security.dto.TokenRequest;
import com.student.system.security.entity.User;
import com.student.system.security.enums.Role;
import com.student.system.security.repository.UserRepository;
import com.student.system.security.service.JwtService;
import com.student.system.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not Found"));
    }

    @Override
    public User signUp(SignUpRequest signUpDto) {
        User user = new User();
        user.setFirstname(signUpDto.getFirstname());
        user.setLastname(signUpDto.getLastname());
        user.setEmail(signUpDto.getEmail());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        return repository.save(user);
    }

    @Override
    public AuthenticationResponse signIn(SignInRequest loginDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        var user = repository.findByEmail(loginDto.getEmail()).orElseThrow();
        String token = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        AuthenticationResponse response = new AuthenticationResponse();
        response.setAccessToken(token);
        response.setRefreshToken(refreshToken);
        return response;
    }

    @Override
    public AuthenticationResponse refreshToken(TokenRequest request) {
        String email = jwtService.extractUserName(request.getToken());
        User user = repository.findByEmail(email).orElseThrow();
        if (jwtService.validateToken(request.getToken(), user)) {
            var jwt = jwtService.generateToken(user);
            AuthenticationResponse response = new AuthenticationResponse();
            response.setAccessToken(jwt);
            response.setRefreshToken(request.getToken());
            return response;
        }
        return null;
    }
}
