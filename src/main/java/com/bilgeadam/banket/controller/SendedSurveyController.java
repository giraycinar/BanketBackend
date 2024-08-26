package com.bilgeadam.banket.controller;

import com.bilgeadam.banket.dto.response.SendedSurveyResponseDto;
import com.bilgeadam.banket.repository.SendedSurveyRepository;
import com.bilgeadam.banket.service.SendedSurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static com.bilgeadam.banket.constant.Endpoint.*;

@RestController
@RequestMapping(ROOT + SENDED_SURVEY)
@RequiredArgsConstructor
@CrossOrigin
public class SendedSurveyController {

    private final SendedSurveyService sendedSurveyService;

    @GetMapping(GET_ALL)
    @CrossOrigin("*")
    public ResponseEntity<List<SendedSurveyResponseDto>> getAllSendedSurveys() {
        return ResponseEntity.ok(sendedSurveyService.getAllSendedSurveys());
    }

    @GetMapping(GET_ALL_BY_GROUP)
    public ResponseEntity<List<SendedSurveyResponseDto>> getAllSendedSurveysByGroup(UUID groupUuid) {
        return ResponseEntity.ok(sendedSurveyService.getAllSendedSurveysByGroup(groupUuid));
    }

    @GetMapping(GET_ALL_BY_SURVEY)
    public ResponseEntity<List<SendedSurveyResponseDto>> getAllSendedSurveysBySurvey(UUID surveyUuid) {
        return ResponseEntity.ok(sendedSurveyService.getAllSendedSurveysBySurvey(surveyUuid));
    }
}
