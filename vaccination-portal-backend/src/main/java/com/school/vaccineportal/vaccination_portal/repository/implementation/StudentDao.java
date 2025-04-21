package com.school.vaccineportal.vaccination_portal.repository.implementation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.school.vaccineportal.vaccination_portal.config.database.LoadJdbcQueries;
import com.school.vaccineportal.vaccination_portal.constants.QueryParams;
import com.school.vaccineportal.vaccination_portal.dto.TStudentsDto;
import com.school.vaccineportal.vaccination_portal.exception.ResourceNotFoundException;
import com.school.vaccineportal.vaccination_portal.repository.IStudentDao;

@Repository
public class StudentDao implements IStudentDao {
    private static final Logger logger = LoggerFactory.getLogger(StudentDao.class);

    private NamedParameterJdbcTemplate jdbcTemplate;

    StudentDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Add a new student to the database
    @Override
    public int addStudent(TStudentsDto student) {
        logger.debug("adding new student: {}", student.getRollNo());

        String query = LoadJdbcQueries.getQueryById("ADD_NEW_STUDENT");
        MapSqlParameterSource paramSource = new MapSqlParameterSource();

        paramSource.addValue(QueryParams.FIRST_NAME, student.getFirstName());
        paramSource.addValue(QueryParams.LAST_NAME, student.getLastName());
        paramSource.addValue(QueryParams.GENDER, student.getGender());
        paramSource.addValue(QueryParams.GRADE, student.getGrade());
        paramSource.addValue(QueryParams.EMAIL, student.getEmail());
        paramSource.addValue(QueryParams.ROLL_NO, student.getRollNo());

        logger.debug("query for ADD_NEW_STUDENT: {}", query);
        logger.debug("param for ADD_NEW_STUDENT: {}", paramSource);

        return jdbcTemplate.update(query, paramSource);
    }

    // Get a student by their ID
    @Override
    public TStudentsDto getStudentById(Integer studentId) {
        String query = LoadJdbcQueries.getQueryById("GET_STUDENT_BY_ID");

        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue(QueryParams.STUDENT_ID, studentId);

        logger.debug("Query for GET_STUDENT_BY_ID: {}", query);
        logger.debug("Param for GET_STUDENT_BY_ID: {}", paramSource);

        try {
            return jdbcTemplate.queryForObject(query, paramSource,
                    BeanPropertyRowMapper.newInstance(TStudentsDto.class));
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Student not found with ID: " + studentId);
        }
    }

    // Get all students
    @Override
    public List<TStudentsDto> getAllStudents(int offset, int limit) {
        String query = LoadJdbcQueries.getQueryById("GET_ALL_STUDENTS");
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(QueryParams.LIMIT, limit);
        params.addValue(QueryParams.OFFSET, offset);
        logger.debug("Query for GET_ALL_STUDENTS: {}", query);

        return jdbcTemplate.query(query, params, new BeanPropertyRowMapper<>(TStudentsDto.class));
    }

    // Update a student's details
    @Override
    public int updateStudent(TStudentsDto student) {
        String query = LoadJdbcQueries.getQueryById("UPDATE_STUDENT");

        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue(QueryParams.STUDENT_ID, student.getStudentId());
        paramSource.addValue(QueryParams.FIRST_NAME, student.getFirstName());
        paramSource.addValue(QueryParams.LAST_NAME, student.getLastName());
        paramSource.addValue(QueryParams.GENDER, student.getGender());
        paramSource.addValue(QueryParams.GRADE, student.getGrade());
        paramSource.addValue(QueryParams.EMAIL, student.getEmail());
        paramSource.addValue(QueryParams.ROLL_NO, student.getRollNo());

        logger.debug("Query for UPDATE_STUDENT: {}", query);
        logger.debug("Parameters for UPDATE_STUDENT: {}", paramSource);

        int count = jdbcTemplate.update(query, paramSource);
        logger.info("rows affected in UPDATE_STUDENT: {}", count);
        return count;
    }

    // Delete a student by their ID
    @Override
    public int deleteStudent(Integer studentId) {
        String query = LoadJdbcQueries.getQueryById("DELETE_STUDENT");

        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue(QueryParams.STUDENT_ID, studentId);

        logger.debug("Query for DELETE_STUDENT: {}", query);
        logger.debug("Param for DELETE_STUDENT: {}", paramSource);

        int count = jdbcTemplate.update(query, paramSource);
        logger.info("rows affected in DELETE_STUDENT: {}", count);
        return count;
    }

    @Override
    public int getTotalCountOfStudents() {
        String query = LoadJdbcQueries.getQueryById("GET_TOTAL_COUNT_STUDENTS");

        MapSqlParameterSource params = new MapSqlParameterSource();

        logger.debug("Counting totalStudents");
        return jdbcTemplate.queryForObject(query, params, Integer.class);
    }
}
