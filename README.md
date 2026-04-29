# 📘 Student Management System

A **full-stack Student Management System** built using **Spring Boot** and **React** that allows users to perform **CRUD operations** on student records.

---

# 🚀 Tech Stack

## 🔧 Backend
- Java 21
- Spring Boot 4.0.6
- PostgreSQL
- JDBC (JdbcTemplate)
- Maven

## 🎨 Frontend
- React 18.2.0
- Axios
- CSS3

---

# 📂 Project Structure


---

# 🔙 Backend (Spring Boot)

## 🏗 Architecture

- **Controller Layer** → REST endpoints  
- **Service Layer** → Business logic  
- **Repository Layer** → JDBC database operations  
- **Entity Layer** → Student model  

---

## ⚙️ Prerequisites

Make sure the following are installed:

- Java 21 or higher
- Maven 3.6+
- PostgreSQL 10+

---

# ▶️ Running Backend

cd backend

# Build project
mvn clean package

# Run backend
mvn spring-boot:run

-----


# 🗄 Database Setup

## Option 1 — Using schema.sql

```bash
psql -U postgres

\i backend/src/main/resources/schema.sql


| Method | Endpoint             | Description       |
| ------ | -------------------- | ----------------- |
| POST   | `/api/students`      | Create student    |
| GET    | `/api/students`      | Get all students  |
| GET    | `/api/students/{id}` | Get student by ID |
| PUT    | `/api/students/{id}` | Update student    |
| DELETE | `/api/students/{id}` | Delete student    |


