package com.school.vaccineportal.vaccination_portal.service.implementation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.school.vaccineportal.vaccination_portal.dto.DashboardSummaryDto;
import com.school.vaccineportal.vaccination_portal.dto.DashboardVaccinationDriveDto;
import com.school.vaccineportal.vaccination_portal.dto.DashboardVaccinationPercentageDto;
import com.school.vaccineportal.vaccination_portal.repository.IDashboardDao;
import com.school.vaccineportal.vaccination_portal.service.IDashboardService;

@Service
public class DashboardService implements IDashboardService {
    private static final Logger logger = LoggerFactory.getLogger(DashboardService.class);

    private final IDashboardDao dashboardDao;

    DashboardService(IDashboardDao dashboardDao) {
        this.dashboardDao = dashboardDao;
    }

    @Override
    public DashboardSummaryDto getCompleteDashboardSummaryDto() {
        DashboardSummaryDto dashboardSummary = new DashboardSummaryDto();

        dashboardSummary.setTotalStudents(getTotalStudents());
        dashboardSummary.setTotalVaccinated(getTotalVaccinated());
        dashboardSummary.setVaccinatedPercentageByGrade(getVaccinatedPercentageByGrade());
        dashboardSummary.setUpcomingVaccinationDrives(getUpcomingDrives());
        return dashboardSummary;
    }

    // Fetch Total Students
    @Override
    public int getTotalStudents() {
        return dashboardDao.getTotalStudents();
    }

    // Fetch Total Vaccinated Students
    @Override
    public int getTotalVaccinated() {
        return dashboardDao.getTotalVaccinated();
    }

    // Fetch Vaccinated Percentage by Grade
    @Override
    public List<DashboardVaccinationPercentageDto> getVaccinatedPercentageByGrade() {
        return dashboardDao.getVaccinatedPercentageByGrade();
    }

    // Fetch Upcoming Vaccination Drives
    @Override
    public List<DashboardVaccinationDriveDto> getUpcomingDrives() {
        return dashboardDao.getUpcomingDrives();
    }
}
