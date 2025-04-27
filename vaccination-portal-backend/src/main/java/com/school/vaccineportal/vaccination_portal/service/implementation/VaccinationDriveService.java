package com.school.vaccineportal.vaccination_portal.service.implementation;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.school.vaccineportal.vaccination_portal.dto.TVaccinationDrivesDto;
import com.school.vaccineportal.vaccination_portal.exception.ResourceNotFoundException;
import com.school.vaccineportal.vaccination_portal.repository.IVaccinationDriveDao;
import com.school.vaccineportal.vaccination_portal.service.IVaccinationDriveService;

@Service
public class VaccinationDriveService implements IVaccinationDriveService {
    private static final Logger logger = LoggerFactory.getLogger(VaccinationDriveService.class);

    private final IVaccinationDriveDao vaccinationDriveDao;

    VaccinationDriveService(IVaccinationDriveDao vaccinationDriveDao) {
        this.vaccinationDriveDao = vaccinationDriveDao;
    }

    // Add a new vaccination drive
    @Override
    public int addVaccinationDrive(TVaccinationDrivesDto vaccinationDrive) {
        return vaccinationDriveDao.addVaccinationDrive(vaccinationDrive);
    }

    // Get all vaccination drives
    @Override
    public List<TVaccinationDrivesDto> getAllVaccinationDrives(int page, int size) {
        int offset = page * size;
        return vaccinationDriveDao.getAllVaccinationDrives(offset, size);
    }

    @Override
    public List<TVaccinationDrivesDto> getAllActiveVaccinationDrives(int grade) {
        return vaccinationDriveDao.getAllActiveVaccinationDrives(grade);
    }

    @Override
    public Integer getTotalVaccinationDrivesCount() {
        return vaccinationDriveDao.getTotalVaccinationDrivesCount();
    }

    // Get vaccination drive by ID
    @Override
    public TVaccinationDrivesDto getVaccinationDriveById(int driveId) {
        Optional<TVaccinationDrivesDto> drive = vaccinationDriveDao.getVaccinationDriveById(driveId);
        if (!drive.isPresent()) {
            throw new ResourceNotFoundException("Vaccination drive with ID " + driveId + " not found.");
        }
        return drive.get();
    }

    // Update vaccination drive by ID
    @Override
    public int updateVaccinationDrive(int driveId, TVaccinationDrivesDto vaccinationDrive) {
        vaccinationDrive.setDriveId(driveId); // Ensure the ID is set before update
        int rowsAffected = vaccinationDriveDao.updateVaccinationDrive(vaccinationDrive);
        if (rowsAffected == 0) {
            throw new ResourceNotFoundException("Vaccination drive with ID " + driveId + " not found.");
        }
        return rowsAffected;
    }

    // Delete a vaccination drive by ID
    @Override
    public int deleteVaccinationDrive(int driveId) {
        int rowsAffected = vaccinationDriveDao.deleteVaccinationDrive(driveId);
        if (rowsAffected == 0) {
            throw new ResourceNotFoundException("Vaccination drive with ID " + driveId + " not found.");
        }
        return rowsAffected;
    }
}
