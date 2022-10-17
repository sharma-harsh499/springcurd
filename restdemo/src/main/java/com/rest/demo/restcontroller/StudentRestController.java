package com.rest.demo.restcontroller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.demo.entity.Student;

@RestController
@RequestMapping("/demo")
public class StudentRestController {

    private List<Student> theStudents;

    //define @PostConstruct to load the student data ... only once

    @PostConstruct
    public void loadData(){
        theStudents=new ArrayList<>();

        theStudents.add(new Student("Harsh","Sharma"));
        theStudents.add(new Student("Mayank","Sharma"));
        theStudents.add(new Student("Raj","Sharma"));
    }
    
    @GetMapping("/students")
    public List<Student> getStudents(){
        return theStudents;
    }
    //endpoint to retrieve single entity object

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){

        //index into the list 
        
        //check the student id gainst list size

        if((studentId>=theStudents.size()) || (studentId<0)){
            throw new StudentNotFoundException("Student id not found");
        }
        return theStudents.get(studentId);
    }
   
}
