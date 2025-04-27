import React from "react";

export default function Footer() {
  return (
    <footer className="bg-dark text-light mt-auto py-4">
      <div className="container text-center small">
        <div className="mb-2">
          🛠 Developed by <strong>Vaishnav Datir</strong> | 2024TM93176
        </div>
        <div className="mb-2">
          🔗 API Documentation:{" "}
          <a
            href="http://localhost:9091/swagger-ui/#/"
            target="_blank"
            rel="noopener noreferrer"
            className="text-info text-decoration-underline"
          >
            Swagger UI
          </a>
        </div>
        <div className="text-info">Built using React + Spring Boot</div>
      </div>
    </footer>
  );
}
