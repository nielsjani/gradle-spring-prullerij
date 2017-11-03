package be.cegeka.ivolunteer.event.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import static org.springframework.context.annotation.FilterType.ANNOTATION;

@Configuration
@ComponentScan(
        value = "be.cegeka.ivolunteer.event",
        excludeFilters = @ComponentScan.Filter(type = ANNOTATION, value = Configuration.class))
@EnableJpaRepositories(basePackages = "be.cegeka.ivolunteer.event")
public class EventConfig {
}
