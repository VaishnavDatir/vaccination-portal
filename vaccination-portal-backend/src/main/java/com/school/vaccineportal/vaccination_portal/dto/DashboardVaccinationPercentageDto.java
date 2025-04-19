package com.school.vaccineportal.vaccination_portal.dto;

public class DashboardVaccinationPercentageDto {
    private String grade;
    private Double vaccinationPercentage;

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Double getVaccinationPercentage() {
        return vaccinationPercentage;
    }

    public void setVaccinationPercentage(Double vaccinationPercentage) {
        this.vaccinationPercentage = vaccinationPercentage;
    }

    @Override
    public String toString() {
        return "VaccinationPercentageDto [grade=" + grade + ", vaccinationPercentage=" + vaccinationPercentage + "]";
    }

}
