package com.software.eventmanagement.Feedback;

import com.software.eventmanagement.event.Event;
import jakarta.persistence.*;


@Entity
@Table(name = "feedback")
public class Feedback {

    @EmbeddedId
    private FeedbackId id;
    private String feedback;

    // Getters, setters, constructors
    public Feedback() {}

    public Feedback(FeedbackId id, String feedback) {
        this.id = id;
        this.feedback = feedback;
    }

    public FeedbackId getId() {
        return id;
    }

    public void setId(FeedbackId id) {
        this.id = id;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
