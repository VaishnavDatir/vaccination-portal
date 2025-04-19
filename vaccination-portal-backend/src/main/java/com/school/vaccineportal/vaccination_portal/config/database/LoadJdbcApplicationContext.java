package com.school.vaccineportal.vaccination_portal.config.database;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LoadJdbcApplicationContext {
    private static ApplicationContext applicationContext = null;
    private static final String DATABASE_QUERY = "database-queries.xml";

    private LoadJdbcApplicationContext() {

    }

    public static synchronized ApplicationContext getApplicationContext() {
        if (applicationContext == null) {
            applicationContext = new ClassPathXmlApplicationContext(DATABASE_QUERY);
        }
        return applicationContext;
    }
}
