import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './App.css';
import StudentForm from './components/StudentForm';
import StudentList from './components/StudentList';

const API_URL = 'http://localhost:8080/api/students';

function App() {
  const [students, setStudents] = useState([]);
  const [loading, setLoading] = useState(false);
  const [editingStudent, setEditingStudent] = useState(null);
  const [showForm, setShowForm] = useState(false);

  // Fetch all students
  useEffect(() => {
    fetchStudents();
  }, []);

  const fetchStudents = async () => {
    setLoading(true);
    try {
      const response = await axios.get(API_URL);
      setStudents(response.data);
    } catch (error) {
      console.error('Error fetching students:', error);
      alert('Error fetching students');
    } finally {
      setLoading(false);
    }
  };

  // Create or Update student
  const handleSaveStudent = async (studentData) => {
    try {
      if (editingStudent) {
        // Update
        await axios.put(`${API_URL}/${editingStudent.id}`, studentData);
        alert('Student updated successfully');
      } else {
        // Create
        await axios.post(API_URL, studentData);
        alert('Student created successfully');
      }
      setEditingStudent(null);
      setShowForm(false);
      fetchStudents();
    } catch (error) {
      console.error('Error saving student:', error);
      alert('Error saving student: ' + (error.response?.data || error.message));
    }
  };

  // Delete student
  const handleDeleteStudent = async (id) => {
    if (window.confirm('Are you sure you want to delete this student?')) {
      try {
        await axios.delete(`${API_URL}/${id}`);
        alert('Student deleted successfully');
        fetchStudents();
      } catch (error) {
        console.error('Error deleting student:', error);
        alert('Error deleting student');
      }
    }
  };

  // Edit student
  const handleEditStudent = (student) => {
    setEditingStudent(student);
    setShowForm(true);
  };

  // Cancel form
  const handleCancelForm = () => {
    setEditingStudent(null);
    setShowForm(false);
  };

  // Open form for new student
  const handleNewStudent = () => {
    setEditingStudent(null);
    setShowForm(true);
  };

  return (
    <div className="App">
      <header className="App-header">
        <h1>Student Management System</h1>
      </header>
      
      <main className="App-main">
        <div className="action-bar">
          {!showForm && (
            <button className="btn btn-primary" onClick={handleNewStudent}>
              + Add New Student
            </button>
          )}
        </div>

        {showForm && (
          <StudentForm
            student={editingStudent}
            onSave={handleSaveStudent}
            onCancel={handleCancelForm}
          />
        )}

        {loading ? (
          <p className="loading">Loading students...</p>
        ) : students.length === 0 ? (
          <p className="no-students">No students found. Add one to get started!</p>
        ) : (
          <StudentList
            students={students}
            onEdit={handleEditStudent}
            onDelete={handleDeleteStudent}
          />
        )}
      </main>
    </div>
  );
}

export default App;
