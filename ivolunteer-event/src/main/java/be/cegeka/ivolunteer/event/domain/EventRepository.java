package be.cegeka.ivolunteer.event.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.inject.Named;

@Named
public interface EventRepository extends JpaRepository<Event, String> {
}
