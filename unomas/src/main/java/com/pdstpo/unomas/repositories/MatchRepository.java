package com.pdstpo.unomas.repositories;

import com.pdstpo.unomas.model.MatchContext;
import com.pdstpo.unomas.model.State;
import com.pdstpo.unomas.model.StateFactory;
import com.pdstpo.unomas.model.entities.Match;
import com.pdstpo.unomas.model.enums.StateEnum;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class MatchRepository implements IMatchRepository {

    @PersistenceContext
    private EntityManager entityManager;

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

        state.setMatchContext(matchContext);
        matchContext.setMatch(match);
        matchContext.setState(state);

        return matchContext;
    }

}
