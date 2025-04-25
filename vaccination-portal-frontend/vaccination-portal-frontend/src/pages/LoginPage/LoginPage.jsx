import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axiosInstance from "../../api/axiosInstance";

export default function LoginPage() {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({ username: "", password: "" });
  const [error, setError] = useState("");

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axiosInstance.post("/auth/signin", formData);
      const { token } = response.data.data;
      localStorage.setItem("token", token);
      navigate("/home");
    } catch (err) {
      setError("Login failed. Please check your credentials.");
    }
  };

  return (
    <div className="bg-light min-vh-100">
      {/* Navbar */}
      <nav className="navbar navbar-light bg-white shadow-sm px-4">
        <span className="navbar-brand mb-0 h1 text-primary">
          School Vaccination Portal
        </span>
      </nav>

      {/* Main layout */}
      <div
        className="container d-flex align-items-center justify-content-center"
        style={{ minHeight: "90vh" }}
      >
        <div
          className="row w-100 shadow-lg rounded bg-white overflow-hidden"
          style={{ maxWidth: "900px" }}
        >
          {/* Left side */}
          <div className="col-md-6 d-flex flex-column justify-content-center align-items-center bg-primary text-white p-4">
            <h2>Welcome Back!</h2>
            <p>Login to continue managing your dashboard.</p>
          </div>

          {/* Right side - Login form */}
          <div className="col-md-6 p-4">
            <h3 className="text-center mb-4 text-primary">Login</h3>
            {error && <div className="alert alert-danger">{error}</div>}
            <form onSubmit={handleSubmit}>
              <div className="mb-3">
                <label htmlFor="username" className="form-label">
                  Username
                </label>
                <input
                  type="text"
                  className="form-control"
                  id="username"
                  name="username"
                  value={formData.username}
                  onChange={handleChange}
                  placeholder="Enter username"
                  required
                />
              </div>
              <div className="mb-3">
                <label htmlFor="password" className="form-label">
                  Password
                </label>
                <input
                  type="password"
                  className="form-control"
                  id="password"
                  name="password"
                  value={formData.password}
                  onChange={handleChange}
                  placeholder="Enter password"
                  required
                />
              </div>
              <button type="submit" className="btn btn-primary w-100">
                Login
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}
