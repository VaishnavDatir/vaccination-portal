package com.school.vaccineportal.vaccination_portal.repository;

import java.util.List;
import java.util.Optional;

import com.school.vaccineportal.vaccination_portal.dto.TVaccinationDrivesDto;

public interface IVaccinationDriveDao {
    public int addVaccinationDrive(TVaccinationDrivesDto drive);

    // Get a vaccination drive by ID
    public Optional<TVaccinationDrivesDto> getVaccinationDriveById(Integer driveId);

    // Get all vaccination drives
    public List<TVaccinationDrivesDto> getAllVaccinationDrives(int offset, int limit);

    public List<TVaccinationDrivesDto> getAllActiveVaccinationDrives(int grade);

    // Update a vaccination drive
    public int updateVaccinationDrive(TVaccinationDrivesDto drive);

    // Delete a vaccination drive
    public int deleteVaccinationDrive(Integer driveId);

    public Integer getTotalVaccinationDrivesCount();
}
