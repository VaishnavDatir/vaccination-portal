package com.school.vaccineportal.vaccination_portal.repository;

import java.util.List;

import com.school.vaccineportal.vaccination_portal.dto.VaccinationReportDto;

public interface IReportDao {
    public List<VaccinationReportDto> getVaccinationReport(String vaccineName, String grade);

    public int getVaccinationReportCount(String vaccineName);
}
