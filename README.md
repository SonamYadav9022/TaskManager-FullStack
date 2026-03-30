# Task Manager — Full Stack Web Application

A full-stack task management application built with **Java**, **Spring Boot**, **JDBC**, and **MySQL** on the backend, and a **vanilla HTML/CSS/JavaScript** single-page frontend. Includes a console-based version (v1) and an advanced web version (v2) with REST APIs and live cloud deployment.

**Live Demo → [taskmanager-fullstack-production-edf1.up.railway.app](https://taskmanager-fullstack-production-edf1.up.railway.app)**

---

## Tech Stack

| Layer | Technology |
|-------|------------|
| Language | Java 17 |
| Backend Framework | Spring Boot |
| Database Access | JDBC (raw SQL — no ORM) |
| Database | MySQL |
| Frontend | HTML, CSS, JavaScript (Vanilla) |
| API Testing | Postman |
| Cloud Deployment | Railway |
| Version Control | Git & GitHub |

---

## Features

- Add a new task with title, status, and priority
- View all tasks in a clean card-based UI
- Edit any existing task inline via modal
- Delete tasks with confirmation
- Filter tasks by status — Pending, In Progress, Done
- Filter tasks by priority — High, Medium, Low
- Live task count stats in the header
- Toast notifications for every action
- Fully responsive single-page UI
- REST APIs tested and verified via Postman

---

## REST API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/tasks` | Get all tasks |
| GET | `/task/{id}` | Get a single task by ID |
| POST | `/task` | Create a new task |
| PUT | `/task/{id}` | Update an existing task |
| DELETE | `/task/{id}` | Delete a task |
| GET | `/hello` | Health check endpoint |
| GET | `/test-db` | Test database connection |

### Sample Request — Create Task (POST `/task`)
```json
{
  "title": "Complete project documentation",
  "status": "in-progress",
  "priority": "high"
}
```

### Sample Response — Get All Tasks (GET `/tasks`)
```json
[
  {
    "id": 1,
    "title": "Complete project documentation",
    "status": "in-progress",
    "priority": "high"
  },
  {
    "id": 2,
    "title": "Fix login bug",
    "status": "pending",
    "priority": "medium"
  }
]
```

---

## Project Structure

```
taskmanager/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/sonam/taskmanager/
│       │       ├── TaskmanagerApplication.java   # Spring Boot entry point
│       │       ├── DBConnection.java             # JDBC connection handler
│       │       ├── controller/
│       │       │   └── HelloController.java      # REST API endpoints
│       │       ├── dao/
│       │       │   └── TaskDAO.java              # Data access layer (CRUD)
│       │       └── model/
│       │           └── Task.java                 # Task entity / POJO
│       └── resources/
│           ├── static/
│           │   └── index.html                    # Frontend SPA
│           └── application.properties            # App configuration
├── pom.xml
└── README.md
```

---

## Architecture

```
Browser (index.html)
      |
      | HTTP Requests (fetch API)
      ↓
Spring Boot — HelloController
      |
      | Java method calls
      ↓
TaskDAO (JDBC)
      |
      | Raw SQL queries
      ↓
MySQL Database (tasks table)
```

The frontend is served directly by Spring Boot from `src/main/resources/static/` — no separate server needed.

---

## Database Schema

```sql
CREATE TABLE tasks (
    id       INT AUTO_INCREMENT PRIMARY KEY,
    title    VARCHAR(255) NOT NULL,
    status   VARCHAR(50)  DEFAULT 'pending',
    priority VARCHAR(50)  DEFAULT 'medium'
);
```

---

## Run Locally

### Prerequisites
- Java 17+
- Maven
- MySQL running on port 3306

### Steps

**1. Clone the repository**
```bash
git clone https://github.com/SonamYadav9022/TaskManager-FullStack.git
cd TaskManager-FullStack
```

**2. Create the database**
```sql
CREATE DATABASE taskdb;
USE taskdb;
CREATE TABLE tasks (
    id       INT AUTO_INCREMENT PRIMARY KEY,
    title    VARCHAR(255) NOT NULL,
    status   VARCHAR(50)  DEFAULT 'pending',
    priority VARCHAR(50)  DEFAULT 'medium'
);
```

**3. Update credentials**

Edit `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/taskdb?useSSL=false&serverTimezone=UTC
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
server.port=8080
```

**4. Run the application**
```bash
mvn spring-boot:run
```

**5. Open in browser**
```
http://localhost:8080
```

---

## Environment Variables (for deployment)

| Variable | Description |
|----------|-------------|
| `DB_URL` | Full JDBC MySQL connection URL |
| `DB_USER` | MySQL username |
| `DB_PASS` | MySQL password |
| `PORT` | Server port (Railway sets this automatically) |

---

## Deployment

This project is deployed on **Railway** using:
- Spring Boot service connected to GitHub repo (auto-deploys on push)
- MySQL database plugin on Railway
- Environment variables for secure credential management

**Live URL:** https://taskmanager-fullstack-production-edf1.up.railway.app

---

## Version History

| Version | Description |
|---------|-------------|
| v1 | Console-based Task Manager in Core Java — menu-driven CRUD with in-memory storage |
| v2 | Full-stack web app with Spring Boot REST APIs, MySQL, and deployed frontend |

---

## What I Learned

- Building REST APIs from scratch using Spring Boot
- Using raw JDBC without ORM — writing and executing SQL queries manually
- Applying the DAO (Data Access Object) design pattern
- Connecting a Java backend to a MySQL database
- Serving a frontend SPA directly from Spring Boot static resources
- Deploying a full-stack Java application to cloud with environment-based config
- Testing APIs end-to-end using Postman

---

## Author

**Sonam Yadav**
- GitHub: [@SonamYadav9022](https://github.com/SonamYadav9022)
- Email: sonamyadav246810@gmail.com
