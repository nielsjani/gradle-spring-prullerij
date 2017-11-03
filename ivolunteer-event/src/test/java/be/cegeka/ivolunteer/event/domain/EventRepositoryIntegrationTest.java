package be.cegeka.ivolunteer.event.domain;

import org.junit.Test;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static be.cegeka.ivolunteer.event.domain.Event.EventBuilder.event;
import static org.assertj.core.api.Assertions.assertThat;

public class EventRepositoryIntegrationTest extends EventIntegrationTest {

    @Inject
    private EventRepository eventRepository;

    @Test
    public void saveAndFind() {
        Event saved = eventRepository.save(event()
                .withName("Checkmate")
                .withBegindatum(Date.from(LocalDate.of(2017, 2, 2).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .withEinddatum(Date.from(LocalDate.of(2017, 3, 3).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build());

        Event actual = eventRepository.findOne(saved.getId());

        assertThat(actual).isEqualTo(saved);
    }
}