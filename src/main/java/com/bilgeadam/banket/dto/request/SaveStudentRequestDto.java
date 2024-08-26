package com.bilgeadam.banket.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SaveStudentRequestDto {

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String group;
}
