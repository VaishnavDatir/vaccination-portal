package com.school.vaccineportal.vaccination_portal.dto;

import java.time.LocalDateTime;

public class TStudentsDto {
    private Integer studentId;
    private String firstName;
    private String lastName;
    private String gender;
    private String grade;
    private String email;
    private String rollNo;
    private LocalDateTime updateTs;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public LocalDateTime getUpdateTs() {
        return updateTs;
    }

    public void setUpdateTs(LocalDateTime updateTs) {
        this.updateTs = updateTs;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "TStudentsDto [studentId=" + studentId + ", firstName=" + firstName + ", lastName=" + lastName
                + ", gender=" + gender + ", grade=" + grade + ", email=" + email + ", rollNo=" + rollNo + ", updateTs="
                + updateTs + "]";
    }

}
