package com.bilgeadam.banket.repository;

import com.bilgeadam.banket.entity.Answer;
import com.bilgeadam.banket.entity.FilledSurvey;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FilledSurveyRepository extends MongoRepository<FilledSurvey, String> {

    Optional<FilledSurvey> findByUuid(UUID uuid);
    Optional<FilledSurvey> findBySurveyId(String surveyId);

    Boolean getFilledSurveyByStudentEmailAndSurveyId(String surveyId, String StudentEmail);
}
