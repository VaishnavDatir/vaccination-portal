package com.school.vaccineportal.vaccination_portal.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TVaccinationDrivesDto {
    private Integer driveId;
    private String vaccineName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer availableDoses;
    private String gradeEligibility;
    private LocalDateTime updateTs;

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

    public Integer getAvailableDoses() {
        return availableDoses;
    }

    public void setAvailableDoses(Integer availableDoses) {
        this.availableDoses = availableDoses;
    }

    public String getGradeEligibility() {
        return gradeEligibility;
    }

    public void setGradeEligibility(String gradeEligibility) {
        this.gradeEligibility = gradeEligibility;
    }

    public LocalDateTime getUpdateTs() {
        return updateTs;
    }

    public void setUpdateTs(LocalDateTime updateTs) {
        this.updateTs = updateTs;
    }

    @Override
    public String toString() {
        return "TVaccinationDrivesDto [driveId=" + driveId + ", vaccineName=" + vaccineName + ", startDate=" + startDate
                + ", endDate=" + endDate + ", availableDoses=" + availableDoses + ", gradeEligibility="
                + gradeEligibility + ", updateTs=" + updateTs + "]";
    }

}
