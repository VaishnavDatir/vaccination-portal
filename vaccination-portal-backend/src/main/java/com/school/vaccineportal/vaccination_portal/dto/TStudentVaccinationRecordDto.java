package com.school.vaccineportal.vaccination_portal.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TStudentVaccinationRecordDto {
    private Integer recordId;
    private Integer studentId;
    private Integer driveId;
    private LocalDate vaccinationDate;
    private Integer doseNumber;
    private String status;
    private LocalDateTime updateTs;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getDriveId() {
        return driveId;
    }

    public void setDriveId(Integer driveId) {
        this.driveId = driveId;
    }

    public LocalDate getVaccinationDate() {
        return vaccinationDate;
    }

    public void setVaccinationDate(LocalDate vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }

    public Integer getDoseNumber() {
        return doseNumber;
    }

    public void setDoseNumber(Integer doseNumber) {
        this.doseNumber = doseNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getUpdateTs() {
        return updateTs;
    }

    public void setUpdateTs(LocalDateTime updateTs) {
        this.updateTs = updateTs;
    }

    @Override
    public String toString() {
        return "TStudentVaccinationRecordDto [recordId=" + recordId + ", studentId=" + studentId + ", driveId="
                + driveId + ", vaccinationDate=" + vaccinationDate + ", doseNumber=" + doseNumber + ", status=" + status
                + ", updateTs=" + updateTs + "]";
    }

}
