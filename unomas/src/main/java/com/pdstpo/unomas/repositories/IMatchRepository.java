package com.pdstpo.unomas.repositories;

import com.pdstpo.unomas.model.MatchContext;
import com.pdstpo.unomas.model.entities.Match;

public interface IMatchRepository {
    Match save(Match match);
    MatchContext findById(Integer id);
}
