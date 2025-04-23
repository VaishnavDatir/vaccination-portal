import axios from "axios";
import React, { useEffect, useState } from "react";

export default function HomePage() {
  const [summary, setSummary] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchSummary = async () => {
      try {
        const token = localStorage.getItem("token");
        const response = await axios.get(
          "http://localhost:9091/api/dashboard/summary",
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );
        setSummary(response.data.data);
      } catch (err) {
        console.error("Dashboard fetch error:", err);
      } finally {
        setLoading(false);
      }
    };

    fetchSummary();
  }, []);

  if (loading) {
    return (
      <div className="d-flex align-items-center justify-content-center vh-100 bg-light">
        <h4>Loading...</h4>
      </div>
    );
  }

  return (
    <div className="bg-body-tertiary min-vh-100">
      {/* Navbar */}
      <nav className="navbar navbar-expand-lg bg-white shadow-sm px-4">
        <div className="container-fluid">
          <span className="navbar-brand fw-bold fs-4 text-primary">
            🏫 School Vaccination Portal
          </span>
          <div className="d-flex gap-2">
            <button
              className="btn btn-outline-primary"
              onClick={() => (window.location.href = "/students")}
            >
              👥 Manage Students
            </button>
            <button
              className="btn btn-outline-success"
              onClick={() => (window.location.href = "/add-drive")}
            >
              💉 Add Vaccination Drive
            </button>
          </div>
        </div>
      </nav>

      <div className="container py-5">
        {/* Main Summary Cards */}
        <div className="row g-4 mb-4">
          <div className="col-md-6">
            <div className="glass-card p-4 text-center">
              <h6>Total Students</h6>
              <h2 className="text-primary">{summary?.totalStudents}</h2>
            </div>
          </div>
          <div className="col-md-6">
            <div className="glass-card p-4 text-center">
              <h6>Total Vaccinated</h6>
              <h2 className="text-success">{summary?.totalVaccinated}</h2>
            </div>
          </div>
        </div>

        {/* Vaccination by Grade */}
        <h5 className="mb-3 text-primary">📊 Vaccination % by Grade</h5>
        <div className="scroll-row mb-5">
          {summary?.vaccinatedPercentageByGrade?.map((item, idx) => (
            <div key={idx} className="scroll-card">
              <h6 className="mb-1">{item.grade}</h6>
              <h4 className="text-success">{item.vaccinationPercentage}%</h4>
            </div>
          ))}
        </div>

        {/* Upcoming Vaccination Drives */}
        <h5 className="mb-3 text-primary">📅 Upcoming Vaccination Drives</h5>
        <div className="scroll-row">
          {summary?.upcomingVaccinationDrives?.map((drive) => (
            <div key={drive.driveId} className="scroll-card text-start">
              <h6 className="text-dark">{drive.vaccineName}</h6>
              <p className="mb-1">
                <strong>Start:</strong> {drive.startDate}
              </p>
              <p className="mb-0">
                <strong>End:</strong> {drive.endDate}
              </p>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}
