package com.school.vaccineportal.vaccination_portal.repository.implementation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.school.vaccineportal.vaccination_portal.config.database.LoadJdbcQueries;
import com.school.vaccineportal.vaccination_portal.dto.DashboardVaccinationDriveDto;
import com.school.vaccineportal.vaccination_portal.dto.DashboardVaccinationPercentageDto;
import com.school.vaccineportal.vaccination_portal.repository.IDashboardDao;

@Repository
public class DashboardDao implements IDashboardDao {
    private static final Logger logger = LoggerFactory.getLogger(DashboardDao.class);

    private NamedParameterJdbcTemplate jdbcTemplate;

    DashboardDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Fetch Total Students
    @Override
    public int getTotalStudents() {
        String query = LoadJdbcQueries.getQueryById("GET_TOTAL_STUDENTS");
        return jdbcTemplate.queryForObject(query, new MapSqlParameterSource(), Integer.class);
    }

    // Fetch Total Vaccinated Students
    @Override
    public int getTotalVaccinated() {
        String query = LoadJdbcQueries.getQueryById("GET_TOTAL_VACCINATED");
        return jdbcTemplate.queryForObject(query, new MapSqlParameterSource(), Integer.class);
    }

    // Fetch Vaccinated Percentage by Grade
    @Override
    public List<DashboardVaccinationPercentageDto> getVaccinatedPercentageByGrade() {
        String query = LoadJdbcQueries.getQueryById("GET_VACCINATED_PERCENTAGE_BY_GRADE");
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(DashboardVaccinationPercentageDto.class));
    }

    // Fetch Upcoming Vaccination Drives
    @Override
    public List<DashboardVaccinationDriveDto> getUpcomingDrives() {
        String query = LoadJdbcQueries.getQueryById("GET_UPCOMING_DRIVES");
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(DashboardVaccinationDriveDto.class));
    }

}
