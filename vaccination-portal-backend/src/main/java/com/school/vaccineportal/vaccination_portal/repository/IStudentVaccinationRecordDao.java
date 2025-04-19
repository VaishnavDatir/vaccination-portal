package com.school.vaccineportal.vaccination_portal.repository;

import java.util.List;

import com.school.vaccineportal.vaccination_portal.dto.TStudentVaccinationRecordDto;

public interface IStudentVaccinationRecordDao {
    // Add vaccination record
    public int addVaccinationRecord(TStudentVaccinationRecordDto recordDto);

    // Get records by student ID
    public List<TStudentVaccinationRecordDto> getVaccinationRecordsByStudentId(Integer studentId);

    // Update vaccination record
    public int updateVaccinationRecord(TStudentVaccinationRecordDto recordDto);

    // Delete vaccination record
    public int deleteVaccinationRecord(Integer recordId);
}
