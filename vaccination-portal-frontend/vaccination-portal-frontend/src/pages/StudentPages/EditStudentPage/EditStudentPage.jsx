import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

export default function EditStudentPage() {
  const { studentId } = useParams();
  const navigate = useNavigate();
  const token = localStorage.getItem("token");
  const [formData, setFormData] = useState({
    firstName: "",
    lastName: "",
    email: "",
    grade: "",
    gender: "",
    rollNo: "",
  });
  const [message, setMessage] = useState("");

  useEffect(() => {
    fetchStudent();
  }, [studentId]);

  const fetchStudent = async () => {
    try {
      const res = await axios.get(
        `http://localhost:9091/api/students/${studentId}`,
        {
          headers: { Authorization: `Bearer ${token}` },
        }
      );
      const student = res.data.data;
      const gradeValue = student.grade.replace("Grade ", "");
      setFormData({
        firstName: student.firstName,
        lastName: student.lastName,
        email: student.email,
        grade: gradeValue,
        gender: student.gender,
        rollNo: student.rollNo,
      });
    } catch (err) {
      console.error("Failed to fetch student", err);
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const payload = {
        ...formData,
        grade: `Grade ${formData.grade}`,
      };
      await axios.put(
        `http://localhost:9091/api/students/${studentId}`,
        payload,
        {
          headers: { Authorization: `Bearer ${token}` },
        }
      );
      setMessage("✅ Student updated successfully!");
      setTimeout(() => navigate("/students"), 1500);
    } catch (err) {
      console.error("Update failed", err);
    }
  };

  return (
    <div className="bg-light min-vh-100">
      <nav className="navbar navbar-expand-lg bg-white shadow-sm px-4">
        <div className="container-fluid">
          <span className="navbar-brand fw-bold fs-4 text-primary">
            🏫 School Vaccination Portal
          </span>
          <button
            className="btn btn-outline-dark"
            onClick={() => navigate("/students")}
          >
            ⬅ Back to Students
          </button>
        </div>
      </nav>

      <div className="container py-5">
        <h2 className="text-primary mb-4">Edit Student</h2>

        {message && (
          <div className="alert alert-success text-center" role="alert">
            {message}
          </div>
        )}

        <form
          onSubmit={handleSubmit}
          className="bg-white rounded shadow-sm p-4"
          style={{ maxWidth: "600px", margin: "0 auto" }}
        >
          <div className="mb-3 text-start">
            <label className="form-label">First Name</label>
            <input
              type="text"
              className="form-control"
              name="firstName"
              value={formData.firstName}
              onChange={handleChange}
              required
            />
          </div>

          <div className="mb-3 text-start">
            <label className="form-label">Last Name</label>
            <input
              type="text"
              className="form-control"
              name="lastName"
              value={formData.lastName}
              onChange={handleChange}
              required
            />
          </div>

          <div className="mb-3 text-start">
            <label className="form-label">Email</label>
            <input
              type="email"
              className="form-control"
              name="email"
              value={formData.email}
              onChange={handleChange}
              required
            />
          </div>

          <div className="mb-3 text-start">
            <label className="form-label">Roll No</label>
            <input
              type="text"
              className="form-control"
              name="rollNo"
              value={formData.rollNo}
              onChange={handleChange}
              required
            />
          </div>

          <div className="mb-3 text-start">
            <label className="form-label">Grade</label>
            <input
              type="number"
              className="form-control"
              name="grade"
              value={formData.grade}
              onChange={handleChange}
              required
            />
          </div>

          <div className="mb-3 text-start">
            <label className="form-label">Gender</label>
            <select
              className="form-select"
              name="gender"
              value={formData.gender}
              onChange={handleChange}
              required
            >
              <option value="">Select Gender</option>
              <option value="M">Male</option>
              <option value="F">Female</option>
            </select>
          </div>

          <div className="text-end">
            <button type="submit" className="btn btn-primary">
              💾 Update Student
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
