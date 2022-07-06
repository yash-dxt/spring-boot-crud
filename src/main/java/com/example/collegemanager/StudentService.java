package com.example.collegemanager;

import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service
public class StudentService {

    public final StudentRepository studentRepository;

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student saveStudent(Student student){
        return studentRepository.save(student);
    }

    public Optional<Student> getStudentById(String id){
        return studentRepository.findById(id);
    }

    public Student getStudentByRollNumber(int rollNumber){
        return studentRepository.findByRollNumber(rollNumber);
    }




}
