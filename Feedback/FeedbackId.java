package com.software.eventmanagement.Feedback;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FeedbackId implements Serializable {
    private String username;
    private Long eventId;

    public FeedbackId(String username, Long eventId) {
        this.username = username;
        this.eventId = eventId;
    }

    public FeedbackId() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
    @Override
    public String toString() {
        return "FeedbackId{" +
                "username='" + username + '\'' +
                ", eventId='" + eventId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FeedbackId that = (FeedbackId) o;
        return Objects.equals(username, that.username) && Objects.equals(eventId, that.eventId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, eventId);
    }
}
