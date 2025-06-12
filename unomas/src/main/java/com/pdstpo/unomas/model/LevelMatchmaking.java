package com.pdstpo.unomas.model;

import com.pdstpo.unomas.model.entities.Match;
import com.pdstpo.unomas.model.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LevelMatchmaking implements IMatchmakingStrategy {

    @Override
    public List<User> search(Match match) {
        return List.of();
    }
}
