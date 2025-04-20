package com.school.vaccineportal.vaccination_portal.dto;

import java.time.LocalDate;

public class VaccinationReportDto {
    private String firstName;
    private String lastName;
    private String grade;
    private String rollNo;
    private String email;
    private String vaccineName;
    private LocalDate vaccinationDate;
    private String status;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public LocalDate getVaccinationDate() {
        return vaccinationDate;
    }

    public void setVaccinationDate(LocalDate vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "VaccinationReportDto [firstName=" + firstName + ", lastName=" + lastName + ", grade=" + grade
                + ", rollNo=" + rollNo + ", email=" + email + ", vaccineName=" + vaccineName + ", vaccinationDate="
                + vaccinationDate + ", status=" + status + "]";
    }

}
