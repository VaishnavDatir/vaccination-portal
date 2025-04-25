import React, { useState } from "react";
import { VaccinationRecord } from "../../../models/VaccinationRecordModel";
import { useVaccinationViewModel } from "./useVaccinationViewModel";

export default function AddVaccinationForm({ studentId, driveId }) {
  const { addVaccination, loading, message } = useVaccinationViewModel();

  const [doseNumber, setDoseNumber] = useState(1);

  const handleSubmit = async (e) => {
    e.preventDefault();
    const today = new Date().toISOString().split("T")[0];
    const record = new VaccinationRecord(studentId, driveId, today, doseNumber);
    await addVaccination(record);
  };

  return (
    <form onSubmit={handleSubmit} className="bg-white p-3 rounded shadow-sm">
      <h5>Add Vaccination</h5>
      <div className="mb-3">
        <label className="form-label">Dose Number</label>
        <input
          type="number"
          min="1"
          className="form-control"
          value={doseNumber}
          onChange={(e) => setDoseNumber(Number(e.target.value))}
          required
        />
      </div>
      <button type="submit" className="btn btn-primary" disabled={loading}>
        💉 Add Vaccination
      </button>
      {message && <div className="mt-2 text-muted">{message}</div>}
    </form>
  );
}
