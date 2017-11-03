package be.cegeka.ivolunteer.event.domain;


import be.cegeka.ivolunteer.event.spring.EventConfig;
import be.cegeka.ivolunteer.infrastructure.spring.InfrastructureConfig;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(classes = {
        EventConfig.class,
        InfrastructureConfig.class
})
public abstract class EventIntegrationTest  extends AbstractTransactionalJUnit4SpringContextTests {

    //TODO: dbCleaner
    //TODO: db test config
}
