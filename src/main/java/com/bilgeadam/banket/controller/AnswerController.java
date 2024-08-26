package com.bilgeadam.banket.controller;
import com.bilgeadam.banket.dto.request.SaveAnswerRequestDto;
import com.bilgeadam.banket.dto.request.UpdateAnswerRequestDto;
import com.bilgeadam.banket.entity.Answer;
import com.bilgeadam.banket.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

import static com.bilgeadam.banket.constant.Endpoint.*;
@RestController
@RequestMapping(ROOT + ANSWER )
@RequiredArgsConstructor
@CrossOrigin
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping(SAVE)
    public ResponseEntity<String>saveAnswer(SaveAnswerRequestDto dto){
        return ResponseEntity.ok(answerService.saveAnswer(dto));
    }
    @GetMapping(GET_ALL)
    public ResponseEntity<List<Answer>> getAllAnswer(){
        return ResponseEntity.ok((answerService.findAll()));
    }
    @PutMapping(UPDATE+"/{answerUuid}")
    public ResponseEntity<String>updateAnswer(  @PathVariable UUID answerUuid,@RequestBody UpdateAnswerRequestDto dto){
        return ResponseEntity.ok(answerService.updateAnswer(answerUuid,dto));
    }
    @GetMapping(GET_BY_UUID)
    public ResponseEntity<Answer>findByUuid( @PathVariable UUID uuid){
        return ResponseEntity.ok(answerService.findByUuid(uuid));
    }
    @DeleteMapping(DELETE_BY_UUID)
    public ResponseEntity<String>deleteAnswer(  @PathVariable UUID uuid){
        return ResponseEntity.ok(answerService.deleteAnswer(uuid));
    }
    @DeleteMapping(REMOVE_BY_UUID)
    public ResponseEntity<String>removeAnswer( @PathVariable UUID uuid){
        return ResponseEntity.ok(answerService.removeAnswer(uuid));
    }


    }


