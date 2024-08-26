package com.bilgeadam.banket.service;
import com.bilgeadam.banket.dto.request.SaveAnswerRequestDto;
import com.bilgeadam.banket.dto.request.UpdateAnswerRequestDto;
import com.bilgeadam.banket.entity.Answer;
import com.bilgeadam.banket.exception.BanketApplicationException;
import com.bilgeadam.banket.exception.ErrorType;
import com.bilgeadam.banket.mapper.IAnswerMapper;
import com.bilgeadam.banket.repository.AnswerRepository;
import com.bilgeadam.banket.utility.ServiceManager;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public class AnswerService extends ServiceManager<Answer, String> {
    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        super(answerRepository);
        this.answerRepository = answerRepository;
    }

    public String saveAnswer(SaveAnswerRequestDto dto) {
        if (answerRepository.findByQuestionUuid(dto.getQuestionUuid())) {
            throw new BanketApplicationException(ErrorType.ANSWER_ALREADY_EXISTS);
        }
        Answer answer = IAnswerMapper.INSTANCE.saveAnswerDtoToAnswer(dto);
        save(answer);
        return "Save answer successfully.";
    }
    public Answer findByUuid(UUID uuid) {
        Optional<Answer> answerOptional = answerRepository.findByUuid(uuid);
        if (answerOptional.isPresent()) {
            return answerOptional.get();
        } else {
            throw new BanketApplicationException(ErrorType.ANSWER_NOT_FOUND);
        }
    }
    public String deleteAnswer(UUID uuid) {
        Optional<Answer> optionalAnswer = answerRepository.findByUuid(uuid);
        if (optionalAnswer.isEmpty()){
            throw new BanketApplicationException(ErrorType.ANSWER_NOT_FOUND);
        }
        if (optionalAnswer.get().isDeleted()){
            throw new BanketApplicationException(ErrorType.ANSWER_ALREADY_DELETED);
        }
        softDelete(optionalAnswer.get());
        return "Delete answer successfully.";   }


    public String removeAnswer(UUID uuid) {
        Optional<Answer> optionalAnswer = answerRepository.findByUuid(uuid);
        if (optionalAnswer.isEmpty()){
            throw new BanketApplicationException(ErrorType.ANSWER_NOT_FOUND);
        }
        hardDelete(optionalAnswer.get());
        return "Remove answer successfully.";
    }

    public String updateAnswer(UUID answerUuid, UpdateAnswerRequestDto dto) {
        Optional<Answer> existingAnswerOptional = answerRepository.findByUuid(answerUuid);
        if (existingAnswerOptional.isPresent()) {
            Answer existingAnswer = existingAnswerOptional.get();
            existingAnswer.setAnswerText(dto.getAnswerText());
            existingAnswer.setQuestionUuid(dto.getQuestionUuid());
            existingAnswer.setFilledSurveyUuid(dto.getFilledSurveyUuid());

            answerRepository.save(existingAnswer);
            return "Update answer successfully.";
        } else {
            throw new BanketApplicationException(ErrorType.ANSWER_NOT_FOUND);
        }
    }

}
