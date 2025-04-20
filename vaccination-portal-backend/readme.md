# School Vaccination Portal Backend Service

## Overview

The **School Vaccination Portal** is a backend service that helps manage students, vaccination drives, and vaccination records. It provides APIs to handle operations like adding students, creating vaccination drives, tracking student vaccination statuses, and generating vaccination reports.

## Technologies Used

- **Backend Framework:** Spring Boot (Java)
- **Database:** MySQL
- **API Documentation:** Swagger
- **Data Format:** JSON
- **Testing:** Postman
- **Build Tool:** Maven

---

## Key Modules

### 1. **Student Management**

- Add, view, update, and delete student records.
- Endpoint: `/api/students`

### 2. **Vaccination Drives**

- Create, update, list, and manage vaccination drives.
- Endpoint: `/api/drives`

### 3. **Student Vaccination Records**

- Track vaccination records for each student.
- Endpoint: `/api/vaccination-records`

### 4. **Dashboard**

- Provides summarized metrics and reports for the system.
- Endpoint: `/api/dashboard`

### 5. **Bulk Upload (CSV)**

- Bulk upload students using a CSV file.
- Endpoint: `/api/upload`

### 6. **Reports**

- Generate and export vaccination reports based on various criteria.
- Endpoint: `/api/reports`

---

## DAOs (Data Access Objects)

The DAOs handle all database interactions, using JDBC templates and parameterized queries to interact with MySQL.

- **StudentDao**

  - Handles queries related to students like adding a new student, fetching student details, etc.
  - Example query: `INSERT INTO students (first_name, last_name, grade, email, roll_no) VALUES (:firstName, :lastName, :grade, :email, :rollNo)`

- **VaccinationDriveDao**

  - Handles queries related to vaccination drives.
  - Example query: `INSERT INTO vaccination_drives (vaccine_name, start_date, end_date, available_doses, grade_eligibility) VALUES (:vaccineName, :startDate, :endDate, :availableDoses, :gradeEligibility)`

- **VaccinationRecordDao**

  - Manages queries related to student vaccination records.
  - Example query: `INSERT INTO vaccination_records (student_id, drive_id, vaccination_date, dose_number, status) VALUES (:studentId, :driveId, :vaccinationDate, :doseNumber, :status)`

- **DashboardDao**
  - Retrieves data for generating dashboard reports, such as total students, vaccinated students, and vaccination drive statuses.

---

## DTOs (Data Transfer Objects)

DTOs are used to transfer data between the backend and frontend.

- **TStudentsDto**

  - Represents student details.
  - Fields: `studentId`, `firstName`, `lastName`, `email`, `rollNo`, `grade`, etc.

- **TVaccinationDrivesDto**

  - Represents vaccination drive details.
  - Fields: `driveId`, `vaccineName`, `startDate`, `endDate`, `availableDoses`, `gradeEligibility`, etc.

- **TStudentVaccinationRecordDto**

  - Represents a student's vaccination record.
  - Fields: `recordId`, `studentId`, `driveId`, `vaccinationDate`, `doseNumber`, `status`, etc.

- **VaccinationReportDto**
  - Represents the vaccination report data, such as the count of vaccinated students, vaccination status, and drive information.

---

## Controllers

Controllers handle incoming HTTP requests and route them to the appropriate service methods.

- **StudentController**

  - Endpoints for student management.
  - `POST /api/students`: Add a new student
  - `GET /api/students`: Get all students
  - `GET /api/students/{id}`: Get student by ID
  - `PUT /api/students/{id}`: Update student details
  - `DELETE /api/students/{id}`: Delete student

- **VaccinationDriveController**

  - Endpoints for managing vaccination drives.
  - `POST /api/drives`: Create a new vaccination drive
  - `GET /api/drives`: List all vaccination drives
  - `GET /api/drives/{id}`: Get vaccination drive by ID
  - `PUT /api/drives/{id}`: Update vaccination drive details
  - `DELETE /api/drives/{id}`: Delete vaccination drive

