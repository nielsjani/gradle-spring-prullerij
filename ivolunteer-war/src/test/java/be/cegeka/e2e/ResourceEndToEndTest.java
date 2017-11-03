package be.cegeka.e2e;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.ObjectMapperConfig;
import com.jayway.restassured.config.RestAssuredConfig;
import com.jayway.restassured.specification.RequestSpecification;
import org.junit.Before;

import javax.inject.Inject;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.http.ContentType.JSON;

public abstract class ResourceEndToEndTest extends EndToEndTest {

    @Inject
    protected ObjectMapper objectMapper;

    @Before
    public void configureRestAssuredObjectMapper() {
        RestAssured.config = RestAssuredConfig
                .config()
                .objectMapperConfig(
                        new ObjectMapperConfig()
                                .jackson2ObjectMapperFactory((aClass, s) -> objectMapper));
    }

    protected RequestSpecification givenRequest() {
        return given()
                .baseUri(testApplication.baseUri())
                .port(testApplication.port())
                .contentType(JSON);
    }

    protected String urlFor(String path) {
        return testApplication.contextPath() + path;
    }

}
