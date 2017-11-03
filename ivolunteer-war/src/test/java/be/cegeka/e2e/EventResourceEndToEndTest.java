package be.cegeka.e2e;

import be.cegeka.ivolunteer.event.api.EventDto;
import be.cegeka.ivolunteer.event.domain.Event;
import be.cegeka.ivolunteer.event.domain.EventRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import javax.inject.Inject;
import java.util.Date;

import static be.cegeka.ivolunteer.event.domain.Event.EventBuilder.event;
import static org.springframework.http.HttpStatus.OK;

public class EventResourceEndToEndTest extends ResourceEndToEndTest {

    @Inject
    private EventRepository eventRepository;

    @Test
    public void getEvent() {
        Event event = eventRepository.save(event().withName("a").withBegindatum(new Date()).withEinddatum(new Date()).build());

        EventDto actual = givenRequest()
                .when()
                .get(urlFor("/api/events/" + event.getId()))
                .then()
                .assertThat()
                .statusCode(OK.value())
                .extract()
                .body()
                .as(EventDto.class);

        Assertions.assertThat(actual.name).isEqualTo("a");
    }
}
