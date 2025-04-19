package com.school.vaccineportal.vaccination_portal.model;

import java.time.LocalDate;

public class StudentModel {
    private Integer id;
    private String name;
    private String rollNo;
    private String grade;
    private boolean vaccinated;
    private LocalDate vaccinationDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public boolean isVaccinated() {
        return vaccinated;
    }

    public void setVaccinated(boolean vaccinated) {
        this.vaccinated = vaccinated;
    }

    public LocalDate getVaccinationDate() {
        return vaccinationDate;
    }

    public void setVaccinationDate(LocalDate vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }

    @Override
    public String toString() {
        return "StudentModel [id=" + id + ", name=" + name + ", rollNo=" + rollNo + ", grade=" + grade + ", vaccinated="
                + vaccinated + ", vaccinationDate=" + vaccinationDate + "]";
    }

}
