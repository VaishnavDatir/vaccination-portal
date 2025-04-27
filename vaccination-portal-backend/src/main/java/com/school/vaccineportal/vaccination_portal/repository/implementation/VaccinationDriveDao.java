package com.school.vaccineportal.vaccination_portal.repository.implementation;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.school.vaccineportal.vaccination_portal.config.database.LoadJdbcQueries;
import com.school.vaccineportal.vaccination_portal.constants.QueryParams;
import com.school.vaccineportal.vaccination_portal.dto.TVaccinationDrivesDto;
import com.school.vaccineportal.vaccination_portal.repository.IVaccinationDriveDao;

@Repository
public class VaccinationDriveDao implements IVaccinationDriveDao {
    private static final Logger logger = LoggerFactory.getLogger(VaccinationDriveDao.class);

    private NamedParameterJdbcTemplate jdbcTemplate;

    VaccinationDriveDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Add a new vaccination drive
    @Override
    public int addVaccinationDrive(TVaccinationDrivesDto drive) {
        String query = LoadJdbcQueries.getQueryById("ADD_VACCINATION_DRIVE");
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue(QueryParams.VACCINE_NAME, drive.getVaccineName());
        paramSource.addValue(QueryParams.START_DATE, drive.getStartDate());
        paramSource.addValue(QueryParams.END_DATE, drive.getEndDate());
        paramSource.addValue(QueryParams.AVAILABLE_DOSES, drive.getAvailableDoses());
        paramSource.addValue(QueryParams.GRADE_ELIGIBILITY, drive.getGradeEligibility());

        logger.debug("Query for ADD_VACCINATION_DRIVE: {}", query);
        logger.debug("Param for ADD_VACCINATION_DRIVE: {}", paramSource);

        return jdbcTemplate.update(query, paramSource);
    }

    // Get a vaccination drive by ID
    @Override
    public Optional<TVaccinationDrivesDto> getVaccinationDriveById(Integer driveId) {
        String query = LoadJdbcQueries.getQueryById("GET_VACCINATION_DRIVE_BY_ID");
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue(QueryParams.DRIVE_ID, driveId);

        logger.debug("Query for GET_VACCINATION_DRIVE_BY_ID: {}", query);
        logger.debug("Param for GET_VACCINATION_DRIVE_BY_ID: {}", paramSource);

        List<TVaccinationDrivesDto> drives = jdbcTemplate.query(query, paramSource,
                new BeanPropertyRowMapper<>(TVaccinationDrivesDto.class));
        return drives.isEmpty() ? Optional.empty() : Optional.of(drives.get(0));
    }

    // Get all vaccination drives
    @Override
    public List<TVaccinationDrivesDto> getAllVaccinationDrives(int offset, int limit) {
        String query = LoadJdbcQueries.getQueryById("GET_ALL_VACCINATION_DRIVES");
        logger.debug("Query for GET_ALL_VACCINATION_DRIVES: {}", query);
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(QueryParams.LIMIT, limit);
        params.addValue(QueryParams.OFFSET, offset);
        return jdbcTemplate.query(query, params, new BeanPropertyRowMapper<>(TVaccinationDrivesDto.class));
    }

    // Update a vaccination drive
    @Override
    public int updateVaccinationDrive(TVaccinationDrivesDto drive) {
        String query = LoadJdbcQueries.getQueryById("UPDATE_VACCINATION_DRIVE");
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue(QueryParams.DRIVE_ID, drive.getDriveId());
        paramSource.addValue(QueryParams.VACCINE_NAME, drive.getVaccineName());
        paramSource.addValue(QueryParams.START_DATE, drive.getStartDate());
        paramSource.addValue(QueryParams.END_DATE, drive.getEndDate());
        paramSource.addValue(QueryParams.AVAILABLE_DOSES, drive.getAvailableDoses());
        paramSource.addValue(QueryParams.GRADE_ELIGIBILITY, drive.getGradeEligibility());

        logger.debug("Query for UPDATE_VACCINATION_DRIVE: {}", query);
        logger.debug("Param for UPDATE_VACCINATION_DRIVE: {}", paramSource);

        int count = jdbcTemplate.update(query, paramSource);
        logger.info("rows affected in UPDATE_VACCINATION_DRIVE: {}", count);
        return count;
    }

    // Delete a vaccination drive
    @Override
    public int deleteVaccinationDrive(Integer driveId) {
        String query = LoadJdbcQueries.getQueryById("DELETE_VACCINATION_DRIVE");
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue(QueryParams.DRIVE_ID, driveId);

        logger.debug("Query for DELETE_VACCINATION_DRIVE: {}", query);
        logger.debug("Param for DELETE_VACCINATION_DRIVE: {}", paramSource);

        int count = jdbcTemplate.update(query, paramSource);
        logger.info("rows affected in DELETE_VACCINATION_DRIVE: {}", count);
        return count;
    }

    @Override
    public Integer getTotalVaccinationDrivesCount() {
        String query = LoadJdbcQueries.getQueryById("GET_TOTAL_COUNT_VACCINATION_DRIVES");

        MapSqlParameterSource params = new MapSqlParameterSource();

        logger.debug("Counting VACCINATION_DRIVES");
        return jdbcTemplate.queryForObject(query, params, Integer.class);
    }

    @Override
    public List<TVaccinationDrivesDto> getAllActiveVaccinationDrives(int grade) {
        String query = LoadJdbcQueries.getQueryById("GET_ALL_VACCINATION_DRIVES_ACTIVE");
        logger.debug("Query for GET_ALL_VACCINATION_DRIVES_ACTIVE: {}", query);
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(QueryParams.GRADE, StringUtils.join("Grade ", grade));
        return jdbcTemplate.query(query, params, new BeanPropertyRowMapper<>(TVaccinationDrivesDto.class));
    }
}
