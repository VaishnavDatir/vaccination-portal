import React from "react";
import { useNavigate } from "react-router-dom";
import "./ListStudentsPage.css";
import useListStudentsViewModel from "./useListStudentsViewModel";

export default function ListStudentsPage() {
  const { students, page, total, size, setPage, handleCsvUpload } =
    useListStudentsViewModel();

  const navigate = useNavigate();
  const totalPages = Math.ceil(total / size);

  const onCsvChange = (e) => {
    const file = e.target.files[0];
    if (!file) return;
    handleCsvUpload(
      file,
      () => alert("✅ Upload successful!"),
      () => alert("❌ Upload failed")
    );
  };

  return (
    <div className="bg-light min-vh-100">
      <NavBar />
      <div className="container py-5">
        <MyHeader />
        <DataTable />
        <PaginationBtns />
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
            onClick={() => navigate("/home")}
          >
            🏠 Dashboard
          </button>
        </div>
      </nav>
    );
  }

  function MyHeader() {
    return (
      <div className="d-flex justify-content-between align-items-center mb-4">
        <h2 className="text-primary m-0">Student Records</h2>
        <div>
          <input
            type="file"
            accept=".csv"
            id="csvUpload"
            style={{ display: "none" }}
            onChange={onCsvChange}
          />
          <button
            className="btn btn-outline-primary me-2"
            onClick={() => document.getElementById("csvUpload").click()}
          >
            📤 Upload CSV
          </button>
          <button
            className="btn btn-success shadow-sm"
            onClick={() => navigate("/students/add-student")}
          >
            + Add Student
          </button>
        </div>
      </div>
    );
  }

  function DataTable() {
    return (
      <div className="table-responsive bg-white rounded shadow-sm p-3">
        <table className="table table-bordered table-hover text-start align-middle mb-0">
          <thead className="table-light">
            <tr className="fw-semibold">
              <th>#</th>
              <th>Full Name</th>
              <th>Roll No</th>
              <th>Grade</th>
              <th>Email</th>
              <th>Gender</th>
              <th>Last Updated</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {students.length === 0 ? (
              <tr>
                <td colSpan="8" className="text-center text-muted">
                  No students found.
                </td>
              </tr>
            ) : (
              students.map((student, index) => (
                <tr key={student.studentId}>
                  <td>{page * size + index + 1}</td>
                  <td>
                    {student.firstName} {student.lastName}
                  </td>
                  <td>{student.rollNo}</td>
                  <td>{student.grade}</td>
                  <td>{student.email}</td>
                  <td>{student.gender || "-"}</td>
                  <td>{new Date(student.updateTs).toLocaleString()}</td>
                  <td>
                    <button
                      className="btn btn-sm btn-outline-secondary"
                      onClick={() =>
                        navigate(`/students/edit/${student.studentId}`)
                      }
                    >
                      ✏️ Edit
                    </button>
                    <button
                      className="btn btn-sm btn-outline-success"
                      onClick={() =>
                        navigate(
                          `/students/${student.studentId}/add-vaccination`
                        )
                      }
                    >
                      💉 Add Vaccination
                    </button>
                  </td>
                </tr>
              ))
            )}
          </tbody>
        </table>
      </div>
    );
  }

  function PaginationBtns() {
    return (
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
    );
  }
}
