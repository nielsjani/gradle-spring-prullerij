package be.cegeka.ivolunteer.event.api;

import be.cegeka.ivolunteer.event.domain.Event;

import javax.inject.Named;

import static be.cegeka.ivolunteer.event.domain.Event.EventBuilder.event;

@Named
public class EventMapper {

    public Event toDomain(EventDto eventDto) {
        return event()
                .withName(eventDto.name)
                .withBegindatum(eventDto.begindatum)
                .withEinddatum(eventDto.einddatum).build();
    }

    public EventDto toDto(Event event){
        return EventDto.eventDto()
                .withId(event.getId())
                .withVersie(event.getVersie())
                .withBegindatum(event.getBegindatum())
                .withEinddatum(event.getEinddatum())
                .withName(event.getName());
    }
}
