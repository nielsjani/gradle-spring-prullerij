package be.cegeka.ivolunteer.event.service;

import be.cegeka.ivolunteer.event.domain.Event;
import be.cegeka.ivolunteer.event.domain.EventRepository;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class EventService {
    @Inject
    private EventRepository eventRepository;

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event findEvent(String id){
        return eventRepository.findOne(id);
    }
}
