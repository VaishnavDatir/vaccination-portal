package com.school.vaccineportal.vaccination_portal.repository.implementation;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.school.vaccineportal.vaccination_portal.config.database.LoadJdbcQueries;
import com.school.vaccineportal.vaccination_portal.constants.QueryParams;
import com.school.vaccineportal.vaccination_portal.dto.VaccinationReportDto;
import com.school.vaccineportal.vaccination_portal.repository.IReportDao;

@Repository
public class ReportDao implements IReportDao {
    private static final Logger logger = LoggerFactory.getLogger(ReportDao.class);

    private NamedParameterJdbcTemplate jdbcTemplate;

    ReportDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<VaccinationReportDto> getVaccinationReport(String vaccineName, String grade) {
        String query = LoadJdbcQueries.getQueryById("GET_VACCINATION_REPORT");

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(QueryParams.VACCINE_NAME, StringUtils.isBlank(vaccineName) ? null : vaccineName);
        params.addValue(QueryParams.GRADE, StringUtils.isBlank(grade) ? null : grade);

        logger.debug("Fetching vaccination report with params: {}", params);

        return jdbcTemplate.query(query, params, new BeanPropertyRowMapper<>(VaccinationReportDto.class));
    }

    @Override
    public int getVaccinationReportCount(String vaccineName) {
        String query = LoadJdbcQueries.getQueryById("GET_VACCINATION_REPORT_COUNT");

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(QueryParams.VACCINE_NAME, vaccineName);

        logger.debug("Counting report rows with vaccineName: {}", vaccineName);
        return jdbcTemplate.queryForObject(query, params, Integer.class);
    }
}
