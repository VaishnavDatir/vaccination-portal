package com.school.vaccineportal.vaccination_portal.model;

import java.time.LocalDate;

public class VaccinationDriveModel {
    private Integer id;
    private String vaccineName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer availableDoses;
    private String applicableGrades;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getApplicableGrades() {
        return applicableGrades;
    }

    public void setApplicableGrades(String applicableGrades) {
        this.applicableGrades = applicableGrades;
    }

    @Override
    public String toString() {
        return "VaccinationDriveModel [id=" + id + ", vaccineName=" + vaccineName + ", startDate=" + startDate
                + ", endDate=" + endDate + ", availableDoses=" + availableDoses + ", applicableGrades="
                + applicableGrades + "]";
    }

}
