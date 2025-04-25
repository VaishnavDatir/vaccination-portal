export class VaccinationRecord {
  constructor(
    studentId,
    driveId,
    vaccinationDate,
    doseNumber,
    status = "VACCINATED"
  ) {
    this.studentId = studentId;
    this.driveId = driveId;
    this.vaccinationDate = vaccinationDate;
    this.doseNumber = doseNumber;
    this.status = status;
  }
}
