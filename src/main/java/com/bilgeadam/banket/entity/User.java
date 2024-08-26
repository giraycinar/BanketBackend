package com.bilgeadam.banket.entity;

import com.bilgeadam.banket.entity.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@Document(collection = "users")
public class User extends BaseEntity {

    @Indexed(unique = true)
    private String email;

    private String password;

    private String name;

    private String surname;

    private List<ERole> roles;

}
