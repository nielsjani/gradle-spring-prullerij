package be.cegeka.ivolunteer.event.api;

import java.io.Serializable;
import java.util.Date;

public class EventDto implements Serializable {
    public String id;
    public int versie;
    public String name;
    public Date begindatum;
    public Date einddatum;

    private EventDto(){}

    public static EventDto eventDto(){
        return new EventDto();
    }

    public EventDto withId(String id) {
        this.id = id;
        return this;
    }

    public EventDto withVersie(int versie) {
        this.versie = versie;
        return this;
    }

    public EventDto withName(String name) {
        this.name = name;
        return this;
    }

    public EventDto withBegindatum(Date begindatum) {
        this.begindatum = begindatum;
        return this;
    }

    public EventDto withEinddatum(Date einddatum) {
        this.einddatum = einddatum;
        return this;
    }
}
