import React, { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import axiosInstance from "../../../api/axiosInstance";

export default function AddVaccinationPage() {
  const location = useLocation();
  const { studentId, firstName, lastName, rollNo, grade } =
    location.state || {};

  const navigate = useNavigate();
  const [vaccinations, setVaccinations] = useState([]);
  const [selectedVaccinationId, setSelectedVaccinationId] = useState("");
  const [vaccinationDate, setVaccinationDate] = useState("");
  const [doseNumber, setDoseNumber] = useState(1);
  const [selectedDriveDetails, setSelectedDriveDetails] = useState(null);

  useEffect(() => {
    fetchVaccinationDrives();
  }, []);

  const fetchVaccinationDrives = async () => {
    try {
      const res = await axiosInstance.get(
        `/vaccination-drives/active/${grade}`,
        {
          headers: { Authorization: `Bearer ${localStorage.getItem("token")}` },
        }
      );
      const drives = res.data.data.map((drive) => ({
        ...drive,
        id: drive.driveId,
      }));
      setVaccinations(drives);
    } catch (error) {
      console.error("Failed to fetch vaccination drives", error);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!selectedVaccinationId) {
      alert("❌ Please select a vaccination drive!");
      return;
    }
    try {
      const payload = {
        studentId: studentId,
        driveId: Number(selectedVaccinationId),
        vaccinationDate,
        doseNumber: Number(doseNumber),
        status: "VACCINATED",
      };
      const res = await axiosInstance.post("/vaccination-records", payload, {
        headers: { Authorization: `Bearer ${localStorage.getItem("token")}` },
      });
      if (res.data.success) {
        alert("✅ Vaccination added successfully!");
        navigate("/students"); // Go back to student list
      } else {
        alert("❌ Failed to add vaccination!");
      }
    } catch (error) {
      console.error("Error adding vaccination", error);
      alert("❌ Something went wrong!");
    }
  };

  const handleDriveChange = (e) => {
    const selectedId = e.target.value;
    setSelectedVaccinationId(selectedId);

    const drive = vaccinations.find((d) => d.id === Number(selectedId));

    if (drive) {
      setSelectedDriveDetails(drive);

      const formattedStartDate = drive.startDate.slice(0, 10); // only "YYYY-MM-DD"
      setVaccinationDate(formattedStartDate);
    }
  };

  return (
    <div className="bg-light min-vh-100">
      <NavBar />
      <div className="container py-5">
        <h2 className="text-primary mb-4">💉 Add Vaccination</h2>
        <form
          onSubmit={handleSubmit}
          className="bg-white p-3 rounded shadow-sm"
        >
          <h2>
            {firstName} {lastName} ({rollNo})
          </h2>

          <div className="mb-3">
            <label className="form-label fw-semibold">
              Select Vaccination Drive
            </label>
            <select
              className="form-select"
              value={selectedVaccinationId}
              onChange={handleDriveChange}
            >
              <option value="">-- Select Drive --</option>
              {vaccinations.map((vaccine) => (
                <option key={vaccine.id} value={vaccine.id}>
                  {vaccine.vaccineName} ({vaccine.startDate.slice(0, 10)} -{" "}
                  {vaccine.endDate.slice(0, 10)})
                </option>
              ))}
            </select>
          </div>

          {selectedDriveDetails && (
            <div className="mb-2 text-muted small">
              Drive allowed between:{" "}
              <strong>
                {selectedDriveDetails.startDate.slice(0, 10)} -{" "}
                {selectedDriveDetails.endDate.slice(0, 10)}
              </strong>
            </div>
          )}

          <div className="mb-3">
            <label className="form-label fw-semibold">Vaccination Date</label>
            <input
              type="date"
              className="form-control"
              value={vaccinationDate}
              min={selectedDriveDetails?.startDate.slice(0, 10) || ""}
              max={selectedDriveDetails?.endDate.slice(0, 10) || ""}
              onChange={(e) => {
                const selectedDate = e.target.value;
                const start = selectedDriveDetails?.startDate.slice(0, 10);
                const end = selectedDriveDetails?.endDate.slice(0, 10);

                if (start && selectedDate < start) {
                  setVaccinationDate(start);
                } else if (end && selectedDate > end) {
                  setVaccinationDate(end);
                } else {
                  setVaccinationDate(selectedDate);
                }
              }}
            />
          </div>

          <div className="mb-3">
            <label className="form-labe form-label fw-semibold">
              Dose Number
            </label>
            <input
              type="number"
              min="1"
              className="form-control"
              value={doseNumber}
              onChange={(e) => setDoseNumber(e.target.value)}
            />
          </div>

          <div className="d-flex gap-2">
            <button type="submit" className="btn btn-success">
              ✅ Submit
            </button>
          </div>
        </form>
      </div>
    </div>
  );

  function NavBar() {
    return (
      <nav className="navbar navbar-expand-lg bg-white shadow-sm px-4">
        <div className="container-fluid">
          <span className="navbar-brand fw-bold fs-4 text-primary">
            🏫 School Vaccination Portal
          </span>
          <button
            className="btn btn-outline-dark"
            onClick={() => navigate("/students")}
          >
            Cancel
          </button>
        </div>
      </nav>
    );
  }
}
