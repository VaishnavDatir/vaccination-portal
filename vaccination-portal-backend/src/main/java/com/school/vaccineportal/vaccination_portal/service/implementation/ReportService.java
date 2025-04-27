package com.school.vaccineportal.vaccination_portal.service.implementation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.school.vaccineportal.vaccination_portal.dto.VaccinationReportDto;
import com.school.vaccineportal.vaccination_portal.repository.IReportDao;
import com.school.vaccineportal.vaccination_portal.service.IReportService;

@Service
public class ReportService implements IReportService {
    private static final Logger logger = LoggerFactory.getLogger(ReportService.class);

    private final IReportDao reportDao;

    ReportService(IReportDao reportDao) {
        this.reportDao = reportDao;
    }

    public List<VaccinationReportDto> getVaccinationReport(String vaccineName, String grade) {
        return reportDao.getVaccinationReport(vaccineName, grade);
    }

    public int getVaccinationReportCount(String vaccineName) {
        return reportDao.getVaccinationReportCount(vaccineName);
    }
}
