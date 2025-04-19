package com.school.vaccineportal.vaccination_portal.service.implementation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.school.vaccineportal.vaccination_portal.dto.TStudentVaccinationRecordDto;
import com.school.vaccineportal.vaccination_portal.repository.IStudentVaccinationRecordDao;
import com.school.vaccineportal.vaccination_portal.service.IStudentVaccinationRecordService;

@Service
public class StudentVaccinationRecordService implements IStudentVaccinationRecordService {
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final IStudentVaccinationRecordDao studentVaccinationRecordDao;

    StudentVaccinationRecordService(IStudentVaccinationRecordDao studentVaccinationRecordDao) {
        this.studentVaccinationRecordDao = studentVaccinationRecordDao;
    }

    // Add a vaccination record
    @Override
    public int addVaccinationRecord(TStudentVaccinationRecordDto recordDto) {
        logger.debug("Adding vaccination record for student ID: {}", recordDto.getStudentId());
        return studentVaccinationRecordDao.addVaccinationRecord(recordDto);
    }

    // Get all vaccination records for a student
    @Override
    public List<TStudentVaccinationRecordDto> getVaccinationRecordsByStudentId(Integer studentId) {
        logger.debug("Fetching vaccination records for student ID: {}", studentId);
        return studentVaccinationRecordDao.getVaccinationRecordsByStudentId(studentId);
    }

    // Update a vaccination record
    @Override
    public int updateVaccinationRecord(TStudentVaccinationRecordDto recordDto) {
        logger.debug("Updating vaccination record for record ID: {}", recordDto.getRecordId());
        return studentVaccinationRecordDao.updateVaccinationRecord(recordDto);
    }

    // Delete a vaccination record
    @Override
    public int deleteVaccinationRecord(Integer recordId) {
        logger.debug("Deleting vaccination record for record ID: {}", recordId);
        return studentVaccinationRecordDao.deleteVaccinationRecord(recordId);
    }
}
