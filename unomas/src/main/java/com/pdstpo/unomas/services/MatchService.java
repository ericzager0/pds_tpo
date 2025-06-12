package com.pdstpo.unomas.services;

import com.pdstpo.unomas.model.EmailNotification;
import com.pdstpo.unomas.model.INotificationStrategy;
import com.pdstpo.unomas.model.MatchContext;
import com.pdstpo.unomas.model.Notification;
import com.pdstpo.unomas.model.entities.Comment;
import com.pdstpo.unomas.model.entities.Match;
import com.pdstpo.unomas.model.entities.Sport;
import com.pdstpo.unomas.model.entities.User;
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

    @Override
    public Match create(Match match) {
        User user = userRepository.findById(match.getCreator().getId())
                .orElseThrow(() -> new EntityNotFoundException("No se encontró un usuario con id: " + match.getCreator().getId()));

        Sport sport = sportRepository.findById(match.getSport().getId()).orElseThrow(() -> new EntityNotFoundException("No se encotró un deporte con id: " + match.getSport().getId()));

        List<User> players = new ArrayList<>();
        players.add(user);

        match.setPlayers(players);
        match.setCreator(user);
        match.setSport(sport);

        List<User> interestedIn = userRepository.findUsersIsFavoriteSport(sport.getId());

        Match createdMatch = matchRepository.save(match);

        // Por ahora
        INotificationStrategy notificator = new EmailNotification();

        for (User u : interestedIn) {
            if (!u.getId().equals(user.getId())) {
                notificator.send(new Notification(u, match, "Fuiste invitado al partido con id " + createdMatch.getId()));
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
}
