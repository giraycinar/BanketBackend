package com.bilgeadam.banket.controller;

import com.bilgeadam.banket.dto.request.DeleteQuestionRequestDto;
import com.bilgeadam.banket.dto.request.SaveSurveyRequestDto;
import com.bilgeadam.banket.dto.request.UpdateSurveyRequestDto;
import com.bilgeadam.banket.entity.Question;
import com.bilgeadam.banket.entity.Survey;
import com.bilgeadam.banket.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static com.bilgeadam.banket.constant.Endpoint.*;

@CrossOrigin
@RestController
@RequestMapping(ROOT + SURVEY)
@RequiredArgsConstructor
public class SurveyController {

    private final SurveyService surveyService;

    @PostMapping(SAVE)
    public ResponseEntity<String> saveSurvey(@RequestBody SaveSurveyRequestDto dto) {
        return ResponseEntity.ok(surveyService.saveSurvey(dto));
    }

    @GetMapping(GET_ALL)
    public ResponseEntity<List<Survey>> getAllSurveys() {
        return ResponseEntity.ok(surveyService.findAll());
    }

    @DeleteMapping(DELETE_BY_UUID)
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<String> deleteSurvey(@PathVariable UUID uuid) {
        return ResponseEntity.ok(surveyService.deleteSurvey(uuid));
    }

    @DeleteMapping(REMOVE_BY_UUID)
    public ResponseEntity<String> removeSurvey(@PathVariable UUID uuid) {
        return ResponseEntity.ok(surveyService.removeSurvey(uuid));
    }

    @PutMapping(UPDATE)
    public ResponseEntity<String> updateSurvey(@RequestBody UpdateSurveyRequestDto dto) {
        return ResponseEntity.ok(surveyService.updateSurvey(dto));
    }

    @GetMapping(GET_BY_UUID)
    @CrossOrigin("*")
    public ResponseEntity<Survey> getSurvey(@PathVariable UUID uuid) {
        Survey survey = surveyService.getSurvey(uuid);
        if (survey.getQuestions().isEmpty()) {
            survey.setQuestions(Collections.emptyList());
        }
        return ResponseEntity.ok(survey);
    }


//    @GetMapping(GET_QUESTIONS_BY_SURVEY)
//    public ResponseEntity<List<Question>> getQuestionsBySurvey(@PathVariable UUID surveyUuid) {
//        List<Question> questions = surveyService.getQuestionsBySurvey(surveyUuid);
//        return ResponseEntity.ok(questions);
//    }

    @GetMapping(GET_QUESTION_BY_SURVEY)
    @CrossOrigin("*")
    public ResponseEntity<List<Question>> getQuestionsBySurvey(@PathVariable UUID surveyUuid) {
        List<Question> questions = surveyService.getQuestionsBySurvey(surveyUuid);
        return ResponseEntity.ok(questions);
    }

    @DeleteMapping("/delete-question")
    public ResponseEntity<String> deleteQuestionFromSurvey(@RequestBody DeleteQuestionRequestDto requestDto) {
        surveyService.deleteQuestionFromSurvey(requestDto.getSurveyUuid(), requestDto.getQuestionId());
        return ResponseEntity.ok("Soru silindi");
    }
}
