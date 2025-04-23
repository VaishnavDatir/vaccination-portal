import { Route, Routes } from "react-router-dom";
import "./App.css";
import HomePage from "./pages/HomePage/HomePage";
import LoginPage from "./pages/LoginPage/LoginPage";
import AddStudentPage from "./pages/StudentPages/AddStudentPage/AddStudentPage";
import EditStudentPage from "./pages/StudentPages/EditStudentPage/EditStudentPage";
import ListStudentsPage from "./pages/StudentPages/ListStudentsPage/ListStudentsPage";
import AddDrivePage from "./pages/VaccinationPages/AddDrivePage/AddDrivePage";

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
          <Route path="/add-drive" element={<AddDrivePage />} />
        </Routes>
      </main>
    </>
  );
}

export default App;
