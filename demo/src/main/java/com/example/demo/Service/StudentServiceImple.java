package com.example.demo.Service;

import com.example.demo.Model.Student;
import com.example.demo.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImple implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImple(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    public void verifyEmail(String email,Student student,MongoTemplate mongoTemplate) {
        usingMongoTemplateAndQuery(email, student, mongoTemplate);
    }

    private void usingMongoTemplateAndQuery(String email, Student student, MongoTemplate mongoTemplate) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));

        List<Student> students = mongoTemplate.find(query,Student.class);

        if(students.size() > 1){
            throw new IllegalStateException("Found many students with email"+ email);
        }

        if(students.isEmpty()){
            System.out.println("Inserting Student "+ student);
            addStudent(student);
        }else{
            System.out.println(student +"already exist");
        }
        studentRepository.findStudentByEmail(email)
                .ifPresentOrElse(s -> {
                    System.out.println(s +" already exist");
                }, () -> {
                    System.out.println("Inserting Student "+ student);
                    addStudent(student);
                });
    }

    @Override
    public void getEmailByNameAndVerify(String name, String email,MongoTemplate mongoTemplate) {
        QuerytoGetEmailAndName(name, email, mongoTemplate);

        studentRepository.findName_BE(email).orElseThrow(()->{
            throw new IllegalStateException("Email is Not Found BY : "+name);
        });
    }

    @Override
    public void getStudentByName(String name,String email, MongoTemplate mongoTemplate) {
        QuerytoGetEmailAndName(name, email, mongoTemplate);
    }

    private static void QuerytoGetEmailAndName(String name, String email, MongoTemplate mongoTemplate) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));

        String query_email = mongoTemplate.getCollectionName(query.getClass());
        if (query_email.isEmpty()){
            throw new IllegalStateException("Name is Not Found For this "+ email);
        }else {
            System.out.println(email +" already exist");
        }
    }
}
