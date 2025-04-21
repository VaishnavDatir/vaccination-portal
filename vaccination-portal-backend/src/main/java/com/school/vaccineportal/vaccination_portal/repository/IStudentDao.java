package com.school.vaccineportal.vaccination_portal.repository;

import java.util.List;

import com.school.vaccineportal.vaccination_portal.dto.TStudentsDto;

public interface IStudentDao {

    // Add a new student to the database
    public int addStudent(TStudentsDto student);

    // Get a student by their ID
    public TStudentsDto getStudentById(Integer studentId);

    // Get all students
    public List<TStudentsDto> getAllStudents(int offset, int limit);

    // Update a student's details
    public int updateStudent(TStudentsDto student);

    // Delete a student by their ID
    public int deleteStudent(Integer studentId);

    public int getTotalCountOfStudents();
}
