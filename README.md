# 🎓 Student Management REST API
### Spring Boot 3 + Maven + H2 Database — Complete Beginner Guide

---

## 📋 Prerequisites

| Tool | Version | Check Command |
|------|---------|---------------|
| Java JDK | 17+ | `java -version` |
| Maven | 3.8+ | `mvn -version` |
| (Optional) IDE | IntelliJ / VS Code / Eclipse | — |

### Install Java (if not installed)
```bash
# Ubuntu / Debian
sudo apt update && sudo apt install openjdk-17-jdk

# macOS (Homebrew)
brew install openjdk@17

# Windows → Download from: https://adoptium.net
```

### Install Maven (if not installed)
```bash
# Ubuntu / Debian
sudo apt install maven

# macOS (Homebrew)
brew install maven

# Windows → Download from: https://maven.apache.org/download.cgi
```

---

## 🗂️ Project Structure

```
student-api/
├── pom.xml                          ← Maven config + dependencies
└── src/
    ├── main/
    │   ├── java/com/example/studentapi/
    │   │   ├── StudentApiApplication.java   ← ENTRY POINT (main method)
    │   │   ├── model/
    │   │   │   └── Student.java             ← Database entity
    │   │   ├── repository/
    │   │   │   └── StudentRepository.java   ← Data access (DB queries)
    │   │   ├── service/
    │   │   │   └── StudentService.java      ← Business logic
    │   │   └── controller/
    │   │       ├── StudentController.java   ← REST API endpoints
    │   │       └── GlobalExceptionHandler.java
    │   └── resources/
    │       ├── application.properties       ← App configuration
    │       └── data.sql                     ← Sample seed data
    └── test/
        └── java/com/example/studentapi/
            └── StudentServiceTest.java      ← Unit tests
```

### Architecture (Request Flow)
```
HTTP Request
    │
    ▼
[Controller]  ← Receives HTTP, validates input, sends response
    │
    ▼
[Service]     ← Business rules, logic
    │
    ▼
[Repository]  ← Database queries (JPA/Hibernate)
    │
    ▼
[H2 Database] ← In-memory storage
```

---

## 🚀 Step-by-Step: Run the Project

### Step 1 — Navigate to the project folder
```bash
cd student-api
```

### Step 2 — Build the project (downloads dependencies)
```bash
mvn clean install
```
> First run may take 2–3 minutes to download dependencies from Maven Central.
> You'll see: `BUILD SUCCESS` ✅

### Step 3 — Run the application
```bash
mvn spring-boot:run
```
You should see:
```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
 ........
 :: Spring Boot ::                (v3.2.3)

✅  Student API is running → http://localhost:8080/api/students
```

### Step 4 — Test the API

Open a **new terminal** and run these curl commands:

#### 📖 GET all students
```bash
curl http://localhost:8080/api/students
```

#### 📖 GET single student (id=1)
```bash
curl http://localhost:8080/api/students/1
```

#### 🔍 Search by name
```bash
curl "http://localhost:8080/api/students?search=Priya"
```

#### 🔍 Filter by grade
```bash
curl "http://localhost:8080/api/students?grade=10"
```

#### ➕ POST create a new student
```bash
curl -X POST http://localhost:8080/api/students \
     -H "Content-Type: application/json" \
     -d '{
       "firstName": "Deepa",
       "lastName": "Krishnan",
       "email": "deepa@school.com",
       "grade": 9
     }'
```

#### ✏️ PUT update a student (id=1)
```bash
curl -X PUT http://localhost:8080/api/students/1 \
     -H "Content-Type: application/json" \
     -d '{
       "firstName": "Aarav",
       "lastName": "Sharma",
       "email": "aarav.sharma@school.com",
       "grade": 11
     }'
```

#### ❌ DELETE a student (id=1)
```bash
curl -X DELETE http://localhost:8080/api/students/1
```

#### 🚫 Test Validation (should return 400 error)
```bash
curl -X POST http://localhost:8080/api/students \
     -H "Content-Type: application/json" \
     -d '{"firstName": "", "email": "bad-email", "grade": 99}'
```

---

## 🗄️ H2 Database Console (Visual DB Browser)

1. Open: **http://localhost:8080/h2-console**
2. Fill in:
   - **JDBC URL**: `jdbc:h2:mem:studentdb`
   - **Username**: `sa`
   - **Password**: *(leave blank)*
3. Click **Connect**
4. Run SQL: `SELECT * FROM STUDENTS;`

---

## 🧪 Run Tests

```bash
mvn test
```

Expected output:
```
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

---

## 📦 Build Executable JAR

```bash
mvn clean package
java -jar target/student-api-1.0.0.jar
```

---

## 📡 API Reference

| Method | URL | Description |
|--------|-----|-------------|
| GET | `/api/students` | Get all students |
| GET | `/api/students?search=name` | Search by name |
| GET | `/api/students?grade=10` | Filter by grade |
| GET | `/api/students/{id}` | Get student by ID |
| POST | `/api/students` | Create new student |
| PUT | `/api/students/{id}` | Update student |
| DELETE | `/api/students/{id}` | Delete student |

---

## 📘 Key Concepts Explained

| Annotation | What it does |
|-----------|--------------|
| `@SpringBootApplication` | Marks entry point, enables auto-config |
| `@RestController` | Marks class as REST API controller |
| `@GetMapping` / `@PostMapping` | Maps HTTP method to Java method |
| `@PathVariable` | Extracts `{id}` from URL |
| `@RequestBody` | Parses JSON body into Java object |
| `@Entity` | Maps Java class to database table |
| `@Service` | Marks business logic class |
| `@Repository` | Marks data access class |
| `@Autowired` | Injects Spring-managed beans |
| `@Valid` | Triggers input validation |

---

## 🔧 Common Issues

| Problem | Solution |
|---------|----------|
| `Port 8080 already in use` | Change `server.port=8081` in application.properties |
| `java: command not found` | Install JDK 17 and add to PATH |
| `mvn: command not found` | Install Maven and add to PATH |
| Build fails | Run `mvn clean install -U` to force update |
