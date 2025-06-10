package com.pdstpo.unomas.model;

import com.pdstpo.unomas.model.entities.Match;

public class MatchContext {
    private Match match;
    private State state;
    private IMatchmakingStrategy matchmakingStrategy;

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
    }

    public IMatchmakingStrategy getMatchmakingStrategy() {
        return matchmakingStrategy;
    }

    public void setMatchmakingStrategy(IMatchmakingStrategy matchmakingStrategy) {
        this.matchmakingStrategy = matchmakingStrategy;
    }
}
