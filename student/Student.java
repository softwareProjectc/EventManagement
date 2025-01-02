package com.software.eventmanagement.student;

import com.software.eventmanagement.entities.Person;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "students")
public class Student extends Person {
    private String major;
    @ElementCollection
    private List<Long> events;

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public List<Long> getEvents() {
        return events;
    }

    public void setEvents(List<Long> events) {
        this.events = events;
    }
}
