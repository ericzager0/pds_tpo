package com.pdstpo.unomas.services;

import com.pdstpo.unomas.model.*;
import com.pdstpo.unomas.model.entities.Comment;
import com.pdstpo.unomas.model.entities.Match;
import com.pdstpo.unomas.model.entities.Sport;
import com.pdstpo.unomas.model.entities.User;
import com.pdstpo.unomas.model.enums.LevelEnum;
import com.pdstpo.unomas.model.enums.MatchmakingStrategyEnum;
import com.pdstpo.unomas.model.enums.StateEnum;
import com.pdstpo.unomas.repositories.IMatchRepository;
import com.pdstpo.unomas.repositories.ISportRepository;
import com.pdstpo.unomas.repositories.IUserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatchService implements IMatchService {

    @Autowired
    private IMatchRepository matchRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ISportRepository sportRepository;

    @Autowired
    private MatchmakingStrategyFactory matchmakingStrategyFactory;

    @Override
    public Match create(Match match) {
        User user = userRepository.findById(match.getCreator().getId())
                .orElseThrow(() -> new EntityNotFoundException("No se encontró un usuario con id: " + match.getCreator().getId()));

        Sport sport = sportRepository.findById(match.getSport().getId()).orElseThrow(() -> new EntityNotFoundException("No se encotró un deporte con id: " + match.getSport().getId()));

        if (match.getMatchmakingStrategy().equals(MatchmakingStrategyEnum.PROXIMITY) && match.getMinRadius() == null) {
            throw new IllegalArgumentException("Falta el radio mínimo.");
        }

        if (match.getMatchmakingStrategy().equals(MatchmakingStrategyEnum.HISTORY) && match.getMinMatches() == null) {
            throw new IllegalArgumentException("Falta la cantidad mínima de partidos jugados.");
        }

        if (match.getMatchmakingStrategy().equals(MatchmakingStrategyEnum.LEVEL)) {
            LevelEnum minLevel = match.getMinLevel();
            LevelEnum maxLevel = match.getMaxLevel();

            if (minLevel != null && maxLevel != null) {
                if (minLevel.ordinal() > maxLevel.ordinal()) {
                    throw new IllegalArgumentException("El nivel mínimo no puede ser mayor que el nivel máximo.");
                }
            } else if (minLevel == null && maxLevel == null){
                throw new IllegalArgumentException("Es necesario indicar un nivel de referencia.");
            }
        }

        List<User> players = new ArrayList<>();
        players.add(user);

        match.setPlayers(players);
        match.setCreator(user);
        match.setSport(sport);

        List<User> interestedIn = userRepository.findUsersIsFavoriteSport(sport.getId());

        Match createdMatch = matchRepository.save(match);

        Notifier notifier = new Notifier();

        List<User> candidates = matchmakingStrategyFactory.create(match.getMatchmakingStrategy()).search(match);

        for (User u : interestedIn) {
            if (!u.getId().equals(user.getId())) {
                boolean qualify = candidates.stream().anyMatch(candidate -> candidate.getId().equals(u.getId()));

                if (qualify) {
                    notifier.notify(new Notification(u, "Fuiste invitado al partido con id " + createdMatch.getId()));
                }
            }
        }

        return createdMatch;
    }

    @Override
    public Match getById(Integer id) {
        return matchRepository.findById(id).getMatch();
    }

    @Override
    public void addPlayer(Integer matchId, Integer userId) {
        MatchContext matchContext = matchRepository.findById(matchId);

        if (matchContext == null) {
            throw new EntityNotFoundException("No se encontró un partido con id: " + matchId);
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró un usuario con id: " + userId));

        matchContext.getState().addPlayer(user);
        matchRepository.save(matchContext.getMatch());
    }

    @Override
    public void removePlayer(Integer matchId, Integer userId) {
        MatchContext matchContext = matchRepository.findById(matchId);

        if (matchContext == null) {
            throw new EntityNotFoundException("No se encontró un partido con id: " + matchId);
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró un usuario con id: " + userId));

        matchContext.getState().removePlayer(user);
        matchRepository.save(matchContext.getMatch());
    }

    @Override
    public void addComment(Integer matchId, String content, Integer userId) {
        MatchContext matchContext = matchRepository.findById(matchId);

        if (matchContext == null) {
            throw new EntityNotFoundException("No se encontró un partido con id: " + matchId);
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró un usuario con id: " + userId));

        Comment comment = new Comment();

        comment.setUser(user);
        comment.setMatch(matchContext.getMatch());
        comment.setComment(content);

        matchContext.getState().addComment(comment);

        matchRepository.save(matchContext.getMatch());
    }

    @Override
    public List<Comment> getAllComments(Integer matchId) {
        MatchContext matchContext = matchRepository.findById(matchId);

        if (matchContext == null) {
            throw new EntityNotFoundException("No se encontró un partido con id: " + matchId);
        }

        return matchContext.getMatch().getComments();
    }

    @Override
    public void updateMatch(Integer matchId, Integer userId, StateEnum state) {
        MatchContext matchContext = matchRepository.findById(matchId);

        if (matchContext == null) {
            throw new EntityNotFoundException("No se encontró un partido con id: " + matchId);
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró un usuario con id: " + userId));

        if (state.equals(StateEnum.CANCELADO)) {
            matchContext.getState().cancel(user);
        } else if (state.equals(StateEnum.CONFIRMADO)) {
            matchContext.getState().confirm(user);
        }

        matchRepository.save(matchContext.getMatch());
    }

    @Override
    public List<Match> search(Integer sportId, Integer userId) {
        List<MatchContext> matches = matchRepository.search(sportId);
        List<Match> filteredMatches = new ArrayList<>();

        for (MatchContext match : matches) {
            List<User> candidates = match.getMatchmakingStrategy().search(match.getMatch());

            for (User candidate : candidates) {
                if (candidate.getId().equals(userId)) {
                    filteredMatches.add(match.getMatch());
                    break;
                }
            }
        }

        return filteredMatches;
    }
}
