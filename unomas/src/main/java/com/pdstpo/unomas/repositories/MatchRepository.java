package com.pdstpo.unomas.repositories;

import com.pdstpo.unomas.model.*;
import com.pdstpo.unomas.model.entities.Match;
import com.pdstpo.unomas.model.enums.StateEnum;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MatchRepository implements IMatchRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    MatchmakingStrategyFactory matchmakingStrategyFactory;

    @Override @Transactional
    public Match save(Match match) {
        if (match.getId() == null) {
            match.setState(StateEnum.NECESITAMOS_JUGADORES);
            entityManager.persist(match);

            return match;
        } else {
            return entityManager.merge(match);
        }
    }

    @Override
    public MatchContext findById(Integer id) {
        Match match = entityManager.find(Match.class, id);

        if (match == null) {
            return null;
        }

        MatchContext matchContext = new MatchContext();
        State state = StateFactory.create(match.getState());
        IMatchmakingStrategy matchmakingStrategy = matchmakingStrategyFactory.create(match.getMatchmakingStrategy());

        state.setMatchContext(matchContext);
        matchContext.setMatch(match);
        matchContext.setState(state);
        matchContext.setMatchmakingStrategy(matchmakingStrategy);

        return matchContext;
    }

    @Override
    public List<MatchContext> findAllForBotService() {
        List<StateEnum> states = List.of(StateEnum.CONFIRMADO, StateEnum.EN_JUEGO);

        List<Match> matches = entityManager.createQuery(
                "SELECT m FROM Match m WHERE m.state IN :states", Match.class)
                .setParameter("states", states)
                .getResultList();

        List<MatchContext> matchContexts = new ArrayList<>();

        for (Match match : matches) {
            MatchContext context = new MatchContext();
            State state = StateFactory.create(match.getState());

            context.setMatch(match);
            context.setState(state);
            state.setMatchContext(context);

            matchContexts.add(context);
        }

        return matchContexts;
    }
}
