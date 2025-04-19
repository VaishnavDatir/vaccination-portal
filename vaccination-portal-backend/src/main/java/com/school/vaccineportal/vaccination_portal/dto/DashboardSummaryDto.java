package com.school.vaccineportal.vaccination_portal.dto;

import java.util.List;

public class DashboardSummaryDto {
    private int totalStudents;
    private int totalVaccinated;
    private List<DashboardVaccinationPercentageDto> vaccinatedPercentageByGrade;
    private List<DashboardVaccinationDriveDto> upcomingVaccinationDrives;

    public int getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(int totalStudents) {
        this.totalStudents = totalStudents;
    }

    public int getTotalVaccinated() {
        return totalVaccinated;
    }

    public void setTotalVaccinated(int totalVaccinated) {
        this.totalVaccinated = totalVaccinated;
    }

    public List<DashboardVaccinationPercentageDto> getVaccinatedPercentageByGrade() {
        return vaccinatedPercentageByGrade;
    }

    public void setVaccinatedPercentageByGrade(List<DashboardVaccinationPercentageDto> vaccinatedPercentageByGrade) {
        this.vaccinatedPercentageByGrade = vaccinatedPercentageByGrade;
    }

    public List<DashboardVaccinationDriveDto> getUpcomingVaccinationDrives() {
        return upcomingVaccinationDrives;
    }

    public void setUpcomingVaccinationDrives(List<DashboardVaccinationDriveDto> upcomingVaccinationDrives) {
        this.upcomingVaccinationDrives = upcomingVaccinationDrives;
    }

    @Override
    public String toString() {
        return "DashboardSummaryDto [totalStudents=" + totalStudents + ", totalVaccinated=" + totalVaccinated
                + ", vaccinatedPercentageByGrade=" + vaccinatedPercentageByGrade + ", upcomingVaccinationDrives="
                + upcomingVaccinationDrives + "]";
    }

}
