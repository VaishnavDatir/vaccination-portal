import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axiosInstance from "../../../api/axiosInstance";

export default function AddDrivePage() {
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    vaccineName: "",
    startDate: "",
    endDate: "",
    availableDoses: "",
    gradeEligibility: "",
  });

  const [status, setStatus] = useState(null);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const token = localStorage.getItem("token");
      const payload = {
        ...formData,
        availableDoses: parseInt(formData.availableDoses),
      };
      const response = await axiosInstance.post(
        "/vaccination-drives",
        payload,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );

      if (response.data.success) {
        setStatus("✅ Vaccination drive added successfully!");
        setFormData({
          vaccineName: "",
          startDate: "",
          endDate: "",
          availableDoses: "",
          gradeEligibility: "",
        });
      }
    } catch (error) {
      console.error("Error adding drive:", error);
      setStatus("❌ Failed to add drive. Please check input or server.");
    }
  };

  return (
    <div className="bg-body-tertiary min-vh-100">
      {/* Navbar */}
      <nav className="navbar navbar-expand-lg bg-white shadow-sm px-4">
        <div className="container-fluid">
          <span className="navbar-brand fw-bold fs-4 text-primary">
            🏫 School Vaccination Portal
          </span>
        </div>
      </nav>

      {/* Main Form */}
      <div className="container py-5">
        <div className="glass-card p-4 mx-auto" style={{ maxWidth: "600px" }}>
          <h3 className="text-start mb-4 text-primary fw-bold">
            ➕ Add Vaccination Drive
          </h3>

          {status && <div className="alert alert-info">{status}</div>}

          <form onSubmit={handleSubmit}>
            <div className="mb-3 text-start">
              <label className="form-label">Vaccine Name</label>
              <input
                type="text"
                className="form-control"
                name="vaccineName"
                value={formData.vaccineName}
                onChange={handleChange}
                required
              />
            </div>

            <div className="mb-3 text-start">
              <label className="form-label">Start Date</label>
              <input
                type="date"
                className="form-control"
                name="startDate"
                value={formData.startDate}
                onChange={handleChange}
                required
              />
            </div>

            <div className="mb-3 text-start">
              <label className="form-label">End Date</label>
              <input
                type="date"
                className="form-control"
                name="endDate"
                value={formData.endDate}
                onChange={handleChange}
                required
              />
            </div>

            <div className="mb-3 text-start">
              <label className="form-label">Available Doses</label>
              <input
                type="number"
                className="form-control"
                name="availableDoses"
                value={formData.availableDoses}
                onChange={handleChange}
                required
              />
            </div>

            <div className="mb-4 text-start">
              <label className="form-label">Grade Eligibility</label>
              <select
                className="form-select"
                name="gradeEligibility"
                value={
                  formData.gradeEligibility
                    ? formData.gradeEligibility.replace("Grade ", "")
                    : ""
                }
                onChange={(e) =>
                  setFormData((prev) => ({
                    ...prev,
                    gradeEligibility: `Grade ${e.target.value}`,
                  }))
                }
                required
              >
                <option value="">Select Grade</option>
                {[...Array(12)].map((_, i) => (
                  <option key={i + 1} value={i + 1}>
                    Grade {i + 1}
                  </option>
                ))}
              </select>
            </div>

            <button type="submit" className="btn btn-success w-100">
              Submit Drive
            </button>
          </form>
        </div>
      </div>
    </div>
  );
}
