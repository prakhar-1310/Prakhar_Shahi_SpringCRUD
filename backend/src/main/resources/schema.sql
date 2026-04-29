-- Create database
CREATE DATABASE IF NOT EXISTS midterm_db;

-- Use the database
\c midterm_db;

-- Create students table
CREATE TABLE IF NOT EXISTS students (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    course VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insert sample data (optional)
INSERT INTO students (name, email, course) VALUES 
('John Doe', 'john@example.com', 'Computer Science'),
('Jane Smith', 'jane@example.com', 'Information Technology'),
('Bob Johnson', 'bob@example.com', 'Software Engineering')
ON CONFLICT DO NOTHING;
