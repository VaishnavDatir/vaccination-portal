import { useEffect, useState } from "react";
import axiosInstance from "../../../api/axiosInstance";

export default function useAddVaccinationViewModel() {
  const [showModal, setShowModal] = useState(false);
  const [selectedStudentId, setSelectedStudentId] = useState(null);
  const [selectedDriveId, setSelectedDriveId] = useState("");
  const [vaccinations, setVaccinations] = useState([]);
  const [selectedVaccinationId, setSelectedVaccinationId] = useState(null);
  const [vaccinationDate, setVaccinationDate] = useState("");
  const [doseNumber, setDoseNumber] = useState(1);
  const token = localStorage.getItem("token");

  useEffect(() => {
    if (showModal) {
      fetchVaccinations();
    }
  }, [showModal]);

  const fetchVaccinations = async () => {
    try {
      const res = await axiosInstance.get("/vaccination-drives/active", {
        headers: { Authorization: `Bearer ${token}` },
      });
      setVaccinations(res.data.data);
    } catch (error) {
      console.error("Failed to fetch vaccinations", error);
    }
  };

  const openModal = (studentId) => {
    setSelectedStudentId(studentId);
    setSelectedDriveId("");
    setSelectedVaccinationId(null);
    setVaccinationDate("");
    setDoseNumber(1);
    setShowModal(true);
  };

  const closeModal = () => {
    setShowModal(false);
    setSelectedStudentId(null);
    setSelectedVaccinationId(null);
    setVaccinationDate("");
    setDoseNumber(1);
  };

  const handleAddVaccination = async () => {
    try {
      if (
        selectedVaccinationId === null ||
        selectedVaccinationId === undefined ||
        selectedVaccinationId === ""
      ) {
        alert("❌ Please select a vaccination drive!");
        return;
      }
      const payload = {
        studentId: selectedStudentId,
        driveId: Number(selectedVaccinationId),
        vaccinationDate,
        doseNumber: Number(doseNumber),
        status: "VACCINATED",
      };

      const response = await axiosInstance.post(
        "/vaccination-records",
        payload,
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        }
      );

      console.log("Add vaccination response", response.data);

      if (response.data && response.data.success) {
        alert("✅ Vaccination added successfully!");
        closeModal();
      } else {
        alert("❌ Failed to add vaccination");
      }
    } catch (error) {
      console.error("Error adding vaccination", error);
      alert("❌ Error occurred while adding vaccination");
    }
  };

  return {
    showModal,
    openModal,
    closeModal,
    vaccinations,
    selectedDriveId,
    setSelectedDriveId,
    handleAddVaccination,
    selectedVaccinationId,
    setSelectedVaccinationId,
    vaccinationDate,
    setVaccinationDate,
    doseNumber,
    setDoseNumber,
  };
}
