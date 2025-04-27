import { useState } from "react";
import axiosInstance from "../../../api/axiosInstance";

export const useVaccinationReportViewModel = () => {
  const [reportData, setReportData] = useState([]);
  const [loading, setLoading] = useState(false);
  const [vaccineName, setVaccineName] = useState("");
  const [grade, setGrade] = useState("");
  const [page, setPage] = useState(1);
  const [error, setError] = useState(""); // new for error tracking

  const pageSize = 10; // fixed page size

  const fetchReport = async () => {
    setLoading(true);
    setError(""); // clear previous error
    setReportData([]); // clear old data

    try {
      const token = localStorage.getItem("token");

      const response = await axiosInstance.get(`/reports/vaccination`, {
        params: { vaccineName, grade },
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      if (response.data.success) {
        if (response.data.data.length === 0) {
          setError("No reports available for the selected filters.");
        }
        setReportData(response.data.data);
        setPage(1); // reset page to 1 when new data comes
      } else {
        setError("Failed to fetch report data.");
      }
    } catch (err) {
      console.error("Error fetching report:", err);
      setError("Something went wrong. Please try again later.");
    } finally {
      setLoading(false);
    }
  };

  const downloadCSV = () => {
    if (reportData.length === 0) return;

    const csvContent = [
      [
        "First Name",
        "Last Name",
        "Grade",
        "Roll No",
        "Email",
        "Vaccine Name",
        "Vaccination Date",
        "Status",
      ],
      ...reportData.map((item) => [
        item.firstName,
        item.lastName,
        item.grade,
        item.rollNo,
        item.email,
        item.vaccineName,
        item.vaccinationDate,
        item.status,
      ]),
    ]
      .map((row) => row.join(","))
      .join("\n");

    const blob = new Blob([csvContent], { type: "text/csv;charset=utf-8;" });
    const link = document.createElement("a");
    link.href = URL.createObjectURL(blob);
    link.setAttribute("download", "vaccination_report.csv");
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
  };

  const paginatedData = reportData.slice(
    (page - 1) * pageSize,
    page * pageSize
  );

  return {
    vaccineName,
    setVaccineName,
    grade,
    setGrade,
    fetchReport,
    downloadCSV,
    reportData: paginatedData,
    page,
    setPage,
    totalPages: Math.ceil(reportData.length / pageSize),
    loading,
    error,
  };
};
