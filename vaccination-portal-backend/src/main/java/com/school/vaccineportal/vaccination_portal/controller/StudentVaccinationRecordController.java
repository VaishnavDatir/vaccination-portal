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

import com.school.vaccineportal.vaccination_portal.dto.TStudentVaccinationRecordDto;
import com.school.vaccineportal.vaccination_portal.model.ApiResponse;
import com.school.vaccineportal.vaccination_portal.service.IStudentVaccinationRecordService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/vaccination-records")
@Api(tags = "Student Vaccination Records", value = "APIs to manage student vaccination records")
public class StudentVaccinationRecordController {
    private static final Logger logger = LoggerFactory.getLogger(StudentVaccinationRecordController.class);
    private final IStudentVaccinationRecordService studentVaccinationRecordService;

    StudentVaccinationRecordController(IStudentVaccinationRecordService studentVaccinationRecordService) {
        this.studentVaccinationRecordService = studentVaccinationRecordService;
    }

    @ApiOperation(value = "Add a vaccination record for a student")
    @PostMapping
    public ResponseEntity<ApiResponse<String>> addVaccinationRecord(
            @RequestBody TStudentVaccinationRecordDto recordDto) {
        studentVaccinationRecordService.addVaccinationRecord(recordDto);
        return new ResponseEntity<>(ApiResponse.success(null, HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get all vaccination records for a student")
    @GetMapping("/student/{studentId}")
    public ResponseEntity<ApiResponse<List<TStudentVaccinationRecordDto>>> getRecordsByStudentId(
            @ApiParam(value = "ID of the student", required = true) @PathVariable Integer studentId) {
        List<TStudentVaccinationRecordDto> records = studentVaccinationRecordService
                .getVaccinationRecordsByStudentId(studentId);
        return new ResponseEntity<>(ApiResponse.success(records, HttpStatus.OK), HttpStatus.OK);
    }

    @ApiOperation(value = "Update an existing vaccination record")
    @PutMapping
    public ResponseEntity<ApiResponse<String>> updateVaccinationRecord(
            @RequestBody TStudentVaccinationRecordDto recordDto) {
        studentVaccinationRecordService.updateVaccinationRecord(recordDto);
        return new ResponseEntity<>(ApiResponse.success(null, HttpStatus.OK), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a vaccination record by its ID")
    @DeleteMapping("/{recordId}")
    public ResponseEntity<ApiResponse<String>> deleteVaccinationRecord(
            @ApiParam(value = "Vaccination record ID", required = true) @PathVariable Integer recordId) {
        studentVaccinationRecordService.deleteVaccinationRecord(recordId);
        return new ResponseEntity<>(ApiResponse.success(null, HttpStatus.NO_CONTENT), HttpStatus.NO_CONTENT);
    }
}
