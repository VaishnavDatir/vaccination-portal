import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "./ListStudentsPage.css";
export default function ListStudentsPage() {
  const [students, setStudents] = useState([]);
  const [page, setPage] = useState(0);
  const [total, setTotal] = useState(0);
  const size = 10;
  const navigate = useNavigate();
  const token = localStorage.getItem("token");

  useEffect(() => {
    fetchStudents();
  }, [page]);

  const fetchStudents = async () => {
    try {
      const res = await axios.get(
        `http://localhost:9091/api/students?page=${page}&size=${size}`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      setStudents(res.data.data);
      setTotal(res.data.totalCount);
    } catch (error) {
      console.error("Failed to fetch students", error);
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
          <h2 className="text-primary m-0">Student Records</h2>
          <button
            className="btn btn-success shadow-sm"
            onClick={() => navigate("/students/add-student")}
          >
            + Add Student
          </button>
        </div>

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
              {students.map((student, index) => (
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
                  </td>
                </tr>
              ))}
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
