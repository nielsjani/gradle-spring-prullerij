package be.cegeka.e2e;

import be.cegeka.e2e.testapplication.TestApplication;
import be.cegeka.ivolunteer.event.spring.EventConfig;
import be.cegeka.ivolunteer.infrastructure.spring.InfrastructureConfig;
import org.junit.Before;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(classes = {
        EventConfig.class,
        InfrastructureConfig.class
})
public abstract class EndToEndTest extends AbstractJUnit4SpringContextTests {
    protected TestApplication testApplication;

    @Before
    public void startBackend() {
        testApplication = TestApplication.getInstance().start();
    }
}
