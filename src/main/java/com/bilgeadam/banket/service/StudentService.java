package com.bilgeadam.banket.service;

import com.bilgeadam.banket.dto.request.SaveStudentRequestDto;
import com.bilgeadam.banket.dto.request.UpdateStudentRequestDto;
import com.bilgeadam.banket.entity.BaseAPIStudent;
import com.bilgeadam.banket.entity.Student;
import com.bilgeadam.banket.exception.BanketApplicationException;
import com.bilgeadam.banket.exception.ErrorType;
import com.bilgeadam.banket.mapper.IStudentMapper;
import com.bilgeadam.banket.repository.StudentRepository;
import com.bilgeadam.banket.utility.ServiceManager;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;


@Service
public class StudentService extends ServiceManager<Student, String> {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        super(studentRepository);
        this.studentRepository = studentRepository;
    }

    public String saveStudent(SaveStudentRequestDto dto) {
        if (studentRepository.existsByEmail(dto.getEmail())){
            throw new BanketApplicationException(ErrorType.EMAIL_ALREADY_EXISTS);
        }
        Student student = IStudentMapper.INSTANCE.saveStudentRequestDtoToStudent(dto);
        save(student);
        return "Save student successful";
    }


    //Berresu : Şu an tüm fieldlar update edilebilir halde.Ancak daha sonra kurguya göre değişiklik yapılabilir.
    public String updateStudent(UpdateStudentRequestDto dto) {
        Optional<Student> optionalStudent = studentRepository.findByUuid(dto.getUuid());
        if (optionalStudent.isEmpty()){
            throw new BanketApplicationException(ErrorType.STUDENT_NOT_FOUND);
        }

        if (dto.getName() != null){
            optionalStudent.get().setName(dto.getName());
        }
        if (dto.getSurname() != null){
            optionalStudent.get().setSurname(dto.getSurname());
        }
        if (dto.getEmail() != null){
            if (studentRepository.existsByEmail(dto.getEmail())){
                throw new BanketApplicationException(ErrorType.EMAIL_ALREADY_EXISTS);
            }else {
                optionalStudent.get().setEmail(dto.getEmail());
            }
        }
        if (dto.getGroup() != null){
            optionalStudent.get().setGroup(dto.getGroup());
        }
        update(optionalStudent.get());
        return "Update student successful";

    }

    public Student getStudent(UUID uuid) {
        Optional<Student> optionalStudent = studentRepository.findByUuid(uuid);
        if (optionalStudent.isEmpty()){
            return Student.builder().build();
        }
        return optionalStudent.get();
    }
    public String deleteStudent(UUID uuid) {
        Optional<Student> optionalStudent = studentRepository.findByUuid(uuid);
        if (optionalStudent.isEmpty()){
            throw new BanketApplicationException(ErrorType.STUDENT_NOT_FOUND);
        }
        if (optionalStudent.get().isDeleted()){
            throw new BanketApplicationException(ErrorType.STUDENT_ALREADY_DELETED);
        }
        softDelete(optionalStudent.get());
        return "Delete student successfully";
    }

    public String removeStudent(UUID uuid) {
        Optional<Student> optionalStudent = studentRepository.findByUuid(uuid);
        if (optionalStudent.isEmpty()){
            throw new BanketApplicationException(ErrorType.STUDENT_NOT_FOUND);
        }
        hardDelete(optionalStudent.get());
        return "Remove student successfully";
    }

    public List<Student> getBaseAPIStudentsFromBaseAPIGroupId (Long groupId) {
        List<Student> students = new ArrayList<>();
        try {
            URL url = new URL("http://localhost:8082/student/find-student-by-group-id/" + groupId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String value = bufferedReader.readLine();
            BaseAPIStudent[] responseFromBaseApi = new Gson().fromJson(value, BaseAPIStudent[].class);
            Arrays.asList(responseFromBaseApi).forEach(baseAPIStudent -> {
                Student student = Student.builder()
                        .id(baseAPIStudent.getId().toString())
                        .name(baseAPIStudent.getName())
                        .surname(baseAPIStudent.getSurname())
                        .email(baseAPIStudent.getBaEmail()) // BilgeAdam emailini kullanalım.
                        .build();
                students.add(student);
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (students.isEmpty()) throw new BanketApplicationException(ErrorType.STUDENT_NOT_FOUND);
        return students;
    }


    public List<Student> getStudentByGroupName(String groupName) {
        List<Student> students = studentRepository.findByGroup(groupName);
        if (students.isEmpty()) throw new BanketApplicationException(ErrorType.STUDENT_NOT_FOUND);
        return students;
    }
}
