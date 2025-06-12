package com.pdstpo.unomas.model;

import com.pdstpo.unomas.model.entities.Match;
import com.pdstpo.unomas.model.entities.User;

public class Notification {
    private User to;
    private Match match;
    private String message;

    public Notification(User to, Match match, String message) {
        this.to = to;
        this.match = match;
        this.message = message;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
