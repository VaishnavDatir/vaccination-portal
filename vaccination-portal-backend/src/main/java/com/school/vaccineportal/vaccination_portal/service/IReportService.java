package com.school.vaccineportal.vaccination_portal.service;

import java.util.List;

import com.school.vaccineportal.vaccination_portal.dto.VaccinationReportDto;

public interface IReportService {
    public List<VaccinationReportDto> getVaccinationReport(String vaccineName, String grade);

    public int getVaccinationReportCount(String vaccineName);
}
