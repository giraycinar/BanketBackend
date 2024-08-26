package com.bilgeadam.banket.entity;


import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Document
public class Student extends BaseEntity {

    private String name;

    private String surname;

    @Indexed(unique = true)
    private String email;

    private String group;

}
