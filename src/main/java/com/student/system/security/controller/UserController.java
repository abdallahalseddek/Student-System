package com.student.system.security.controller;

import com.student.system.security.dto.AuthenticationResponse;
import com.student.system.security.dto.SignInRequest;
import com.student.system.security.dto.SignUpRequest;
import com.student.system.security.dto.TokenRequest;
import com.student.system.security.entity.User;
import com.student.system.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody SignUpRequest dto) {
        return ResponseEntity.ok(userService.signUp(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> signIn(@RequestBody SignInRequest dto) {
        return ResponseEntity.ok(userService.signIn(dto));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponse> refresh(@RequestBody TokenRequest dto) {
        return ResponseEntity.ok(userService.refreshToken(dto));
    }

}
