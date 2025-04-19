package com.school.vaccineportal.vaccination_portal.dto;

import java.time.LocalDate;

public class DashboardVaccinationDriveDto {
    private Integer driveId;
    private String vaccineName;
    private LocalDate startDate;
    private LocalDate endDate;

    public Integer getDriveId() {
        return driveId;
    }

    public void setDriveId(Integer driveId) {
        this.driveId = driveId;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "VaccinationDriveDto [driveId=" + driveId + ", vaccineName=" + vaccineName + ", startDate=" + startDate
                + ", endDate=" + endDate + "]";
    }

}
