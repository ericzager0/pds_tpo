package com.pdstpo.unomas.model;

import com.pdstpo.unomas.model.entities.Comment;
import com.pdstpo.unomas.model.entities.User;

public abstract class State {
    private MatchContext matchContext;

    public abstract void addPlayer(User user);
    public abstract void removePlayer(User user);
    public abstract void confirm(User requestingUser);
    public abstract void cancel(User requestingUser);
    public abstract void addComment(Comment comment);
    public abstract void init();
    public abstract void end();

    public MatchContext getMatchContext() {
        return matchContext;
    }

    public void setMatchContext(MatchContext matchContext) {
        this.matchContext = matchContext;
    }
}
