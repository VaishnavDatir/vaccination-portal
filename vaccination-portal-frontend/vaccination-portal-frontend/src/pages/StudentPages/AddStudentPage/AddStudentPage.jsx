import axios from "axios";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function AddStudentPage() {
  const navigate = useNavigate();

  const [form, setForm] = useState({
    firstName: "",
    lastName: "",
    email: "",
    gender: "M",
    grade: "",
    rollNo: "",
  });

  const [error, setError] = useState("");
  const [success, setSuccess] = useState(false);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setSuccess(false);

    const token = localStorage.getItem("token");

    const payload = {
      ...form,
      grade: `Grade ${form.grade}`,
    };

    try {
      await axios.post("http://localhost:9091/api/students", payload, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      setSuccess(true);
      setForm({
        firstName: "",
        lastName: "",
        email: "",
        gender: "M",
        grade: "",
        rollNo: "",
      });
    } catch (err) {
      setError("Failed to add student. Please try again.");
    }
  };

  return (
    <div className="bg-light min-vh-100">
      {/* Navbar */}
      <nav className="navbar navbar-expand-lg bg-white shadow-sm py-3 px-4 border-bottom">
        <div className="container-fluid d-flex justify-content-between align-items-center">
          <span className="fw-bold fs-4 text-primary d-flex align-items-center">
            🏫 <span className="ms-2">School Vaccination Portal</span>
          </span>
        </div>
      </nav>

      <div className="container mt-5">
        {/* PAGE TITLE */}
        <h2 className="text-start mb-4 text-primary">➕ Add New Student</h2>

        {error && <div className="alert alert-danger">{error}</div>}
        {success && (
          <div className="alert alert-success">Student added successfully!</div>
        )}

        <form
          onSubmit={handleSubmit}
          className="card p-5 shadow-lg border-0 rounded-4"
        >
          <div className="row g-4">
            <div className="col-md-6">
              <label className="form-label">First Name</label>
              <input
                type="text"
                name="firstName"
                className="form-control"
                value={form.firstName}
                onChange={handleChange}
                required
              />
            </div>
            <div className="col-md-6">
              <label className="form-label">Last Name</label>
              <input
                type="text"
                name="lastName"
                className="form-control"
                value={form.lastName}
                onChange={handleChange}
                required
              />
            </div>
            <div className="col-md-6">
              <label className="form-label">Email</label>
              <input
                type="email"
                name="email"
                className="form-control"
                value={form.email}
                onChange={handleChange}
                required
              />
            </div>
            <div className="col-md-3">
              <label className="form-label">Gender</label>
              <select
                name="gender"
                className="form-select"
                value={form.gender}
                onChange={handleChange}
              >
                <option value="M">Male</option>
                <option value="F">Female</option>
              </select>
            </div>
            <div className="col-md-3">
              <label className="form-label">Grade</label>
              <select
                name="grade"
                className="form-select"
                value={form.grade}
                onChange={handleChange}
                required
              >
                <option value="">Select</option>
                {[...Array(12)].map((_, i) => (
                  <option key={i + 1} value={i + 1}>
                    {i + 1}
                  </option>
                ))}
              </select>
            </div>
            <div className="col-md-6">
              <label className="form-label">Roll Number</label>
              <input
                type="text"
                name="rollNo"
                className="form-control"
                value={form.rollNo}
                onChange={handleChange}
                required
              />
            </div>
          </div>

          <div className="mt-4 text-end">
            <button type="submit" className="btn btn-primary px-4">
              Add Student
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
