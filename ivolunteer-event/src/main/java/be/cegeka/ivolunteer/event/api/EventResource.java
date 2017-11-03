package be.cegeka.ivolunteer.event.api;

import be.cegeka.ivolunteer.event.service.EventService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/events")
public class EventResource {

    @Inject
    private EventMapper eventMapper;
    @Inject
    private EventService eventService;

    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE, value = "{id}")
    public EventDto getEvent(@PathVariable(value = "id") String id){
        return eventMapper.toDto(eventService.findEvent(id));
    }
}
