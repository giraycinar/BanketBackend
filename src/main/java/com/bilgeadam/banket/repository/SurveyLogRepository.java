package com.bilgeadam.banket.repository;

import com.bilgeadam.banket.entity.SurveyLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyLogRepository extends MongoRepository<SurveyLog, String> {
}
