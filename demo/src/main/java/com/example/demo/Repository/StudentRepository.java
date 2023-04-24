package com.example.demo.Repository;

import com.example.demo.Model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface StudentRepository
        extends MongoRepository<Student,String> {
    Optional<Student> findStudentByEmail (String email);
    @Query("SELECT name FROM Student" +
            " WHERE email = 'name'")
    Optional<Student> findName_BE (String name);
}
