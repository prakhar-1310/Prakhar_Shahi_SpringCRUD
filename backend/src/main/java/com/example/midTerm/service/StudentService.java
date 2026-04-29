package com.example.midTerm.service;

import com.example.midTerm.entity.Student;
import com.example.midTerm.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Create a new student
    public void createStudent(Student student) {
        studentRepository.save(student);
    }

    // Get all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Get student by ID
    public Optional<Student> getStudentById(Integer id) {
        return studentRepository.findById(id);
    }

    // Update a student
    public void updateStudent(Student student) {
        studentRepository.update(student);
    }

    // Delete a student
    public void deleteStudent(Integer id) {
        studentRepository.delete(id);
    }
}
