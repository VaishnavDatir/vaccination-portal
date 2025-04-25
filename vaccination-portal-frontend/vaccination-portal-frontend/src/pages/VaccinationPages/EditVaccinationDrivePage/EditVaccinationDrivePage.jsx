import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import axiosInstance from "../../../api/axiosInstance";

export default function EditVaccinationDrivePage() {
  const { vaccinationId } = useParams();
  const navigate = useNavigate();
  const token = localStorage.getItem("token");

  const [formData, setFormData] = useState({
    vaccineName: "",
    startDate: "",
    endDate: "",
    availableDoses: "",
    gradeEligibility: "",
  });
  const [message, setMessage] = useState("");

  useEffect(() => {
    fetchDriveDetails();
  }, []);

  const fetchDriveDetails = async () => {
    try {
      const res = await axiosInstance.get(
        `/vaccination-drives/${vaccinationId}`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      const data = res.data.data;
      const gradeValue = data.gradeEligibility.replace("Grade ", "");
      setFormData({
        vaccineName: data.vaccineName,
        startDate: data.startDate,
        endDate: data.endDate,
        availableDoses: data.availableDoses,
        gradeEligibility: gradeValue,
      });
    } catch (error) {
      console.error("Error fetching vaccination drive details", error);
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const payload = {
      ...formData,
      gradeEligibility: `Grade ${formData.gradeEligibility}`,
    };
    try {
      await axiosInstance.put(`/vaccination-drives/${vaccinationId}`, payload, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setMessage("✅ Vaccination Drive updated successfully!");
      setTimeout(() => navigate("/vaccines"), 1500);
    } catch (error) {
      console.error("Error updating vaccination drive", error);
    }
  };

  return (
    <div className="bg-light">
      <nav className="navbar navbar-expand-lg bg-white shadow-sm px-4">
        <div className="container-fluid">
          <span className="navbar-brand fw-bold fs-4 text-primary">
            🏫 School Vaccination Portal
          </span>
          <button
            className="btn btn-outline-dark"
            onClick={() => navigate("/vaccines")}
          >
            ⬅ Back
          </button>
        </div>
      </nav>

      <div className="container py-5">
        <h2 className="text-primary mb-4">✏️ Edit Vaccination Drive</h2>
        {message && (
          <div
            className="alert alert-success text-center"
            style={{ maxWidth: "600px", margin: "0 auto" }}
            role="alert"
          >
            {message}
          </div>
        )}
        <form
          onSubmit={handleSubmit}
          className="bg-white rounded shadow-sm p-4"
          style={{ maxWidth: "600px", margin: "0 auto" }}
        >
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
          <div className="mb-3 text-start">
            <label className="form-label">Grade Eligibility</label>
            <input
              type="number"
              className="form-control"
              name="gradeEligibility"
              value={formData.gradeEligibility}
              onChange={handleChange}
              required
            />
          </div>
          <div className="text-end">
            <button className="btn btn-primary" type="submit">
              💾 Save Changes
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
