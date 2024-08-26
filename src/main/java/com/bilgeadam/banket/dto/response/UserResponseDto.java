package com.bilgeadam.banket.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserResponseDto {
    private String email;

    private String password;

    private String name;

    private String surname;
    // Volkan: get methodlarındaki kurgunun planlamasıyla birlikte doldurulacak yada silinecek.

}
