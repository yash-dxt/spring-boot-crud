package com.example.collegemanager;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface StudentRepository extends MongoRepository<Student, String> {

    @Query("{rollNumber: ?0}")
    Student findByRollNumber(int rollNumber);
}
