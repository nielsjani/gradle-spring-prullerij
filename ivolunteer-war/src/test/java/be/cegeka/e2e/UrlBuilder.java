package be.cegeka.e2e;

import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class UrlBuilder {

    public static final String TOMCAT_BASE_URI = "http://localhost";
    public static final String TOMCAT_CONTEXT_PATH = "/ivolunteer";
    public static final int TOMCAT_PORT = 2025;

    @Inject
    private JdbcTemplate jdbcTemplate;
    @Inject
    private Environment environment;

    public static String tomcatUrl() {
        return TOMCAT_BASE_URI + ":" + TOMCAT_PORT + TOMCAT_CONTEXT_PATH;
    }

    public static String projectbeheerUIUrl() {
        return TOMCAT_BASE_URI + ":" + TOMCAT_PORT + TOMCAT_CONTEXT_PATH;
    }

}
