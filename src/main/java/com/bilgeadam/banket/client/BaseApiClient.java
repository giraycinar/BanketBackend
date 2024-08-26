package com.bilgeadam.banket.client;


import com.bilgeadam.banket.entity.Group;
import com.bilgeadam.banket.entity.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@FeignClient(name = "coursegroup", url = "http://localhost:8082")
public interface BaseApiClient {

    @GetMapping("/find-all")
    List<Group> findAll();

    @GetMapping("/coursegroup/find-course-group-by-name/{name}")
    List<Student> findCourseGroupByName(@PathVariable String name);


}
