package com.bilgeadam.banket.repository;

import com.bilgeadam.banket.entity.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {
    Optional<Question> findByUuid(UUID uuid);





}