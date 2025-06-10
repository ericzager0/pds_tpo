package com.pdstpo.unomas.services;

import com.pdstpo.unomas.model.entities.Match;

public interface IMatchService {
    Match create(Match match);
    Match getById(Integer id);
    void addPlayer(Integer matchId, Integer userId);
    void removePlayer(Integer matchId, Integer userId);
}
