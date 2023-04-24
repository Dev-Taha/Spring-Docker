package com.example.demo.Service;

import com.example.demo.Model.Student;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentService {
    public Student addStudent(Student student);
    public List<Student> getAllStudent();

    public void getEmailByNameAndVerify(String name, String email, MongoTemplate mongoTemplate);

    public void getStudentByName(String name,String email, MongoTemplate mongoTemplate);


//    public void verifyEmail(String email, Student student);
}
