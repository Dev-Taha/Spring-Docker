package com.example.demo.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data // this annotation gives getters setters constructor
@Document // this annotation index collection in db
public class Student {
    @Id // primary key in db
    private String id;
    private String first_name;
    private String last_name;
//    @Indexed(unique = true)
    private String email;
    private Gender gender;
    private Address address;
    private List<String> favouriteSubjects;
    private BigDecimal totalSpentInBooks;
//    private LocalDateTime created;

    public Student(String first_name,
                   String last_name,
                   String email,
                   Gender gender,
                   Address address,
                   List<String> favouriteSubjects,
                   BigDecimal totalSpentInBooks
                   ) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.gender = gender;
        this.address = address;
        this.favouriteSubjects = favouriteSubjects;
        this.totalSpentInBooks = totalSpentInBooks;
    }

}
