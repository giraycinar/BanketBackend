package com.bilgeadam.banket.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Document
public class Group extends BaseEntity{
    private String name;
    private List<Student> students;
}
