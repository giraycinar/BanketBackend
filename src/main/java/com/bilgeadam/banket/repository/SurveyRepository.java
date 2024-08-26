package com.bilgeadam.banket.repository;

import com.bilgeadam.banket.entity.Survey;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SurveyRepository extends MongoRepository<Survey,String> {

    Boolean existsByName(String name);

    Optional<Survey> findByUuid(UUID uuid);
}
