package com.software.eventmanagement.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventsRepository repository;

    public List<Event> findAll() {
        return repository.findAll();
    }

    public Event save(Event event) {
        return repository.save(event);
    }
    public boolean save(Event event, String userId) {
        if(userId == null || userId.isEmpty())
            return false;
        event.setOrganizerId(userId);
        repository.save(event);
        return true;
    }

    public Event findById(long id) {
        if (repository.findById(id).isPresent())
            return repository.findById(id).get();
        return null;
    }

    public boolean delete(long id, String userId) {
        Event event = findById(id);
        if (event != null && event.getOrganizerId().equals(userId)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public Event edit(long id, String userId, Event newEventDetails) {
        Event event = findById(id);
        if(event != null) {
            String organizer = event.getOrganizerId();
            if (organizer.equals(userId)) {
                Event newEvent = new Event(event, newEventDetails);
                repository.save(newEvent);
                return newEvent;
            }
        }

        return null;
    }


}
