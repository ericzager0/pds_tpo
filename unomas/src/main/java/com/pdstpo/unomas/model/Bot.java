package com.pdstpo.unomas.model;

import com.pdstpo.unomas.model.enums.StateEnum;
import com.pdstpo.unomas.repositories.IMatchRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Bot {

    @Autowired
    private IMatchRepository matchRepository;

    @Transactional
    @Scheduled(fixedRate = 60000)
    public void execute() {
        List<MatchContext> matches = matchRepository.findAllForBotService();

        for (MatchContext matchContext : matches) {
            StateEnum state = matchContext.getMatch().getState();

            if (state.equals(StateEnum.CONFIRMADO)) {
                matchContext.getState().init();
            } else if (state.equals(StateEnum.EN_JUEGO)) {
                matchContext.getState().end();
            }
        }

        for (MatchContext matchContext : matches) {
            matchRepository.save(matchContext.getMatch());
        }
    }
}
