import { Route, Routes } from "react-router-dom";
import "./App.css";
import HomePage from "./pages/HomePage/HomePage";
import LoginPage from "./pages/LoginPage/LoginPage";
import AddStudentPage from "./pages/StudentPages/AddStudentPage/AddStudentPage";
import AddVaccinationForm from "./pages/StudentPages/AddVaccinationPage/AddVaccinationForm";
import EditStudentPage from "./pages/StudentPages/EditStudentPage/EditStudentPage";
import ListStudentsPage from "./pages/StudentPages/ListStudentsPage/ListStudentsPage";
import AddDrivePage from "./pages/VaccinationPages/AddDrivePage/AddDrivePage";
import EditVaccinationDrivePage from "./pages/VaccinationPages/EditVaccinationDrivePage/EditVaccinationDrivePage";
import ListVaccinationDrivesPage from "./pages/VaccinationPages/ListVaccinationDrivesPage/ListVaccinationDrivesPage";

function App() {
  return (
    <>
      <main className="main-content">
        <Routes>
          <Route path="/" element={<LoginPage />} />
          <Route path="/home" element={<HomePage />} />
          <Route path="/students" element={<ListStudentsPage />} />
          <Route path="/students/add-student" element={<AddStudentPage />} />
          <Route
            path="/students/edit/:studentId"
            element={<EditStudentPage />}
          />
          <Route
            path="/students/:studentId/add-vaccination"
            element={<AddVaccinationForm />}
          />
          <Route path="/vaccines" element={<ListVaccinationDrivesPage />} />
          <Route path="/vaccines/add-drive" element={<AddDrivePage />} />
          <Route
            path="/vaccines/edit/:vaccinationId"
            element={<EditVaccinationDrivePage />}
          />
        </Routes>
      </main>
    </>
  );
}

export default App;
