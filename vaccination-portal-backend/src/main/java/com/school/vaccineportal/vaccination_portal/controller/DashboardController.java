package com.school.vaccineportal.vaccination_portal.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.vaccineportal.vaccination_portal.dto.DashboardSummaryDto;
import com.school.vaccineportal.vaccination_portal.model.ApiResponse;
import com.school.vaccineportal.vaccination_portal.service.IDashboardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/dashboard")
@Api(tags = "Dashboard", value = "APIs to manage dashboard data")
public class DashboardController {
    private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);
    private final IDashboardService dashboardService;

    DashboardController(IDashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    // Get Dashboard Summary
    @GetMapping("/summary")
    @ApiOperation(value = "Get Dashboard Summary", notes = "Fetch total students, total vaccinated students, vaccinated percentage by grade, and upcoming drives.")
    public ResponseEntity<ApiResponse<DashboardSummaryDto>> getDashboardSummary() {
        DashboardSummaryDto dashboardSummary = dashboardService.getCompleteDashboardSummaryDto();
        return ResponseEntity.ok(ApiResponse.success(dashboardSummary, HttpStatus.OK));
    }
}
