package com.software.eventmanagement.event;

import com.software.eventmanagement.cookies.CookieController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.findAll();
    }

    //tested successfully
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Event event = eventService.findById(id);
        if (event != null) {
            return ResponseEntity.ok(event);
        }
        return ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<?> createEvent(@RequestBody Event event, @CookieValue(value= "userAuthenticationToken") String userId) {
        userId = CookieController.getUsernameFromCookie(userId);
        if(eventService.save(event, userId))
            return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"Validation successful\"}");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\": \"Invalid organizer\"}");
    }

    // tested successfully 
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable Long id, @CookieValue(value= "userAuthenticationToken") String userId, @RequestBody Event eventDetails) {
        userId = CookieController.getUsernameFromCookie(userId);
        Event updatedEvent = eventService.edit(id, userId, eventDetails);
        if (updatedEvent != null) {
            return ResponseEntity.ok(updatedEvent);
        }
        return ResponseEntity.badRequest().build();
    }

    // not fully tested
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id, @CookieValue(value= "userAuthenticationToken") String userId) {
        userId = CookieController.getUsernameFromCookie(userId);
        boolean deleted = eventService.delete(id, userId);
        if (deleted) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
