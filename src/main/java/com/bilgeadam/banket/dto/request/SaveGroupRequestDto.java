package com.bilgeadam.banket.dto.request;

import com.bilgeadam.banket.entity.Student;
import lombok.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SaveGroupRequestDto {
    private String name;
    private List<Student> students;
}
