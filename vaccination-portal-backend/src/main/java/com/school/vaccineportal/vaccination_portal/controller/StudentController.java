package com.school.vaccineportal.vaccination_portal.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.vaccineportal.vaccination_portal.dto.TStudentsDto;
import com.school.vaccineportal.vaccination_portal.model.ApiResponse;
import com.school.vaccineportal.vaccination_portal.service.IStudentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Student Management APIs")
@RestController
@RequestMapping("/api/students")
public class StudentController {
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
    private final IStudentService studentService;

    StudentController(IStudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    @ApiOperation(value = "Add a new student")
    public ResponseEntity<ApiResponse<Void>> addStudent(@RequestBody TStudentsDto studentDto) {
        logger.info("Request to add student: {}", studentDto.getRollNo());
        studentService.addStudent(studentDto);

        ApiResponse<Void> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Student added successfully");
        response.setStatus(HttpStatus.CREATED);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get student by ID")
    public ResponseEntity<ApiResponse<TStudentsDto>> getStudentById(@PathVariable("id") Integer id) {
        logger.info("Fetching student with ID: {}", id);
        TStudentsDto student = studentService.getStudentById(id);

        ApiResponse<TStudentsDto> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Student fetched successfully");
        response.setData(student);
        response.setStatus(HttpStatus.OK);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    @ApiOperation(value = "Get all students")
    public ResponseEntity<ApiResponse<List<TStudentsDto>>> getAllStudents() {
        logger.info("Fetching all students");
        List<TStudentsDto> students = studentService.getAllStudents();

        ApiResponse<List<TStudentsDto>> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("All students fetched");
        response.setData(students);
        response.setStatus(HttpStatus.OK);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a student by ID")
    public ResponseEntity<ApiResponse<Void>> updateStudent(
            @PathVariable("id") Integer id,
            @RequestBody TStudentsDto studentDto) {

        logger.info("Updating student with ID: {}", id);
        studentDto.setStudentId(id); // Ensure ID is passed from path
        studentService.updateStudent(studentDto);

        ApiResponse<Void> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Student updated successfully");
        response.setStatus(HttpStatus.OK);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a student by ID")
    public ResponseEntity<ApiResponse<Void>> deleteStudent(@PathVariable("id") Integer id) {
        logger.info("Deleting student with ID: {}", id);
        studentService.deleteStudent(id);

        ApiResponse<Void> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Student deleted successfully");
        response.setStatus(HttpStatus.OK);

        return ResponseEntity.ok(response);
    }
}
