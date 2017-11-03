package be.cegeka.ivolunteer.event.service;


import be.cegeka.ivolunteer.event.domain.Event;
import be.cegeka.ivolunteer.event.domain.EventIntegrationTest;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static be.cegeka.ivolunteer.event.domain.Event.EventBuilder.event;

public class EventServiceIntegrationTest extends EventIntegrationTest{

    @Inject
    private EventService eventService;

    @Test
    public void saveAndFind() {
        Event saved = eventService.createEvent(event()
                .withName("Checkers, mate")
                .withBegindatum(Date.from(LocalDate.of(2017, 2, 2).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .withEinddatum(Date.from(LocalDate.of(2017, 3, 3).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build());

        Event retrieved = eventService.findEvent(saved.getId());

        Assertions.assertThat(saved).isEqualTo(retrieved);
    }

}