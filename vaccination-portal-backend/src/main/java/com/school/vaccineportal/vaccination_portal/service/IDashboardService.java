package com.school.vaccineportal.vaccination_portal.service;

import java.util.List;

import com.school.vaccineportal.vaccination_portal.dto.DashboardSummaryDto;
import com.school.vaccineportal.vaccination_portal.dto.DashboardVaccinationDriveDto;
import com.school.vaccineportal.vaccination_portal.dto.DashboardVaccinationPercentageDto;

public interface IDashboardService {
    public DashboardSummaryDto getCompleteDashboardSummaryDto();

    // Fetch Total Students
    public int getTotalStudents();

    // Fetch Total Vaccinated Students
    public int getTotalVaccinated();

    // Fetch Vaccinated Percentage by Grade
    public List<DashboardVaccinationPercentageDto> getVaccinatedPercentageByGrade();

    // Fetch Upcoming Vaccination Drives
    public List<DashboardVaccinationDriveDto> getUpcomingDrives();
}
