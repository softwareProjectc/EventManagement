package com.software.eventmanagement.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.software.eventmanagement.Feedback.Feedback;
import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

enum Type {
    TRAINING,
    COMPETITION,
    WORKSHOP,
    LECTURE,
    INITIATIVE,
    EXHIBITION
}

@Table(name = "events")
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // automatic, not input by registration
    private String OrganizerId;
    private String location;
    private String services; // separate multiple services by comma
    private String phoneNumber;
    private String description;
    private String mentorName; // supervisor (faculty member)
    private Type type;
    private Date date;
    private short seats; // maximum number of seats
    private short participants; // number of current participants, not input by registration
    private short rating; // not input by registration
    private short numRatings;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime endTime;

    @OneToMany
    @JoinColumn(name = "id")
    private List<Feedback> feedback = new ArrayList<>(); // Not input by registration

    public Event(long id, String organizerId, String location, String services, String phoneNumber, String description, String mentorName, Type type, Date date, LocalTime startTime, LocalTime endTime, short seats, short participants, short numRatings) {
        this.id = id;
        this.OrganizerId = organizerId;
        this.location = location;
        this.services = services;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.date = date;
        this.mentorName = mentorName;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.seats = seats;
        this.participants = participants;
        this.rating = 0;
        this.numRatings = 0;
    }

    public Event(Event oldEventDetails, Event event) {
        this.id = oldEventDetails.getId(); // doesnt change
        this.OrganizerId = oldEventDetails.getOrganizerId(); // doesnt change
        this.feedback= oldEventDetails.getFeedback(); // cant edit
        this.rating = oldEventDetails.getRating(); // cant edit
        this.numRatings = oldEventDetails.getNumRatings(); // cant edit
        this.participants = oldEventDetails.getParticipants(); // cant edit

        this.location = event.getLocation();
        this.services = event.getServices();
        this.phoneNumber = event.getPhoneNumber();
        this.description = event.getDescription();
        this.mentorName = event.getMentorName();
        this.type = event.getType();
        this.startTime = event.getStartTime();
        this.seats = event.getSeats();
        this.participants = event.getParticipants();
        this.endTime = event.getEndTime();
        this.date = event.getDate();

    }



    public Event() {
    }

    public short getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(short numRatings) {
        this.numRatings = numRatings;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public short getRating() {
        return rating;
    }

    public void setRating(short rating) {
        this.rating = rating;
    }

    public List<Feedback> getFeedback() {
        return feedback;
    }

    public void setFeedback(List<Feedback> feedback) {
        this.feedback = feedback;
    }

    public short getParticipants() {
        return participants;
    }

    public void setParticipants(short participants) {
        this.participants = participants;
    }

    public short getSeats() {
        return seats;
    }

    public void setSeats(short seats) {
        this.seats = seats;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrganizerId() {
        return OrganizerId;
    }

    public void setOrganizerId(String organizerId) {
        OrganizerId = organizerId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMentorName() {
        return mentorName;
    }

    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

}
