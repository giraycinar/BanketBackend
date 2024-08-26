package com.bilgeadam.banket.dto.request;

import com.bilgeadam.banket.entity.Student;
import lombok.*;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UpdateGroupRequestDto {
    private UUID uuid;
    private String name;
    private List<Student> students;
}
