package com.bilgeadam.banket.controller;


import com.bilgeadam.banket.dto.request.SaveQuestionRequestDto;
import com.bilgeadam.banket.dto.request.UpdateQuestionRequestDto;
import com.bilgeadam.banket.entity.Question;
import com.bilgeadam.banket.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.bilgeadam.banket.constant.Endpoint.*;

@CrossOrigin
@RestController
@RequestMapping(ROOT + QUESTION)
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping(SAVE)
    public ResponseEntity<String> saveQuestion(@RequestBody @Valid SaveQuestionRequestDto dto) {
        return ResponseEntity.ok(questionService.saveQuestion(dto));
    }

    @PutMapping(UPDATE)
    public ResponseEntity<String> updateQuestion(@RequestBody @Valid UpdateQuestionRequestDto dto) {
        return ResponseEntity.ok(questionService.updateQuestion(dto));
    }

    @GetMapping(GET_BY_UUID)
    public ResponseEntity<Question> getQuestion(@PathVariable UUID uuid) {
        return ResponseEntity.ok(questionService.getQuestion(uuid));
    }

    @GetMapping(GET_ALL)
    public ResponseEntity<List<Question>> getAllUsers() {

        return ResponseEntity.ok(questionService.findAll());
    }


    @DeleteMapping(DELETE_BY_UUID)

    public ResponseEntity<String> deleteQuestion(@PathVariable UUID uuid) {
        return ResponseEntity.ok(questionService.deleteQuestion(uuid));
    }


    @DeleteMapping(REMOVE_BY_UUID)
    public ResponseEntity<String> removeQuestion(@PathVariable UUID uuid) {
        return ResponseEntity.ok(questionService.removeQuestion(uuid));
    }
}
