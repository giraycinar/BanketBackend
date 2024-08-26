package com.bilgeadam.banket.service;

import com.bilgeadam.banket.dto.request.SaveFilledSurveyRequestDto;
import com.bilgeadam.banket.entity.FilledSurvey;
import com.bilgeadam.banket.exception.BanketApplicationException;
import com.bilgeadam.banket.exception.ErrorType;
import com.bilgeadam.banket.mapper.IFilledSurveyMapper;
import com.bilgeadam.banket.repository.FilledSurveyRepository;
import com.bilgeadam.banket.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FilledSurveyService extends ServiceManager<FilledSurvey, String> {

    private final FilledSurveyRepository filledSurveyRepository;

    public FilledSurveyService(FilledSurveyRepository filledSurveyRepository) {
        super(filledSurveyRepository);
        this.filledSurveyRepository = filledSurveyRepository;
    }


    public String saveFilledSurvey(SaveFilledSurveyRequestDto dto) {
        FilledSurvey filledSurvey = IFilledSurveyMapper.INSTANCE.saveFilledSurveyRequestDtoToEntity(dto);
        save(filledSurvey);
        return "Save filled survey successful";
    }


    public String updateFilledSurvey(UUID uuid, SaveFilledSurveyRequestDto dto) {
        Optional<FilledSurvey> optionalFilledSurvey = filledSurveyRepository.findByUuid(uuid);
        if (optionalFilledSurvey.isEmpty()) {
            throw new BanketApplicationException(ErrorType.FILLED_SURVEY_NOT_FOUND, "Filled survey not found");
        }
        FilledSurvey filledSurvey = optionalFilledSurvey.get();
        filledSurvey.setAnswers(dto.getAnswers());
        update(filledSurvey);
        return "Update filled survey successful";
    }


    public String deleteFilledSurvey(UUID uuid) {
        Optional<FilledSurvey> optionalFilledSurvey = filledSurveyRepository.findByUuid(uuid);
        if (optionalFilledSurvey.isEmpty()) {
            throw new BanketApplicationException(ErrorType.FILLED_SURVEY_NOT_FOUND, "Filled survey not found");
        }
        FilledSurvey filledSurvey = optionalFilledSurvey.get();
        softDelete(filledSurvey);
        return "Delete filled survey successful";
    }

    public String removeFilledSurvey(UUID uuid) {
        Optional<FilledSurvey> optionalFilledSurvey = filledSurveyRepository.findByUuid(uuid);
        if (optionalFilledSurvey.isEmpty()) {
            throw new BanketApplicationException(ErrorType.FILLED_SURVEY_NOT_FOUND, "Filled survey not found");
        }
        FilledSurvey filledSurvey = optionalFilledSurvey.get();
        hardDelete(filledSurvey);
        return "Remove filled survey successful";
    }

    public FilledSurvey getFilledSurvey(UUID uuid) {
        Optional<FilledSurvey> optionalFilledSurvey = filledSurveyRepository.findByUuid(uuid);
        if (optionalFilledSurvey.isEmpty()) {
            throw new BanketApplicationException(ErrorType.FILLED_SURVEY_NOT_FOUND, "Filled survey not found");
        }
        return optionalFilledSurvey.get();
    }
    public List<FilledSurvey> getAllFilledSurveys() {
        return filledSurveyRepository.findAll();
    }


    public Boolean getFilledSurveyByMailAndSurveyId(String surveyId, String email) {
        return filledSurveyRepository.getFilledSurveyByStudentEmailAndSurveyId(surveyId, email);
    }
}
