import { useEffect, useState } from "react";
import axiosInstance from "../../../api/axiosInstance";

export default function useViewStudentViewModel(studentId) {
  const [student, setStudent] = useState(null);
  const [vaccinationRecords, setVaccinationRecords] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchData();
  }, [studentId]);

  const fetchData = async () => {
    setLoading(true);
    try {
      await Promise.all([fetchStudentDetails(), fetchVaccinationRecords()]);
    } finally {
      setLoading(false);
    }
  };

  const fetchStudentDetails = async () => {
    try {
      const res = await axiosInstance.get(`/students/${studentId}`, {
        headers: { Authorization: `Bearer ${localStorage.getItem("token")}` },
      });
      if (res.data.success) {
        setStudent(res.data.data);
      }
    } catch (error) {
      console.error("Failed to fetch student details", error);
    }
  };

  const fetchVaccinationRecords = async () => {
    try {
      const res = await axiosInstance.get(
        `/vaccination-records/student/${studentId}`,
        {
          headers: { Authorization: `Bearer ${localStorage.getItem("token")}` },
        }
      );
      if (res.data.success) {
        setVaccinationRecords(res.data.data);
      }
    } catch (error) {
      console.error("Failed to fetch vaccination records", error);
    }
  };

  return {
    student,
    vaccinationRecords,
    loading,
  };
}
