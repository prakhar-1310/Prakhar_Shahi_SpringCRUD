package com.example.midTerm.controller;

import com.example.midTerm.entity.Student;
import com.example.midTerm.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // POST /api/students - Create a new student
    @PostMapping
    public ResponseEntity<String> createStudent(@RequestBody Student student) {
        try {
            studentService.createStudent(student);
            return ResponseEntity.status(HttpStatus.CREATED).body("Student created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating student: " + e.getMessage());
        }
    }

    // GET /api/students - Get all students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        try {
            List<Student> students = studentService.getAllStudents();
            return ResponseEntity.ok(students);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // GET /api/students/{id} - Get student by ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer id) {
        try {
            Optional<Student> student = studentService.getStudentById(id);
            return student.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // PUT /api/students/{id} - Update a student
    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Integer id, @RequestBody Student student) {
        try {
            Optional<Student> existingStudent = studentService.getStudentById(id);
            if (existingStudent.isPresent()) {
                student.setId(id);
                studentService.updateStudent(student);
                return ResponseEntity.ok("Student updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating student: " + e.getMessage());
        }
    }

    // DELETE /api/students/{id} - Delete a student
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Integer id) {
        try {
            Optional<Student> student = studentService.getStudentById(id);
            if (student.isPresent()) {
                studentService.deleteStudent(id);
                return ResponseEntity.ok("Student deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting student: " + e.getMessage());
        }
    }
}
