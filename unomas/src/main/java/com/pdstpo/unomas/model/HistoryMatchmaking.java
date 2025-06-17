package com.pdstpo.unomas.model;

import com.pdstpo.unomas.model.entities.Match;
import com.pdstpo.unomas.model.entities.User;
import com.pdstpo.unomas.repositories.IUserRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HistoryMatchmaking implements IMatchmakingStrategy {

    IUserRepository userRepository;

    public HistoryMatchmaking(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> search(Match match) {
        return userRepository.findUsersWithMinCompletedMatches(match.getMinMatches(), match.getSport().getId());
    }
}
