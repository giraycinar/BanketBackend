package com.bilgeadam.banket.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UpdateStudentRequestDto {

    private UUID uuid;

    private String name;

    private String surname;

    private String email;

    private String group;
}
