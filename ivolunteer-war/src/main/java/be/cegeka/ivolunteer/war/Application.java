package be.cegeka.ivolunteer.war;

import be.cegeka.ivolunteer.infrastructure.spring.InfrastructureConfig;
import be.cegeka.ivolunteer.war.spring.WarConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

@EnableAutoConfiguration
@Import({
        WarConfig.class,
        InfrastructureConfig.class
})
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
