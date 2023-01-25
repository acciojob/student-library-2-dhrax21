//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.driver.controller;

import com.driver.models.Student;
import com.driver.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/student"})
public class StudentController {
    @Autowired
    StudentService studentService;

    public StudentController() {
    }

    public ResponseEntity getStudentByEmail(@RequestParam("email") String email) {
        this.studentService.getDetailsByEmail(email);
        return new ResponseEntity("Student details printed successfully ", HttpStatus.OK);
    }

    public ResponseEntity getStudentById(@RequestParam("id") int id) {
        this.studentService.getDetailsById(id);
        return new ResponseEntity("Student details printed successfully ", HttpStatus.OK);
    }

    public ResponseEntity createStudent(@RequestBody Student student) {
        this.studentService.createStudent(student);
        return new ResponseEntity("the student is successfully added to the system", HttpStatus.CREATED);
    }

    public ResponseEntity updateStudent(@RequestBody Student student) {
        this.studentService.updateStudent(student);
        return new ResponseEntity("student is updated", HttpStatus.ACCEPTED);
    }

    public ResponseEntity deleteStudent(@RequestParam("id") int id) {
        this.studentService.deleteStudent(id);
        return new ResponseEntity("student is deleted", HttpStatus.ACCEPTED);
    }
}
