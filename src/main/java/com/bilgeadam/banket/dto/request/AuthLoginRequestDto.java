package com.bilgeadam.banket.dto.request;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AuthLoginRequestDto {
    @Email
    private String email;

    private String password;
}
