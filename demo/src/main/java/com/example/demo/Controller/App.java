package com.example.demo.Controller;

import com.example.demo.Model.Student;
import com.example.demo.Service.StudentServiceImple;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
@AllArgsConstructor
public class App {
    private StudentServiceImple studentService;

    @PostMapping("addStudent")
    public ResponseEntity<Student> addStudent(@RequestBody Student student){
        return ResponseEntity.ok(studentService.addStudent(student));
    }

    @GetMapping("getStudents")
    public List<Student> getAllStudent(){
        return studentService.getAllStudent();
    }
}
