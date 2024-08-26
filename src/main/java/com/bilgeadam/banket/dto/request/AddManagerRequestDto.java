package com.bilgeadam.banket.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AddManagerRequestDto {

    @Indexed(unique = true)
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

}
