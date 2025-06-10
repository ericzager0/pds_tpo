package com.pdstpo.unomas.model;

import com.pdstpo.unomas.model.entities.Match;
import com.pdstpo.unomas.model.entities.User;

import java.util.List;

public interface IMatchmakingStrategy {
    List<User> search(Match match);
}
