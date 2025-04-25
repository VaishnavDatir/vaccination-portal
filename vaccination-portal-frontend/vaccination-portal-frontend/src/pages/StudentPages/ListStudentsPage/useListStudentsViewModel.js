import { useEffect, useState } from "react";
import axiosInstance from "../../../api/axiosInstance";

export default function useListStudentsViewModel() {
  const [students, setStudents] = useState([]);
  const [page, setPage] = useState(0);
  const [total, setTotal] = useState(0);
  const size = 10;

  const token = localStorage.getItem("token");

  useEffect(() => {
    fetchStudents();
  }, [page]);

  const fetchStudents = async () => {
    try {
      const res = await axiosInstance.get(
        `/students?page=${page}&size=${size}`,
        {
          headers: { Authorization: `Bearer ${token}` },
        }
      );
      setStudents(res.data.data);
      setTotal(res.data.totalCount);
    } catch (error) {
      console.error("Failed to fetch students", error);
    }
  };

  const handleCsvUpload = async (file, onSuccess, onError) => {
    const formData = new FormData();
    formData.append("file", file);

    try {
      await axiosInstance.post("/students/upload-csv", formData, {
        headers: { "Content-Type": "multipart/form-data" },
      });
      onSuccess?.();
      fetchStudents();
    } catch (error) {
      onError?.();
      console.error("CSV upload failed", error);
    }
  };

  return {
    students,
    page,
    total,
    size,
    setPage,
    handleCsvUpload,
  };
}
