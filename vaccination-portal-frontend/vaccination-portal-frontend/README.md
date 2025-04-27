# 🏫 School Vaccination Portal

A web application to manage student records and vaccination drives for a school or institution.

---

## 📋 Project Overview

This portal allows:

- 🧑‍🏫 Manage student data (add, edit, view, upload via CSV)
- 💉 Track vaccination records for each student
- 🗓️ Manage vaccination drives
- 🔍 Search students by name or roll number
- 📁 Upload student data from CSV
- ⚡ Clean and modern UI
- 🔐 Token-based secure APIs (coming soon)

---

## 🚀 Tech Stack

- **Frontend:** React.js + Vite
- **Architecture:** MVVM (Model-View-ViewModel) Pattern
- **Routing:** React Router v6
- **Styling:** Bootstrap 5 + Custom CSS
- **Backend:** Spring Boot (Your API on `localhost:9091`)
- **API Testing/Docs:** Swagger UI

---

## 🏗️ Project Structure

```plaintext
vaccination-portal-frontend/
├── node_modules/
├── public/
├── src/
│   ├── api/                  # API request functions
│   ├── assets/               # Images, icons, etc.
│   ├── components/           # Reusable UI components
│   ├── hooks/                # Custom React hooks
│   ├── js/                   # JavaScript utilities (if any)
│   ├── layout/               # Common layout (Navbar, Footer etc.)
│   ├── models/               # Type models and data structures
│   ├── scss/                 # Styling (SCSS / CSS)
│   └── pages/                # Pages
│       ├── HomePage/
│       ├── LoginPage/
│       ├── StudentPages/
│           ├── AddStudentPage/
│           ├── EditStudentPage/
│           ├── ListStudentsPage/
│           ├── AddVaccinationPage/
│           ├── ViewStudentPage/
│       ├── VaccinationPages/
│           ├── AddDrivePage/
│           ├── EditVaccinationDrivePage/
│           ├── ListVaccinationDrivesPage/
├── App.jsx                   # Main routing configuration
├── App.css                    # Global CSS
└── index.js                   # React root
```
