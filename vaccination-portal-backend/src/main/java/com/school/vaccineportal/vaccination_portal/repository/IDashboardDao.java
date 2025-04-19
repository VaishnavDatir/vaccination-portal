package com.school.vaccineportal.vaccination_portal.repository;

import java.util.List;

import com.school.vaccineportal.vaccination_portal.dto.DashboardVaccinationDriveDto;
import com.school.vaccineportal.vaccination_portal.dto.DashboardVaccinationPercentageDto;

public interface IDashboardDao {
    // Fetch Total Students
    public int getTotalStudents();

    // Fetch Total Vaccinated Students
    public int getTotalVaccinated();

    // Fetch Vaccinated Percentage by Grade
    public List<DashboardVaccinationPercentageDto> getVaccinatedPercentageByGrade();

    // Fetch Upcoming Vaccination Drives
    public List<DashboardVaccinationDriveDto> getUpcomingDrives();
}
