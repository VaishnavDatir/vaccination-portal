import { useState } from "react";
import axiosInstance from "../../../api/axiosInstance";

export function useVaccinationViewModel() {
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState("");

  const addVaccination = async (record) => {
    setLoading(true);
    setMessage("");

    try {
      await axiosInstance.post("/vaccination-records", record, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
        },
      });
      setMessage("✅ Vaccination recorded successfully");
    } catch (error) {
      console.error("Error adding vaccination", error);
      setMessage("❌ Failed to add vaccination");
    } finally {
      setLoading(false);
    }
  };

  return { addVaccination, loading, message };
}
