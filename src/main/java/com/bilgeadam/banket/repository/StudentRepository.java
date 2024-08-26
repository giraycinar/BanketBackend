package com.bilgeadam.banket.repository;

import com.bilgeadam.banket.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {

    Boolean existsByEmail(String email);

    Optional<Student> findByUuid(UUID uuid);

    List<Student> findByGroup(String group);
}
