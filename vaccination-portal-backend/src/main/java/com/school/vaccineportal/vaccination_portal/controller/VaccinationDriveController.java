package com.school.vaccineportal.vaccination_portal.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.vaccineportal.vaccination_portal.dto.TVaccinationDrivesDto;
import com.school.vaccineportal.vaccination_portal.model.ApiResponse;
import com.school.vaccineportal.vaccination_portal.service.IVaccinationDriveService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Vaccination Drives API", value = "Operations related to vaccination drives")
@RestController
@RequestMapping("/api/vaccination-drives")
public class VaccinationDriveController {
    private static final Logger logger = LoggerFactory.getLogger(VaccinationDriveController.class);
    private final IVaccinationDriveService vaccinationDriveService;

    VaccinationDriveController(IVaccinationDriveService vaccinationDriveService) {
        this.vaccinationDriveService = vaccinationDriveService;
    }

    @ApiOperation(value = "Add a new vaccination drive", response = TVaccinationDrivesDto.class)
    @PostMapping
    public ApiResponse<TVaccinationDrivesDto> addVaccinationDrive(
            @ApiParam(value = "Vaccination drive details", required = true) @RequestBody @Valid TVaccinationDrivesDto vaccinationDrive) {
        int rowsAffected = vaccinationDriveService.addVaccinationDrive(vaccinationDrive);
        if (rowsAffected > 0) {
            return ApiResponse.success(vaccinationDrive, HttpStatus.CREATED);
        } else {
            return ApiResponse.error("Failed to create vaccination drive", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Get all vaccination drives with pagination", response = List.class)
    @GetMapping
    public ApiResponse<List<TVaccinationDrivesDto>> getAllVaccinationDrives(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        List<TVaccinationDrivesDto> drives = vaccinationDriveService.getAllVaccinationDrives(page, size);
        int totalCount = vaccinationDriveService.getTotalVaccinationDrivesCount();

        ApiResponse<List<TVaccinationDrivesDto>> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Vaccination drives fetched with pagination");
        response.setData(drives);
        response.setStatus(HttpStatus.OK);
        response.setTotalCount(totalCount);

        return response;
    }

    @ApiOperation(value = "Get vaccination drive by ID", response = TVaccinationDrivesDto.class)
    @GetMapping("/{driveId}")
    public ApiResponse<TVaccinationDrivesDto> getVaccinationDriveById(
            @ApiParam(value = "ID of the vaccination drive", required = true) @PathVariable int driveId) {
        TVaccinationDrivesDto vaccinationDrive = vaccinationDriveService.getVaccinationDriveById(driveId);
        return ApiResponse.success(vaccinationDrive, HttpStatus.OK);
    }

    @ApiOperation(value = "Update vaccination drive by ID", response = TVaccinationDrivesDto.class)
    @PutMapping("/{driveId}")
    public ApiResponse<TVaccinationDrivesDto> updateVaccinationDrive(
            @ApiParam(value = "ID of the vaccination drive", required = true) @PathVariable int driveId,
            @ApiParam(value = "Vaccination drive details", required = true) @RequestBody @Valid TVaccinationDrivesDto vaccinationDrive) {
        vaccinationDrive.setDriveId(driveId); // Ensure the drive ID is correctly set in the DTO
        int rowsAffected = vaccinationDriveService.updateVaccinationDrive(driveId, vaccinationDrive);
        if (rowsAffected > 0) {
            return ApiResponse.success(vaccinationDrive, HttpStatus.OK);
        } else {
            return ApiResponse.error("Failed to update vaccination drive", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Delete vaccination drive by ID", response = ApiResponse.class)
    @DeleteMapping("/{driveId}")
    public ApiResponse<Void> deleteVaccinationDrive(
            @ApiParam(value = "ID of the vaccination drive to delete", required = true) @PathVariable int driveId) {
        int rowsAffected = vaccinationDriveService.deleteVaccinationDrive(driveId);
        if (rowsAffected > 0) {
            return ApiResponse.success(null, HttpStatus.NO_CONTENT);
        } else {
            return ApiResponse.error("Failed to delete vaccination drive", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
