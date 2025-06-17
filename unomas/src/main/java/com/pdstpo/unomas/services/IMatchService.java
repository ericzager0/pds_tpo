package com.pdstpo.unomas.services;

import com.pdstpo.unomas.model.entities.Comment;
import com.pdstpo.unomas.model.entities.Match;
import com.pdstpo.unomas.model.enums.StateEnum;

import java.util.List;

public interface IMatchService {
    Match create(Match match);
    Match getById(Integer id);
    void addPlayer(Integer matchId, Integer userId);
    void removePlayer(Integer matchId, Integer userId);
    void addComment(Integer matchId, String comment, Integer userId);
    List<Comment> getAllComments(Integer matchId);
    void updateMatch(Integer matchId, Integer userId, StateEnum state);
    List<Match> search(Integer sportId, Integer userId);
}
