package com.bilgeadam.banket.service;

import com.bilgeadam.banket.dto.request.AddQuestionToSurveyDto;
import com.bilgeadam.banket.dto.request.SaveSurveyRequestDto;
import com.bilgeadam.banket.dto.request.UpdateSurveyRequestDto;
import com.bilgeadam.banket.entity.Question;
import com.bilgeadam.banket.entity.Survey;
import com.bilgeadam.banket.exception.BanketApplicationException;
import com.bilgeadam.banket.exception.ErrorType;
import com.bilgeadam.banket.mapper.ISurveyMapper;
import com.bilgeadam.banket.repository.QuestionRepository;
import com.bilgeadam.banket.repository.SurveyRepository;
import com.bilgeadam.banket.utility.ServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class SurveyService extends ServiceManager<Survey,String> {
    @Autowired
    private final SurveyRepository surveyRepository;
    private final QuestionRepository questionRepository;
    private final ISurveyMapper surveyMapper;

    public SurveyService(SurveyRepository surveyRepository, QuestionRepository questionRepository, ISurveyMapper surveyMapper) {
        super(surveyRepository);
        this.surveyRepository = surveyRepository;
        this.questionRepository = questionRepository;
        this.surveyMapper = surveyMapper;
    }
    public String saveSurvey(SaveSurveyRequestDto dto) {
        if (surveyRepository.existsByName(dto.getName())) {
            throw new BanketApplicationException(ErrorType.SURVEY_ALREADY_EXISTS);
        }
        List<AddQuestionToSurveyDto> questions = new ArrayList<>();
        for (AddQuestionToSurveyDto questionDto : dto.getQuestions()) {
            AddQuestionToSurveyDto addQuestionDto = new AddQuestionToSurveyDto();
            addQuestionDto.setQuestionId(questionDto.getQuestionId());
            addQuestionDto.setRequired(questionDto.isRequired());
            questions.add(addQuestionDto);
        }
        dto.setQuestions(questions);
        Survey survey = Survey.builder()
                .name(dto.getName())
                .questions(dto.getQuestions())
                .build();
        save(survey);
        return "Save survey successfully.";
    }

    public String updateSurvey(UpdateSurveyRequestDto dto) {
        Optional<Survey> optionalSurvey = surveyRepository.findByUuid(dto.getUuid());
        if (optionalSurvey.isEmpty()) {
            throw new BanketApplicationException(ErrorType.SURVEY_NOT_FOUND);
        }

        Survey survey = optionalSurvey.get();

        if (dto.getName() != null) {
            if (surveyRepository.existsByName(dto.getName()) && !survey.getName().equals(dto.getName())) {
                throw new BanketApplicationException(ErrorType.SURVEY_ALREADY_EXISTS);
            }
            survey.setName(dto.getName());
        }

        if (dto.getQuestions() != null) {
            List<AddQuestionToSurveyDto> existingQuestions = survey.getQuestions();


            for (AddQuestionToSurveyDto newQuestion : dto.getQuestions()) {
                Optional<AddQuestionToSurveyDto> existingQuestion = existingQuestions.stream()
                        .filter(q -> q.getQuestionId().equals(newQuestion.getQuestionId()))
                        .findFirst();

                if (existingQuestion.isPresent()) {
                    existingQuestion.get().setRequired(newQuestion.isRequired());
                } else {
                    existingQuestions.add(newQuestion);
                }
            }

            survey.setQuestions(existingQuestions);
        }

        update(survey);
        return "Update survey successfully.";
    }


    public Survey getSurvey(UUID uuid) {
        Optional<Survey> optionalSurvey = surveyRepository.findByUuid(uuid);
        if (optionalSurvey.isEmpty()){
            throw new BanketApplicationException(ErrorType.SURVEY_NOT_FOUND);
        }
        return optionalSurvey.get();
    }


    public String deleteSurvey(UUID uuid) {
        Optional<Survey> optionalSurvey=surveyRepository.findByUuid(uuid);
        if (optionalSurvey.isEmpty()){
            throw new BanketApplicationException(ErrorType.SURVEY_NOT_FOUND);
        }
//        if (optionalSurvey.get().isDeleted()) {
//            throw new BanketApplicationException(ErrorType.SURVEY_ALREADY_DELETED);
//        }
        hardDelete(optionalSurvey.get());
        return "Delete survey successfully.";
    }

    public String removeSurvey(UUID uuid) {
        Optional<Survey> optionalSurvey=surveyRepository.findByUuid(uuid);
        if (optionalSurvey.isEmpty()){
            throw new BanketApplicationException(ErrorType.SURVEY_NOT_FOUND);
        }
        hardDelete(optionalSurvey.get());
        return "Remove survey successfully.";
    }

//    public List<Question> getQuestionsBySurvey(UUID surveyUuid) {
//        Optional<Survey> optionalSurvey = surveyRepository.findByUuid(surveyUuid);
//        if (optionalSurvey.isEmpty()) {
//
//            throw new BanketApplicationException(ErrorType.SURVEY_NOT_FOUND);
//        }
//
//        Survey survey = optionalSurvey.get();
//        List<String> questionIds = survey.getQuestions().stream()
//                .map(AddQuestionToSurveyDto::getQuestionId)
//                .collect(Collectors.toList());
//        return questionRepository.findAllById(questionIds);
//    }

    public List<Question> getQuestionsBySurvey(UUID surveyUuid) {
        Optional<Survey> optionalSurvey = surveyRepository.findByUuid(surveyUuid);
        if (optionalSurvey.isEmpty()) {
            throw new BanketApplicationException(ErrorType.SURVEY_NOT_FOUND);
        }

        Survey survey = optionalSurvey.get();
        List<AddQuestionToSurveyDto> questionDtos = survey.getQuestions();
        List<String> questionIds = questionDtos.stream()
                .map(AddQuestionToSurveyDto::getQuestionId)
                .collect(Collectors.toList());

        // QuestionRepository'den soruları getir
        List<Question> questions = questionRepository.findAllById(questionIds);
        return questions;
    }


    public void deleteQuestionFromSurvey(UUID surveyUuid, String questionId) {
        Optional<Survey> optionalSurvey = surveyRepository.findByUuid(surveyUuid);
        if (optionalSurvey.isEmpty()) {
            throw new BanketApplicationException(ErrorType.SURVEY_NOT_FOUND);
        }
        Survey survey = optionalSurvey.get();
        List<AddQuestionToSurveyDto> questionDtos = survey.getQuestions();
        List<String> questionIds = questionDtos.stream()
                .map(AddQuestionToSurveyDto::getQuestionId)
                .collect(Collectors.toList());
        questionIds.remove(questionId);
        List<AddQuestionToSurveyDto> newQuestionDtos = questionDtos.stream()
                .filter(q -> !q.getQuestionId().equals(questionId))
                .collect(Collectors.toList());
        survey.setQuestions(newQuestionDtos);
        update(survey);
    }



}