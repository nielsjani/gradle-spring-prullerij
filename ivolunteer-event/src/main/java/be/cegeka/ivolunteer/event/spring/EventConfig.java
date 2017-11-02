package be.cegeka.ivolunteer.event.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static org.springframework.context.annotation.FilterType.ANNOTATION;

@Configuration
@ComponentScan(
        value = "be.cegeka.ivolunteer.event",
        excludeFilters = @ComponentScan.Filter(type = ANNOTATION, value = Configuration.class))
public class EventConfig {
}
