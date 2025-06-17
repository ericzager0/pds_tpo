package com.pdstpo.unomas.model;

import com.pdstpo.unomas.model.entities.Match;
import com.pdstpo.unomas.model.entities.User;

public class MatchContext {
    private Match match;
    private State state;
    private IMatchmakingStrategy matchmakingStrategy;

    public MatchContext() {
    }

    public MatchContext(State state) {
        this.state = state;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;

        Notifier notifier = new Notifier();

        for (User user : match.getPlayers()) {
            notifier.notify(new Notification(user, "El partido con id " + match.getId() + " ahora está " + match.getState()));
        }
    }

    public IMatchmakingStrategy getMatchmakingStrategy() {
        return matchmakingStrategy;
    }

    public void setMatchmakingStrategy(IMatchmakingStrategy matchmakingStrategy) {
        this.matchmakingStrategy = matchmakingStrategy;
    }
}
