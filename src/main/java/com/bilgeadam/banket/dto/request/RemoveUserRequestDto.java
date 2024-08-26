package com.bilgeadam.banket.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RemoveUserRequestDto {

    @NotBlank
    private String token;

    @NotBlank
    private UUID uuid;

}
