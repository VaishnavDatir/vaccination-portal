package com.school.vaccineportal.vaccination_portal.repository.implementation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.school.vaccineportal.vaccination_portal.config.database.LoadJdbcQueries;
import com.school.vaccineportal.vaccination_portal.constants.QueryParams;
import com.school.vaccineportal.vaccination_portal.dto.TStudentVaccinationRecordDto;
import com.school.vaccineportal.vaccination_portal.repository.IStudentVaccinationRecordDao;

@Repository
public class StudentVaccinationRecordDao implements IStudentVaccinationRecordDao {
    private static final Logger logger = LoggerFactory.getLogger(StudentVaccinationRecordDao.class);

    private NamedParameterJdbcTemplate jdbcTemplate;

    StudentVaccinationRecordDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Add vaccination record
    @Override
    public int addVaccinationRecord(TStudentVaccinationRecordDto recordDto) {
        logger.debug("Adding vaccination record for student ID: {}", recordDto.getStudentId());

        String query = LoadJdbcQueries.getQueryById("ADD_STUDENT_VACCINATION_RECORD");
        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue(QueryParams.STUDENT_ID, recordDto.getStudentId());
        params.addValue(QueryParams.DRIVE_ID, recordDto.getDriveId());
        params.addValue(QueryParams.VACCINATION_DATE, recordDto.getVaccinationDate());
        params.addValue(QueryParams.DOSE_NUMBER, recordDto.getDoseNumber());
        params.addValue(QueryParams.STATUS, recordDto.getStatus());

        logger.debug("Query for ADD_STUDENT_VACCINATION_RECORD: {}", query);
        logger.debug("Param for ADD_STUDENT_VACCINATION_RECORD: {}", params);

        return jdbcTemplate.update(query, params);
    }

    // Get records by student ID
    @Override
    public List<TStudentVaccinationRecordDto> getVaccinationRecordsByStudentId(Integer studentId) {
        logger.debug("Fetching vaccination records for student ID: {}", studentId);

        String query = LoadJdbcQueries.getQueryById("GET_VACCINATION_RECORDS_BY_STUDENT_ID");
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(QueryParams.STUDENT_ID, studentId);

        logger.debug("Query for GET_VACCINATION_RECORDS_BY_STUDENT_ID: {}", query);
        logger.debug("Param for GET_VACCINATION_RECORDS_BY_STUDENT_ID: {}", params);

        return jdbcTemplate.query(query, params, new BeanPropertyRowMapper<>(TStudentVaccinationRecordDto.class));
    }

    // Update vaccination record
    @Override
    public int updateVaccinationRecord(TStudentVaccinationRecordDto recordDto) {
        logger.debug("Updating vaccination record for ID: {}", recordDto.getRecordId());

        String query = LoadJdbcQueries.getQueryById("UPDATE_STUDENT_VACCINATION_RECORD");
        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue(QueryParams.RECORD_ID, recordDto.getRecordId());
        params.addValue(QueryParams.STUDENT_ID, recordDto.getStudentId());
        params.addValue(QueryParams.DRIVE_ID, recordDto.getDriveId());
        params.addValue(QueryParams.VACCINATION_DATE, recordDto.getVaccinationDate());
        params.addValue(QueryParams.DOSE_NUMBER, recordDto.getDoseNumber());
        params.addValue(QueryParams.STATUS, recordDto.getStatus());

        logger.debug("Query for UPDATE_STUDENT_VACCINATION_RECORD: {}", query);
        logger.debug("Param for UPDATE_STUDENT_VACCINATION_RECORD: {}", params);

        int count = jdbcTemplate.update(query, params);
        logger.info("rows affected in UPDATE_STUDENT_VACCINATION_RECORD: {}", count);
        return count;
    }

    // Delete vaccination record
    @Override
    public int deleteVaccinationRecord(Integer recordId) {
        logger.debug("Deleting vaccination record ID: {}", recordId);

        String query = LoadJdbcQueries.getQueryById("DELETE_STUDENT_VACCINATION_RECORD");
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(QueryParams.RECORD_ID, recordId);

        logger.debug("Query for DELETE_STUDENT_VACCINATION_RECORD: {}", query);
        logger.debug("Param for DELETE_STUDENT_VACCINATION_RECORD: {}", params);

        int count = jdbcTemplate.update(query, params);
        logger.info("rows affected in DELETE_STUDENT_VACCINATION_RECORD: {}", count);
        return count;
    }
}
