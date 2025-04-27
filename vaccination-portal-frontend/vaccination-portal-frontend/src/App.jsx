import { Route, Routes } from "react-router-dom";
import "./App.css";
import Layout from "./layout/Layout";
import HomePage from "./pages/HomePage/HomePage";
import LoginPage from "./pages/LoginPage/LoginPage";
import AddStudentPage from "./pages/StudentPages/AddStudentPage/AddStudentPage";
import EditStudentPage from "./pages/StudentPages/EditStudentPage/EditStudentPage";
import AddVaccinationPage from "./pages/StudentPages/ListStudentsPage/AddVaccinationPage";
import ListStudentsPage from "./pages/StudentPages/ListStudentsPage/ListStudentsPage";
import ViewStudentPage from "./pages/StudentPages/ViewStudentPage/ViewStudentPage";
import AddDrivePage from "./pages/VaccinationPages/AddDrivePage/AddDrivePage";
import EditVaccinationDrivePage from "./pages/VaccinationPages/EditVaccinationDrivePage/EditVaccinationDrivePage";
import ListVaccinationDrivesPage from "./pages/VaccinationPages/ListVaccinationDrivesPage/ListVaccinationDrivesPage";
import VaccinationReportPage from "./pages/VaccinationPages/VaccinationReportPage/VaccinationReportPage";

function App() {
  return (
    <>
      <Layout>
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
              path="/students/add-vaccination/:studentId"
              element={<AddVaccinationPage />}
            />
            <Route
              path="/students/view/:studentId"
              element={<ViewStudentPage />}
            />

            <Route path="/vaccines" element={<ListVaccinationDrivesPage />} />
            <Route path="/vaccines/add-drive" element={<AddDrivePage />} />
            <Route
              path="/vaccines/edit/:vaccinationId"
              element={<EditVaccinationDrivePage />}
            />

            <Route path="/reports" element={<VaccinationReportPage />} />
          </Routes>
        </main>
      </Layout>
    </>
  );
}

export default App;
