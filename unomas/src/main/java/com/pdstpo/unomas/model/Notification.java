package com.pdstpo.unomas.model;

import com.pdstpo.unomas.model.entities.User;

public class Notification {
    private User to;
    private String message;

    public Notification(User to, String message) {
        this.to = to;
        this.message = message;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
