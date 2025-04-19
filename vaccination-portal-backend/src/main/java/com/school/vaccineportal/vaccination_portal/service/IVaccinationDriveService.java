package com.school.vaccineportal.vaccination_portal.service;

import java.util.List;

import com.school.vaccineportal.vaccination_portal.dto.TVaccinationDrivesDto;

public interface IVaccinationDriveService {
    public int addVaccinationDrive(TVaccinationDrivesDto vaccinationDrive);

    // Get all vaccination drives
    public List<TVaccinationDrivesDto> getAllVaccinationDrives();

    // Get vaccination drive by ID
    public TVaccinationDrivesDto getVaccinationDriveById(int driveId);

    // Update vaccination drive by ID
    public int updateVaccinationDrive(int driveId, TVaccinationDrivesDto vaccinationDrive);

    // Delete a vaccination drive by ID
    public int deleteVaccinationDrive(int driveId);
}
