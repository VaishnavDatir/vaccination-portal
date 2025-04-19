package com.school.vaccineportal.vaccination_portal;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.event.EventListener;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAsync
@EnableCaching
@EnableJdbcRepositories
@EnableSwagger2
@EnableAutoConfiguration(exclude = { ErrorMvcAutoConfiguration.class })
public class VaccinationPortalApplication {
	private static final Logger logger = LoggerFactory.getLogger(VaccinationPortalApplication.class);

	@Value("${server.port}")
	private String serverPort;

	public static void main(String[] args) {
		SpringApplication.run(VaccinationPortalApplication.class, args);
	}

	@EventListener
	public void onStartup(ApplicationReadyEvent event) {
		String machineName = Optional.ofNullable(System.getenv("HOSTNAME")).orElse(System.getenv("COMPUTERNAME"));
		logger.info("Service started on: {}:{}", machineName, serverPort);
	}
}
