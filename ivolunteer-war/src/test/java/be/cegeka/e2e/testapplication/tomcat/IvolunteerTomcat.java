package be.cegeka.e2e.testapplication.tomcat;

import be.cegeka.e2e.UrlBuilder;
import be.cegeka.ivolunteer.war.Application;
import org.springframework.boot.builder.SpringApplicationBuilder;

public class IvolunteerTomcat {

    private final int port = UrlBuilder.TOMCAT_PORT;
    private static IvolunteerTomcat instance;

    private final String contextPath = UrlBuilder.TOMCAT_CONTEXT_PATH;
    private boolean started = false;

    private IvolunteerTomcat() {
    }

    public int port() {
        return port;
    }

    public String contextPath() {
        return contextPath;
    }

    public String baseUri() {
        return UrlBuilder.TOMCAT_BASE_URI + ":" + port();
    }

    public void start() {
        if (!started) {
            startTomcat();
            started = true;
        }
    }

    private void startTomcat() {
        new SpringApplicationBuilder()
                .sources(
                        Application.class
//                        PropertiesConfig.class
                )
                // Configuration on standalone tomcat is a little different, see https://tomcat.apache.org/tomcat-8.0-doc/config/http.html
                // compression, compressionMinSize, compressableMimeType
                .properties("server.compression.enabled=true")
                .properties("server.compression.mime-types=application/json")
                .properties("server.port:" + port)
                .properties("server.contextPath:" + contextPath)
                .run();
    }

    public static IvolunteerTomcat getInstance() {
        if (instance == null)
            instance = new IvolunteerTomcat();
        return instance;
    }
}
