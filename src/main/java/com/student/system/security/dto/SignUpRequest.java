package com.student.system.security.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
}
