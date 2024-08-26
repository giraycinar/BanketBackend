package com.bilgeadam.banket.controller;

import com.bilgeadam.banket.dto.request.SaveStudentRequestDto;
import com.bilgeadam.banket.dto.request.UpdateStudentRequestDto;
import com.bilgeadam.banket.entity.Student;
import com.bilgeadam.banket.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.bilgeadam.banket.constant.Endpoint.*;

@RestController
@RequestMapping(ROOT + STUDENT)
@RequiredArgsConstructor
@CrossOrigin
public class StudentController {

    private final StudentService studentService;

    // Berresu : Save metodu kontrol amaçlı yazılmıştır.Daha sonra Student başka bir yerden çekilecektir.
    // Dönüş tiplerinde kurguya göre revizeler yapılabilir.

    @PostMapping(SAVE)
    public ResponseEntity<String> saveStudent(@Valid SaveStudentRequestDto dto){
        return ResponseEntity.ok(studentService.saveStudent(dto));
    }
    @PutMapping(UPDATE)
    public ResponseEntity<String> updateStudent(UpdateStudentRequestDto dto){
        return ResponseEntity.ok(studentService.updateStudent(dto));
    }
    @GetMapping(GET_BY_UUID)
    public ResponseEntity<Student> getStudent (@PathVariable UUID uuid){
        return ResponseEntity.ok((studentService.getStudent(uuid)));
    }
    @GetMapping(GET_BY_NAME)
    public ResponseEntity<List<Student>> getStudent (@PathVariable String groupName){
        return ResponseEntity.ok((studentService.getStudentByGroupName(groupName)));
    }
    @GetMapping(GET_ALL)
    public ResponseEntity<List<Student>> getAllStudents(){
        return ResponseEntity.ok((studentService.findAll()));
    }
    @DeleteMapping(DELETE_BY_UUID)
    public ResponseEntity<String> deleteStudent(@PathVariable UUID uuid){
        return ResponseEntity.ok(studentService.deleteStudent(uuid));
    }

    @DeleteMapping(REMOVE_BY_UUID)
    public ResponseEntity<String> removeStudent(@PathVariable UUID uuid){
        return ResponseEntity.ok(studentService.removeStudent(uuid));
    }
}
