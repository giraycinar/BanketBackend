package com.bilgeadam.banket.repository;

import com.bilgeadam.banket.entity.SendedSurvey;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SendedSurveyRepository extends MongoRepository<SendedSurvey, String> {
    List<SendedSurvey> findAllByGroupUuid(UUID groupUuid);

    List<SendedSurvey> findAllBySurveyUuid(UUID surveyUuid);
}
