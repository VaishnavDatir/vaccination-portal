import React from "react";
import { useNavigate, useParams } from "react-router-dom";
import useViewStudentViewModel from "./useViewStudentViewModel";

export default function ViewStudentPage() {
  const { studentId } = useParams();
  const navigate = useNavigate();
  const { student, vaccinationRecords, loading } =
    useViewStudentViewModel(studentId);

  if (loading) {
    return <div className="text-center mt-5">Loading...</div>;
  }

  if (!student) {
    return <div className="text-center mt-5">Student not found</div>;
  }

  return (
    <div className="bg-light min-vh-100">
      <NavBar />
      <div className="container py-5">
        <h2 className="text-primary mb-4">🎓 Student Details</h2>

        <div className="bg-white p-4 rounded shadow-sm mb-4">
          <h4>
            {student.firstName} {student.lastName}
          </h4>
          <p>Roll No: {student.rollNo}</p>
          <p>Email: {student.email}</p>
          <p>Grade: {student.grade}</p>
          <p>Gender: {student.gender}</p>
          <p>Last Updated: {new Date(student.updateTs).toLocaleString()}</p>
        </div>

        <div className="bg-white p-4 rounded shadow-sm">
          <h4 className="mb-3">💉 Vaccination Records</h4>
          {vaccinationRecords.length === 0 ? (
            <p className="text-muted">No vaccinations found.</p>
          ) : (
            <div className="table-responsive">
              <table className="table table-bordered">
                <thead className="table-light">
                  <tr>
                    <th>#</th>
                    <th>Vaccination Date</th>
                    <th>Dose Number</th>
                    <th>Status</th>
                    <th>Drive Name</th> {/* updated heading */}
                    <th>Last Updated</th>
                  </tr>
                </thead>
                <tbody>
                  {vaccinationRecords.map((record, index) => (
                    <tr key={record.recordId}>
                      <td>{index + 1}</td>
                      <td>{record.vaccinationDate}</td>
                      <td>{record.doseNumber}</td>
                      <td>{record.status}</td>
                      <td>{record.driveName || "-"}</td> {/* updated field */}
                      <td>{new Date(record.updateTs).toLocaleString()}</td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          )}
        </div>
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
            ← Back to Students
          </button>
        </div>
      </nav>
    );
  }
}
