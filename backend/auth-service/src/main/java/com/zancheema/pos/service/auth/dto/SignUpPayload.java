package com.zancheema.pos.service.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpPayload {
    private @NotBlank String username;
    private @NotBlank @Email String email;
    private @NotBlank String password;
}
