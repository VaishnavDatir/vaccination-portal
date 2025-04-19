package com.school.vaccineportal.vaccination_portal.service;

import java.util.List;

import com.school.vaccineportal.vaccination_portal.dto.TStudentsDto;

public interface IStudentService {
    public void addStudent(TStudentsDto student);

    // Get student by ID
    public TStudentsDto getStudentById(Integer studentId);

    // Get all students
    public List<TStudentsDto> getAllStudents();

    // Update existing student
    public void updateStudent(TStudentsDto student);

    // Delete student
    public void deleteStudent(Integer studentId);

}
