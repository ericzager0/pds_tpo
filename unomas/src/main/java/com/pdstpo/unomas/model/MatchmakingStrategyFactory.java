package com.pdstpo.unomas.model;

import com.pdstpo.unomas.model.enums.MatchmakingStrategyEnum;
import com.pdstpo.unomas.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MatchmakingStrategyFactory {

    @Autowired
    private IUserRepository userRepository;

    public IMatchmakingStrategy create(MatchmakingStrategyEnum strategyEnum) {
        return switch (strategyEnum) {
            case PROXIMITY -> new ProximityMatchmaking(userRepository);
            case LEVEL -> new LevelMatchmaking(userRepository);
            case HISTORY -> new HistoryMatchmaking(userRepository);
        };
    }
}
