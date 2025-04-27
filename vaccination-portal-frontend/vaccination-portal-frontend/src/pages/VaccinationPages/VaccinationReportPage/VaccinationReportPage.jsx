import React, { useEffect, useState } from "react";
import axiosInstance from "../../../api/axiosInstance";
import { useVaccinationReportViewModel } from "./useVaccinationReportViewModel";

const VaccinationReportPage = () => {
  const {
    vaccineName,
    setVaccineName,
    grade,
    setGrade,
    fetchReport,
    downloadCSV,
    reportData,
    page,
    setPage,
    totalPages,
    loading,
  } = useVaccinationReportViewModel();

  const [availableVaccines, setAvailableVaccines] = useState([]);
  const [fetchError, setFetchError] = useState(""); // NEW: error state

  useEffect(() => {
    const fetchVaccines = async () => {
      try {
        const token = localStorage.getItem("token");
        const res = await axiosInstance.get(`/vaccination-drives`, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        if (res.data.success) {
          setAvailableVaccines(res.data.data);
        }
      } catch (error) {
        console.error("Error fetching vaccines", error);
      }
    };

    fetchVaccines();
  }, []);

  const handleGenerateReport = async () => {
    setFetchError(""); // clear previous error
    await fetchReport();
    if (reportData.length === 0) {
      setFetchError("No reports available for the selected filters.");
    }
  };

  return (
    <div className="bg-light min-vh-100">
      <nav className="navbar navbar-expand-lg bg-white shadow-sm px-4">
        <div className="container-fluid">
          <span className="navbar-brand fw-bold fs-4 text-primary">
            🏫 School Vaccination Portal
          </span>
        </div>
      </nav>
      <div className="container my-4">
        <h2 className="mb-4">📋 Vaccination Report</h2>

        {/* Filters */}
        <div className="card p-4 mb-4">
          <div className="row g-3 align-items-end">
            <div className="col-md-4">
              <label className="form-label">Select Vaccine</label>
              <select
                className="form-select"
                value={vaccineName}
                onChange={(e) => setVaccineName(e.target.value)}
              >
                <option value="">-- Select Vaccine --</option>
                {availableVaccines.map((vaccine) => (
                  <option key={vaccine.driveId} value={vaccine.vaccineName}>
                    {vaccine.vaccineName}
                  </option>
                ))}
              </select>
            </div>

            <div className="col-md-4">
              <label className="form-label">Select Grade</label>
              <select
                className="form-select"
                value={grade}
                onChange={(e) => setGrade(e.target.value)}
              >
                <option value="">-- Select Grade --</option>
                {[...Array(10)].map((_, index) => (
                  <option key={index + 1} value={index + 1}>
                    Grade {index + 1}
                  </option>
                ))}
              </select>
            </div>

            <div className="col-md-4 d-flex gap-2">
              <button
                className="btn btn-outline-success"
                onClick={handleGenerateReport}
              >
                🔍 Generate Report
              </button>
              <button
                className="btn btn-outline-primary"
                onClick={downloadCSV}
                disabled={reportData.length === 0}
              >
                ⬇️ Download CSV
              </button>
            </div>
          </div>
        </div>

        {/* Table or messages */}
        {loading ? (
          <div className="text-center">
            <div className="spinner-border text-success" role="status">
              <span className="visually-hidden">Loading...</span>
            </div>
          </div>
        ) : fetchError ? (
          <div className="alert alert-warning text-center">{fetchError}</div>
        ) : (
          <div className="table-responsive">
            <table className="table table-bordered table-striped">
              <thead className="table-dark">
                <tr>
                  <th>First Name</th>
                  <th>Last Name</th>
                  <th>Grade</th>
                  <th>Roll No</th>
                  <th>Email</th>
                  <th>Vaccine Name</th>
                  <th>Vaccination Date</th>
                  <th>Status</th>
                </tr>
              </thead>
              <tbody>
                {reportData.length === 0 ? (
                  <tr>
                    <td colSpan="8" className="text-center">
                      No records found.
                    </td>
                  </tr>
                ) : (
                  reportData.map((student, index) => (
                    <tr key={index}>
                      <td>{student.firstName}</td>
                      <td>{student.lastName}</td>
                      <td>{student.grade}</td>
                      <td>{student.rollNo}</td>
                      <td>{student.email}</td>
                      <td>{student.vaccineName}</td>
                      <td>{student.vaccinationDate}</td>
                      <td>{student.status}</td>
                    </tr>
                  ))
                )}
              </tbody>
            </table>
          </div>
        )}

        {/* Pagination */}
        {reportData.length > 0 && (
          <div className="d-flex justify-content-between align-items-center mt-4">
            <button
              className="btn btn-outline-secondary"
              onClick={() => setPage((prev) => Math.max(prev - 1, 1))}
              disabled={page === 1}
            >
              ◀️ Previous
            </button>
            <span>
              Page {page} of {totalPages}
            </span>
            <button
              className="btn btn-outline-secondary"
              onClick={() => setPage((prev) => Math.min(prev + 1, totalPages))}
              disabled={page === totalPages}
            >
              Next ▶️
            </button>
          </div>
        )}
      </div>
    </div>
  );
};

export default VaccinationReportPage;
