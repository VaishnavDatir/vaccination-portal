package com.school.vaccineportal.vaccination_portal.service;

import java.util.List;

import com.school.vaccineportal.vaccination_portal.dto.TStudentVaccinationRecordDto;

public interface IStudentVaccinationRecordService {
    // Add a vaccination record
    public int addVaccinationRecord(TStudentVaccinationRecordDto recordDto);

    // Get all vaccination records for a student
    public List<TStudentVaccinationRecordDto> getVaccinationRecordsByStudentId(Integer studentId);

    // Update a vaccination record
    public int updateVaccinationRecord(TStudentVaccinationRecordDto recordDto);

    // Delete a vaccination record
    public int deleteVaccinationRecord(Integer recordId);
}
