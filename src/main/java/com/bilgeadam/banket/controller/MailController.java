package com.bilgeadam.banket.controller;

import com.bilgeadam.banket.dto.request.AgainStudentListMailSurveyDto;
import com.bilgeadam.banket.dto.request.AgainStudentMailSurveyDto;
import com.bilgeadam.banket.dto.request.MailSurveyDto;
import com.bilgeadam.banket.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bilgeadam.banket.constant.Endpoint.*;

@CrossOrigin(maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping(ROOT + MAIL)
@AllArgsConstructor
public class MailController {

    private final MailService mailService;

    @PostMapping(SENDED_SURVEY)
    public ResponseEntity<String> sendSurvey(@RequestBody MailSurveyDto dto){
        return ResponseEntity.ok(mailService.prepareSurveyMailContent(dto));
    }
    @GetMapping(SEND_MAIL_SAVE_INFO)
    @CrossOrigin
    public ResponseEntity<String> sendMailAndSafeInfo(@RequestParam String groupName,@RequestParam String surveyId){
        return ResponseEntity.ok(mailService.sendMailAndSafeInfo(groupName,surveyId));
    }
    @PostMapping(SEND_MAIL_BY_STUDENT_LIST)
    @CrossOrigin("*")
    public ResponseEntity<Boolean> sendMailByStudentList(@RequestBody AgainStudentListMailSurveyDto dto){
        return ResponseEntity.ok(mailService.sendMailByStudentList(dto.getSurveyId(),dto.getEmailList()));
    }
    @PostMapping(SEND_MAIL_BY_STUDENT)
    @CrossOrigin
    public ResponseEntity<Boolean> sendMailByStudent(@RequestBody AgainStudentMailSurveyDto dto){
        return ResponseEntity.ok(mailService.sendMailByStudent(dto.getSurveyId(),dto.getStudentEmail() ));
    }
}
