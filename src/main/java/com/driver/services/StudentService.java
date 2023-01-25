//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.driver.services;

import com.driver.models.Student;
import com.driver.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    CardService cardService4;
    @Autowired
    StudentRepository studentRepository4;

    public StudentService() {
    }

    public Student getDetailsByEmail(String email) {
        return this.studentRepository4.findByEmailId(email);
    }

    public Student getDetailsById(int id) {
        return (Student)this.studentRepository4.findById(id).get();
    }

    public void createStudent(Student student) {
        this.cardService4.createAndReturn(student);
    }

    public void updateStudent(Student student) {
        this.studentRepository4.updateStudentDetails(student);
    }

    public void deleteStudent(int id) {
        this.cardService4.deactivateCard(id);
        this.studentRepository4.deleteById(id);
    }
}
