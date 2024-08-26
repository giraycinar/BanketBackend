package com.bilgeadam.banket.controller;

import com.bilgeadam.banket.dto.request.SaveFilledSurveyRequestDto;
import com.bilgeadam.banket.entity.FilledSurvey;
import com.bilgeadam.banket.service.FilledSurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.bilgeadam.banket.constant.Endpoint.*;

@RestController
@RequestMapping(ROOT + FILL_SURVEY)
@RequiredArgsConstructor
@CrossOrigin
public class FilledSurveyController {

    private final FilledSurveyService filledSurveyService;

    @PostMapping(SAVE)
    public ResponseEntity<String> saveFilledSurvey(@RequestBody SaveFilledSurveyRequestDto dto){
        return ResponseEntity.ok(filledSurveyService.saveFilledSurvey(dto));
    }

    @PutMapping(UPDATE)
    public ResponseEntity<String> updateFilledSurvey(@PathVariable UUID uuid, @RequestBody SaveFilledSurveyRequestDto dto){
        return ResponseEntity.ok(filledSurveyService.updateFilledSurvey(uuid, dto));
    }

    @GetMapping(GET_BY_UUID)
    public ResponseEntity<FilledSurvey> getFilledSurvey(@PathVariable UUID uuid){
        return ResponseEntity.ok(filledSurveyService.getFilledSurvey(uuid));
    }
    @GetMapping(GET_BY_MAIL_AND_SURVEY_ID)
    public ResponseEntity<Boolean> getFilledSurveyByMailAndSurveyId(@PathVariable String surveyId, @PathVariable String mail){
        return ResponseEntity.ok(filledSurveyService.getFilledSurveyByMailAndSurveyId(surveyId, mail));
    }

    @GetMapping(GET_ALL)
    public ResponseEntity<List<FilledSurvey>> getAllFilledSurveys(){
        return ResponseEntity.ok(filledSurveyService.getAllFilledSurveys());
    }

    @DeleteMapping(DELETE_BY_UUID)
    public ResponseEntity<String> deleteFilledSurvey(@PathVariable UUID uuid){
        return ResponseEntity.ok(filledSurveyService.deleteFilledSurvey(uuid));
    }

    @DeleteMapping(REMOVE_BY_UUID)
    public ResponseEntity<String> removeFilledSurvey(@PathVariable UUID uuid){
        return ResponseEntity.ok(filledSurveyService.removeFilledSurvey(uuid));
    }
}
