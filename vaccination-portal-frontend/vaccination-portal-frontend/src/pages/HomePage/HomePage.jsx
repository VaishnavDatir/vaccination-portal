import React, { useEffect, useState } from "react";
import axiosInstance from "../../api/axiosInstance";
import useHorizontalDragScroll from "../../hooks/useHorizontalDragScroll";
import "./HomePage.css";

export default function HomePage() {
  const [summary, setSummary] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchDashboardSummary();
  }, []);

  const fetchDashboardSummary = async () => {
    try {
      const token = localStorage.getItem("token");
      const response = await axiosInstance.get("/dashboard/summary", {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setSummary(response.data.data);
    } catch (err) {
      console.error("Dashboard fetch error:", err);
    } finally {
      setLoading(false);
    }
  };

  if (loading) {
    return (
      <div className="d-flex align-items-center justify-content-center vh-100 bg-light">
        <h4>Loading...</h4>
      </div>
    );
  }

  return (
    <div className="bg-body-tertiary min-vh-100">
      <NavBar />
      <div className="container py-5">
        <SummaryCards summary={summary} />
        <GradeSection summary={summary} />
        <UpcomingDrives summary={summary} />
      </div>
    </div>
  );
}

// NavBar
function NavBar() {
  return (
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
            👥 Students
          </button>
          <button
            className="btn btn-outline-success"
            onClick={() => (window.location.href = "/vaccines")}
          >
            💉 Vaccination Drive
          </button>
          <button
            className="btn btn-outline-primary"
            onClick={() => (window.location.href = "/reports")}
          >
            📋 Report
          </button>
        </div>
      </div>
    </nav>
  );
}

// Summary Cards
function SummaryCards({ summary }) {
  return (
    <div className="row g-4 mb-4">
      <div className="col-md-6">
        <div className="glass-card p-4 text-center">
          <h6>Total Students</h6>
          <h2 className="text-primary">{summary?.totalStudents}</h2>
        </div>
      </div>
      <div className="col-md-6">
        <div className="glass-card p-4 text-center bg-success text-white">
          <h6>Total Vaccinated</h6>
          <h2 className="text-white">{summary?.totalVaccinated}</h2>
        </div>
      </div>
    </div>
  );
}

// Grade % Section
function GradeSection({ summary }) {
  const dragRef = useHorizontalDragScroll();
  const gradeData = summary?.vaccinatedPercentageByGrade || [];

  return (
    <>
      <h5 className="mb-3 text-primary">📊 Vaccination % by Grade</h5>
      {gradeData.length > 0 ? (
        <div ref={dragRef} className="grade-scroll-wrapper mb-5">
          {gradeData.map((item, idx) => (
            <div key={idx} className="grade-card">
              <h6 className="mb-1">Grade {item.grade}</h6>
              <h4 className="text-success">{item.vaccinationPercentage}%</h4>
            </div>
          ))}
        </div>
      ) : (
        <div className="text-muted mb-4">🚫 No grade-wise data available.</div>
      )}
    </>
  );
}

function UpcomingDrives({ summary }) {
  const dragRef = useHorizontalDragScroll();
  const drives = summary?.upcomingVaccinationDrives || [];

  return (
    <>
      <h5 className="mb-3 text-primary">📅 Upcoming Vaccination Drives</h5>
      {drives.length > 0 ? (
        <div ref={dragRef} className="grade-scroll-wrapper">
          {drives.map((drive) => (
            <div key={drive.driveId} className="grade-card text-start">
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
      ) : (
        <div className="text-muted mb-4">📭 No upcoming drives found.</div>
      )}
    </>
  );
}
