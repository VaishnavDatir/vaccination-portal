import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axiosInstance from "../../../api/axiosInstance";

export default function ListVaccinationDrivesPage() {
  const [drives, setDrives] = useState([]);
  const [page, setPage] = useState(0);
  const [total, setTotal] = useState(0);
  const size = 10;
  const navigate = useNavigate();
  const token = localStorage.getItem("token");

  useEffect(() => {
    fetchDrives();
  }, [page]);

  const fetchDrives = async () => {
    try {
      const res = await axiosInstance.get(
        `/vaccination-drives?page=${page}&size=${size}`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      setDrives(res.data.data);
      setTotal(res.data.totalCount);
    } catch (error) {
      console.error("Failed to fetch vaccination drives", error);
    }
  };

  const totalPages = Math.ceil(total / size);

  return (
    <div className="bg-light min-vh-100">
      {/* Navbar */}
      <nav className="navbar navbar-expand-lg bg-white shadow-sm px-4">
        <div className="container-fluid">
          <span className="navbar-brand fw-bold fs-4 text-primary">
            🏫 School Vaccination Portal
          </span>
          <button
            className="btn btn-outline-dark"
            onClick={() => navigate("/home")}
          >
            🏠 Dashboard
          </button>
        </div>
      </nav>

      <div className="container py-5">
        <div className="d-flex justify-content-between align-items-center mb-4">
          <h2 className="text-primary m-0">Vaccination Drive Records</h2>
          <button
            className="btn btn-primary shadow-sm"
            onClick={() => navigate("/vaccines/add-drive")}
          >
            + Add Drive
          </button>
        </div>

        <div className="table-responsive bg-white rounded shadow-sm p-3">
          <table className="table table-bordered table-hover text-start align-middle mb-0">
            <thead className="table-light">
              <tr className="fw-semibold">
                <th>#</th>
                <th>Vaccine Name</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Available Doses</th>
                <th>Eligible Grade</th>
                <th>Last Updated</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {drives.map((drive, index) => (
                <tr key={drive.driveId}>
                  <td>{page * size + index + 1}</td>
                  <td>{drive.vaccineName}</td>
                  <td>{new Date(drive.startDate).toLocaleDateString()}</td>
                  <td>{new Date(drive.endDate).toLocaleDateString()}</td>
                  <td>{drive.availableDoses}</td>
                  <td>{drive.gradeEligibility}</td>
                  <td>{new Date(drive.updateTs).toLocaleString()}</td>
                  <td>
                    <button
                      className="btn btn-sm btn-outline-secondary"
                      onClick={() =>
                        navigate(`/vaccines/edit/${drive.driveId}`)
                      }
                    >
                      ✏️ Edit
                    </button>
                  </td>
                </tr>
              ))}
              {drives.length === 0 && (
                <tr>
                  <td colSpan="8" className="text-center text-muted">
                    No vaccination drives found.
                  </td>
                </tr>
              )}
            </tbody>
          </table>
        </div>

        {/* Pagination */}
        <div className="d-flex justify-content-between align-items-center mt-4">
          <button
            className="btn btn-outline-primary"
            disabled={page <= 0}
            onClick={() => setPage(page - 1)}
          >
            ← Previous
          </button>
          <span className="fw-semibold">
            Page {page + 1} of {totalPages || 1}
          </span>
          <button
            className="btn btn-outline-primary"
            disabled={page + 1 >= totalPages}
            onClick={() => setPage(page + 1)}
          >
            Next →
          </button>
        </div>
      </div>
    </div>
  );
}
