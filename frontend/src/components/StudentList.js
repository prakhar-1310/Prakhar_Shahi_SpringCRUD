import React from 'react';

const StudentList = ({ students, onEdit, onDelete }) => {
  return (
    <div className="table-container">
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Course</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {students.map(student => (
            <tr key={student.id}>
              <td>{student.id}</td>
              <td>{student.name}</td>
              <td>{student.email}</td>
              <td>{student.course}</td>
              <td>
                <div className="action-buttons">
                  <button 
                    className="btn btn-secondary btn-small"
                    onClick={() => onEdit(student)}
                  >
                    Edit
                  </button>
                  <button 
                    className="btn btn-danger btn-small"
                    onClick={() => onDelete(student.id)}
                  >
                    Delete
                  </button>
                </div>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default StudentList;
