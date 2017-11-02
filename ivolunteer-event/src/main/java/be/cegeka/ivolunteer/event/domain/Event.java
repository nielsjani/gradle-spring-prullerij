package be.cegeka.ivolunteer.event.domain;

import be.cegeka.ivolunteer.domain.common.Persistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "EVENT")
public class Event extends Persistable {

    @Column(name = "NAAM", nullable = false)
    private String name;
    @Column(name = "BEGINDATUM", nullable = false)
    private Date begindatum;
    @Column(name = "EINDDATUM", nullable = false)
    private Date einddatum;

    private Event(EventBuilder builder) {
        this.name = builder.name;
        this.begindatum = builder.begindatum;
        this.einddatum = builder.einddatum;
    }

    public String getName() {
        return name;
    }

    public Date getBegindatum() {
        return begindatum;
    }

    public Date getEinddatum() {
        return einddatum;
    }

    public static class EventBuilder {
        private String name;
        private Date begindatum;
        private Date einddatum;

        public static EventBuilder event() {
            return new EventBuilder();
        }

        public Event build() {
            return new Event(this);
        }

        public EventBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public EventBuilder withBegindatum(Date begindatum) {
            this.begindatum = begindatum;
            return this;
        }

        public EventBuilder withEinddatum(Date einddatum) {
            this.einddatum = einddatum;
            return this;
        }
    }
}
