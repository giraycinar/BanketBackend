package com.bilgeadam.banket.service;


import com.bilgeadam.banket.dto.request.SaveQuestionRequestDto;
import com.bilgeadam.banket.dto.request.UpdateQuestionRequestDto;
import com.bilgeadam.banket.entity.Question;
import com.bilgeadam.banket.exception.BanketApplicationException;
import com.bilgeadam.banket.exception.ErrorType;


import com.bilgeadam.banket.mapper.IQuestionMapper;
import com.bilgeadam.banket.repository.QuestionRepository;
import com.bilgeadam.banket.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class QuestionService extends ServiceManager<Question, String> {
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        super(questionRepository);
        this.questionRepository = questionRepository;
    }

    public String saveQuestion(SaveQuestionRequestDto dto) {

        Question question = IQuestionMapper.INSTANCE.saveQuestionDtoToQuestion(dto);
        save(question);
        return "Save question successfully.";
    }

    public Question getQuestion(UUID uuid) {
        Optional<Question> optionalQuestion = questionRepository.findByUuid(uuid);
        if (optionalQuestion.isEmpty()) {
            throw new BanketApplicationException(ErrorType.QUESTION_NOT_FOUND);
        }
        return optionalQuestion.get();
    }

    public String updateQuestion(UpdateQuestionRequestDto dto) {
        Optional<Question> optionalQuestion = questionRepository.findByUuid(dto.getUuid());
        if (optionalQuestion.isEmpty()) {
            throw new BanketApplicationException(ErrorType.QUESTION_NOT_FOUND);
        }
        if (dto.getDescription() != null) {
            optionalQuestion.get().setDescription(dto.getDescription());
        }
        if (dto.getQuestionType() != null) {
            optionalQuestion.get().setQuestionType(dto.getQuestionType());
        }
        if (dto.getOptions() != null) {
            optionalQuestion.get().setOptions(dto.getOptions());
        }
        update(optionalQuestion.get());
        return "Update question successfully.";
    }


    public String deleteQuestion(UUID uuid) {
        Optional<Question> optionalQuestion = questionRepository.findByUuid(uuid);
        if (optionalQuestion.isEmpty()) {
            throw new BanketApplicationException(ErrorType.QUESTION_NOT_FOUND);
        }
        if (optionalQuestion.get().isDeleted()) {
            throw new BanketApplicationException(ErrorType.QUESTION_ALREADY_DELETED);
        }
        softDelete(optionalQuestion.get());
        return "Delete question successfully";
    }

    public String removeQuestion(UUID uuid) {
        Optional<Question> optionalQuestion = questionRepository.findByUuid(uuid);
        if (optionalQuestion.isEmpty()) {
            throw new BanketApplicationException(ErrorType.QUESTION_NOT_FOUND);
        }
        hardDelete(optionalQuestion.get());
        return "Remove question successfully";
    }

}
