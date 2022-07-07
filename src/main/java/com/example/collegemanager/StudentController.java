package com.example.collegemanager;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/students")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public  ResponseEntity<List<Student>> fetchAllStudents(){
    try{
        List<Student> _students = studentService.getAllStudents();
        return new ResponseEntity<>(_students, HttpStatus.OK);
    }catch(Exception e){
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
}
    }

    @GetMapping("/{rollNumber}")
    public ResponseEntity<Student> getStudentUsingRollNumber(@PathVariable("rollNumber") int rollNumber){

        try{
            Student _studentData = studentService.getStudentByRollNumber(rollNumber);
            if(_studentData == null){
                return new ResponseEntity<>(null,  HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(_studentData,  HttpStatus.OK);

        }catch(Exception e){
            System.out.println(e.toString());

            return new ResponseEntity<>(null,  HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student){
        try{
            Student _resStudent = studentService.saveStudent(new Student(student.getName(), student.getRollNumber(), student.getSection(), student.getBranch(), student.getYear()));
            return new ResponseEntity<>(_resStudent, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(null,  HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/fee/{id}")
    public ResponseEntity<Student> markFeeAsPaid(@PathVariable("id") String id){

        try{
        Optional<Student> studentData = studentService.getStudentById(id);

        if(studentData.isPresent()){
            Student _studentRes = studentData.get();

            /**
             * Checking if student has already paid fee or not.
             * If he has not - we mark the fee as paid and then return the student.
             */

            if (!_studentRes.getFeePaid()) {
                _studentRes.setFeePaid(true);
                studentService.saveStudent(_studentRes);
            }
            return new ResponseEntity<>(_studentRes,HttpStatus.OK);

        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }catch(Exception e){
        return new ResponseEntity<>(null,  HttpStatus.INTERNAL_SERVER_ERROR);
    }

    }

}
