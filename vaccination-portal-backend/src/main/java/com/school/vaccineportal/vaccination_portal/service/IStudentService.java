package com.school.vaccineportal.vaccination_portal.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.school.vaccineportal.vaccination_portal.dto.TStudentsDto;
import com.school.vaccineportal.vaccination_portal.model.BulkUploadResponse;

public interface IStudentService {
    public void addStudent(TStudentsDto student);

    // Get student by ID
    public TStudentsDto getStudentById(Integer studentId);

    // Get all students
    public List<TStudentsDto> getAllStudents(int page, int size);

    // Update existing student
    public void updateStudent(TStudentsDto student);

    // Delete student
    public void deleteStudent(Integer studentId);

    // bulk uplad students data from csv
    public BulkUploadResponse bulkUploadStudentsFromCsv(MultipartFile file);

    public int getTotalStudentsCount();

}
