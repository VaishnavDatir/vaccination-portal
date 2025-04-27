import React from "react";
import Footer from "../components/Footer";

export default function Layout({ children }) {
  return (
    <div className="bg-light min-vh-100 d-flex flex-column">
      <div className="flex-grow-1">{children}</div>

      <Footer />
    </div>
  );
}
