package com.school.vaccineportal.vaccination_portal.service.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.school.vaccineportal.vaccination_portal.dto.TStudentsDto;
import com.school.vaccineportal.vaccination_portal.exception.InvalidInputException;
import com.school.vaccineportal.vaccination_portal.exception.ResourceNotFoundException;
import com.school.vaccineportal.vaccination_portal.model.BulkUploadResponse;
import com.school.vaccineportal.vaccination_portal.repository.IStudentDao;
import com.school.vaccineportal.vaccination_portal.service.IStudentService;

@Service
public class StudentService implements IStudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final IStudentDao studentsDao;

    StudentService(IStudentDao studentsDao) {
        this.studentsDao = studentsDao;
    }

    // Add a new student
    public void addStudent(TStudentsDto student) {
        validateStudentInput(student);
        studentsDao.addStudent(student);
    }

    // Get student by ID
    public TStudentsDto getStudentById(Integer studentId) {
        if (studentId == null || studentId <= 0) {
            throw new InvalidInputException("Invalid student ID provided");
        }

        TStudentsDto student = studentsDao.getStudentById(studentId);

        if (student == null) {
            throw new ResourceNotFoundException("Student not found with ID: " + studentId);
        }

        return student;
    }

    // Get all students
    @Override
    public List<TStudentsDto> getAllStudents(int page, int size) {
        int offset = page * size;
        return studentsDao.getAllStudents(offset, size);
    }

    @Override
    public int getTotalStudentsCount() {
        return studentsDao.getTotalCountOfStudents();
    }

    // Update existing student
    public void updateStudent(TStudentsDto student) {
        if (student.getStudentId() == null) {
            throw new InvalidInputException("Student ID is required for update.");
        }

        // Check if student exists
        TStudentsDto existing = studentsDao.getStudentById(student.getStudentId());
        if (existing == null) {
            throw new ResourceNotFoundException("Student not found with ID: " + student.getStudentId());
        }

        validateStudentInput(student);
        studentsDao.updateStudent(student);
    }

    // Delete student
    public void deleteStudent(Integer studentId) {
        if (studentId == null || studentId <= 0) {
            throw new InvalidInputException("Invalid student ID");
        }

        TStudentsDto existing = studentsDao.getStudentById(studentId);
        if (existing == null) {
            throw new ResourceNotFoundException("Student not found with ID: " + studentId);
        }

        studentsDao.deleteStudent(studentId);
    }

    public BulkUploadResponse bulkUploadStudentsFromCsv(MultipartFile file) {
        List<String> failedRecords = new ArrayList<>();
        int successCount = 0;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
                CSVParser csvParser = CSVParser.parse(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

            for (CSVRecord csvRecord : csvParser) {
                try {
                    TStudentsDto student = new TStudentsDto();
                    student.setFirstName(csvRecord.get("firstName").trim());
                    student.setLastName(csvRecord.get("lastName").trim());
                    student.setGrade(csvRecord.get("grade").trim());
                    student.setEmail(csvRecord.get("email").trim());
                    student.setRollNo(csvRecord.get("rollNo").trim());

                    studentsDao.addStudent(student);
                    successCount++;

                } catch (Exception ex) {
                    failedRecords.add("Row " + csvRecord.getRecordNumber() + ": " + ex.getMessage());
                }
            }

        } catch (IOException e) {
            throw new InvalidInputException("Failed to read CSV file: " + e.getMessage());
        }

        BulkUploadResponse response = new BulkUploadResponse();
        response.setSuccessCount(successCount);
        response.setFailureCount(failedRecords.size());
        response.setFailedRecords(failedRecords);

        return response;
    }

    // Validation logic for student
    private void validateStudentInput(TStudentsDto student) {
        if (isNullOrEmpty(student.getFirstName())) {
            throw new InvalidInputException("First name is required.");
        }
        if (isNullOrEmpty(student.getLastName())) {
            throw new InvalidInputException("Last name is required.");
        }
        if (isNullOrEmpty(student.getEmail())) {
            throw new InvalidInputException("Email is required.");
        }
        if (isNullOrEmpty(student.getGrade())) {
            throw new InvalidInputException("Grade is required.");
        }
        if (isNullOrEmpty(student.getRollNo())) {
            throw new InvalidInputException("Roll number is required.");
        }
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