- **VaccinationRecordController**

  - Endpoints for managing vaccination records.
  - `POST /api/vaccination-records`: Add vaccination record
  - `GET /api/vaccination-records/{studentId}`: Get vaccination records for a student

- **DashboardController**

  - Endpoints for fetching summarized data and metrics.
  - `GET /api/dashboard`: Get dashboard metrics (e.g., vaccinated students, remaining doses)

- **BulkUploadController**
  - Endpoints for handling bulk uploads.
  - `POST /api/upload`: Upload student CSV file

---

## Error Handling

Global error handling is implemented via `@ControllerAdvice` in `GlobalExceptionHandler`. Common exceptions handled include:

- **DataIntegrityViolationException**: Handles unique constraint violations (e.g., duplicate students).
- **MethodArgumentNotValidException**: Handles validation errors in request bodies.
- **AccessDeniedException**: Handles unauthorized access attempts.
- **Exception**: A fallback handler for other exceptions.

---

## Swagger API Documentation

The project uses Swagger for API documentation. You can access the interactive Swagger UI at: http://localhost:9091/swagger-ui/

Swagger provides detailed documentation for all available endpoints, including request parameters, responses, and examples.

---

## Project structure

├── src
│ ├── main
│ │ ├── java
│ │ │ └── com
│ │ │ └── school
│ │ │ └── vaccineportal
│ │ │ └── vaccination_portal
│ │ │ ├── controller # Controller layer: Handles HTTP requests and routes them to service methods
│ │ │ │ ├── DashboardController.java # Manages dashboard-related requests
│ │ │ │ ├── StudentController.java # Handles student-related requests
│ │ │ │ ├── VaccinationDriveController.java # Handles vaccination drive-related requests
│ │ │ │ └── StudentVaccinationRecordController.java # Handles student vaccination record requests
│ │ │ ├── dao # Data Access Object layer: Communicates with the database to fetch or save data
│ │ │ │ ├── DashboardDao.java # Data access methods for dashboard
│ │ │ │ ├── StudentDao.java # Data access methods for students
│ │ │ │ ├── VaccinationDriveDao.java # Data access methods for vaccination drives
│ │ │ │ └── StudentVaccinationRecordDao.java # Data access methods for student vaccination records
│ │ │ ├── dto # Data Transfer Object layer: Contains DTOs to transfer data between layers
│ │ │ │ ├── DashboardReportDto.java # DTO for dashboard report data
│ │ │ │ ├── StudentDto.java # DTO for student data
│ │ │ │ ├── VaccinationDriveDto.java # DTO for vaccination drive data
│ │ │ │ └── StudentVaccinationRecordDto.java # DTO for student vaccination record data
│ │ │ ├── exception # Custom exception classes and global exception handler
│ │ │ │ ├── GlobalExceptionHandler.java # Global exception handling for API errors
│ │ │ │ ├── InvalidInputException.java # Custom exception for invalid input errors
│ │ │ │ └── ResourceNotFoundException.java # Custom exception for resource not found errors
│ │ │ ├── model # Model classes: Basic classes that define the response structures
│ │ │ │ ├── ApiResponse.java # Wrapper class for API responses (success/failure)
│ │ │ │ └── VaccinationReportDto.java # DTO for vaccination reports
│ │ │ ├── service # Service layer: Contains the business logic of the application
│ │ │ │ ├── DashboardService.java # Dashboard-related business logic
│ │ │ │ ├── StudentService.java # Student-related business logic
│ │ │ │ ├── VaccinationDriveService.java # Vaccination drive business logic
│ │ │ │ └── StudentVaccinationRecordService.java # Student vaccination record business logic
│ │ │ ├── VaccinationPortalApplication.java # Main Spring Boot application entry point
│ │ └── resources
│ │ ├── application.properties # Configuration file for application properties (e.g., database connection settings)
│ │ ├── database-queries.xml # SQL queries for database interactions (queries are fetched by ID)
│ │ └── swagger-config.yml # Configuration file for Swagger documentation
├── target # Compiled and generated files (generated by Maven)
│ ├── classes
│ └── test-classes
├── pom.xml # Maven build configuration file
└── README.md # Project documentation
