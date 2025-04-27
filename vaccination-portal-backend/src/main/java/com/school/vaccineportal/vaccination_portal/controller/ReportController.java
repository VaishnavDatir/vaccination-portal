package com.school.vaccineportal.vaccination_portal.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.vaccineportal.vaccination_portal.dto.VaccinationReportDto;
import com.school.vaccineportal.vaccination_portal.model.ApiResponse;
import com.school.vaccineportal.vaccination_portal.service.IReportService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/reports")
@Api(tags = "Vaccination Reports", description = "APIs for generating vaccination reports")
public class ReportController {
    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);
    private final IReportService reportService;

    ReportController(IReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/vaccination")
    @ApiOperation(value = "Get Vaccination Report", notes = "Fetch list of vaccinated students with filters and pagination")
    public ResponseEntity<ApiResponse<List<VaccinationReportDto>>> getVaccinationReport(
            @ApiParam(value = "Vaccine Name for filtering", required = false) @RequestParam(required = false) String vaccineName,
            @ApiParam(value = "Grade for filtering", required = false) @RequestParam(required = false) String grade) {
        List<VaccinationReportDto> reports = reportService.getVaccinationReport(vaccineName, grade);

        return new ResponseEntity<>(
                ApiResponse.success(reports, HttpStatus.OK), HttpStatus.OK);
    }
}
