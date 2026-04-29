package com.example.midTerm.repository;

import com.example.midTerm.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Student> rowMapper = (rs, rowNum) -> new Student(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("email"),
            rs.getString("course")
    );

    // Create
    public void save(Student student) {
        String sql = "INSERT INTO students (name, email, course) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, student.getName(), student.getEmail(), student.getCourse());
    }

    // Read All
    public List<Student> findAll() {
        String sql = "SELECT * FROM students";
        return jdbcTemplate.query(sql, rowMapper);
    }

    // Read by ID
    public Optional<Student> findById(Integer id) {
        String sql = "SELECT * FROM students WHERE id = ?";
        try {
            Student student = jdbcTemplate.queryForObject(sql, rowMapper, id);
            return Optional.of(student);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    // Update
    public void update(Student student) {
        String sql = "UPDATE students SET name = ?, email = ?, course = ? WHERE id = ?";
        jdbcTemplate.update(sql, student.getName(), student.getEmail(), student.getCourse(), student.getId());
    }

    // Delete
    public void delete(Integer id) {
        String sql = "DELETE FROM students WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
