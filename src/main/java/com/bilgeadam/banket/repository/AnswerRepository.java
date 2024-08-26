package com.bilgeadam.banket.repository;
import com.bilgeadam.banket.entity.Answer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AnswerRepository extends MongoRepository<Answer,String> {
    Optional<Answer> findByUuid(UUID uuid);
   Boolean findByQuestionUuid(String questionUuid);

}
