package com.pdstpo.unomas.model;

import com.pdstpo.unomas.model.entities.Match;
import com.pdstpo.unomas.model.entities.User;
import com.pdstpo.unomas.repositories.IUserRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LevelMatchmaking implements IMatchmakingStrategy {

    IUserRepository userRepository;

    public LevelMatchmaking(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> search(Match match) {
        Integer min = (match.getMinLevel() != null) ? match.getMinLevel().ordinal() + 1 : null;
        Integer max = (match.getMaxLevel() != null) ? match.getMaxLevel().ordinal() + 1 : null;

        return userRepository.findUsersByLevel(match.getSport().getId(), min, max);
    }
}
