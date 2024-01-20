package com.student.system.security.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequest {
    private String email;
    private String password;
}
