package com.school.vaccineportal.vaccination_portal.config.database;

import java.util.Map;

public class LoadJdbcQueries {
    private static Map<String, String> queryMap = null;

    private static Map<String, String> queryMapReport = null;

    private LoadJdbcQueries() {

    }

    @SuppressWarnings("unchecked")
    public static synchronized Map<String, String> getQueriesMap() {
        if (queryMap == null) {
            Object obj = LoadJdbcApplicationContext.getApplicationContext().getBean("queries");

            queryMap = (Map<String, String>) obj;

        }
        return queryMap;
    }

    public static String getQueryById(String queryId) {
        String query = null;
        query = getQueriesMap().get(queryId);
        return query;
    }

    /**
     * @Function : to load xml based on name of bean (bean name mapped with xml bean
     *           id param)
     * @param queryBean
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static synchronized Map<String, String> getQueriesMap(String queryBean) {
        if (queryMapReport == null) {
            Object obj = LoadJdbcApplicationContext.getApplicationContext().getBean(queryBean);

            queryMapReport = (Map<String, String>) obj;

        }
        return queryMapReport;
    }

    /**
     * @Function : to fetch the query from passed querybean (bean name mapped with
     *           xml bean id param)
     * @param queryBean
     * @param queryId
     * @return
     * @throws Exception
     */
    public static String getQueryById(String queryBean, String queryId) {
        String query = null;

        query = getQueriesMap(queryBean).get(queryId);

        return query;
    }
}
