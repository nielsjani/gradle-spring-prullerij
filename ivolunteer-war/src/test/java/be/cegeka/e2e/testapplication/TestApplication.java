package be.cegeka.e2e.testapplication;

import be.cegeka.e2e.UrlBuilder;
import be.cegeka.e2e.testapplication.tomcat.IvolunteerTomcat;
import com.jayway.restassured.specification.RequestSpecification;
import org.hamcrest.CoreMatchers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;

import static be.cegeka.e2e.testapplication.Poller.aPoller;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.IsNot.not;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

public class TestApplication {

    private static TestApplication instance = null;

    private final IvolunteerTomcat tomcat;
    private final String baseUri = UrlBuilder.TOMCAT_BASE_URI;
    private boolean started = false;

    private TestApplication() {
        tomcat = IvolunteerTomcat.getInstance();
    }

    public String baseUri() {
        return baseUri;
    }

    public int port() {
        return tomcat.port();
    }

    public String contextPath() {
        return tomcat.contextPath();
    }

    public String projectBeheerUrl() {
        return UrlBuilder.projectbeheerUIUrl();
    }

    public TestApplication start() {
        if (!alreadyStarted()) {
            if (!tomcatAlreadyStarted()) {
                startTomcat();
            } else {
                System.out.println("Skipping Tomcat en Proxy startup. Already running.");
            }
            waitUntilStarted();
            started = true;
            verifyHealth();
        }
        return this;
    }

    private boolean alreadyStarted() {
        return started;
    }

    private boolean tomcatAlreadyStarted() {
        return statusPageAlreadyAccessible();
    }

    private boolean statusPageAlreadyAccessible() {
        try {
            RestTemplate template = new RestTemplate();
            ResponseEntity<String> responseEntity = template.getForEntity(projectBeheerUrl() + "/status", String.class);
            return responseEntity.getStatusCode().equals(OK);
        } catch(ResourceAccessException e) {
            return false;
        }
    }

    /**
     * Checks to see if a specific port is available.
     *
     * @param port the port to check for availability
     */
    public static boolean available(int port) {
        ServerSocket ss = null;
        DatagramSocket ds = null;
        try {
            ss = new ServerSocket(port);
            ss.setReuseAddress(true);
            ds = new DatagramSocket(port);
            ds.setReuseAddress(true);
            return true;
        } catch (IOException e) {
        } finally {
            if (ds != null) {
                ds.close();
            }

            if (ss != null) {
                try {
                    ss.close();
                } catch (IOException e) {
                /* should not be thrown */
                }
            }
        }

        return false;
    }
    private void startTomcat() {
        tomcat.start();
    }

    private void waitUntilStarted() {
        aPoller()
            .doAssert((Assertion) () ->
                givenAuthenticatedRequest()
                    .when()
                    .get(contextPath() + "/health")
                    .then()
                    .assertThat()
                    .statusCode(not(NOT_FOUND.value()))
            );
    }

    private void verifyHealth() {
        givenAuthenticatedRequest()
            .when()
            .get(contextPath() + "/health")
            .then()
            .assertThat()
            .statusCode(OK.value())
            .contentType(JSON)
            .body(CoreMatchers.not(containsString("DOWN")));
        System.out.println("Health page ok!");
    }

    private RequestSpecification givenAuthenticatedRequest() {
        return given()
            .baseUri(baseUri())
            .port(port());
    }
//
//    public MimicAanmeldenPagina open() {
//        Selenide.open(projectBeheerUrl());
//        return new MimicAanmeldenPagina();
//    }
//
//    public LokaalProjectOverzichtPagina openOpStartPagina() {
//        Selenide.open(projectBeheerUrl());
//        return new LokaalProjectOverzichtPagina();
//    }

    public static TestApplication getInstance() {
        if (instance == null)
            instance = new TestApplication();
        return instance;
    }

    public static void main(String[] args) {
        TestApplication.getInstance().start();
//        System.out.println(new TestApplication().tomcatAlreadyStarted());
    }
}
