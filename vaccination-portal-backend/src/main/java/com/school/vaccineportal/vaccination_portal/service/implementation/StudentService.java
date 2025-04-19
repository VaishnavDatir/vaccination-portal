package com.school.vaccineportal.vaccination_portal.service.implementation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.school.vaccineportal.vaccination_portal.dto.TStudentsDto;
import com.school.vaccineportal.vaccination_portal.exception.InvalidInputException;
import com.school.vaccineportal.vaccination_portal.exception.ResourceNotFoundException;
import com.school.vaccineportal.vaccination_portal.repository.IStudentDao;
import com.school.vaccineportal.vaccination_portal.service.IStudentService;

@Service
public class StudentService implements IStudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final IStudentDao studentsDao;

    public StudentService(IStudentDao studentsDao) {
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
    public List<TStudentsDto> getAllStudents() {
        return studentsDao.getAllStudents();
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
