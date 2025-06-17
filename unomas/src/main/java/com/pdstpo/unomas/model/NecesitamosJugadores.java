package com.pdstpo.unomas.model;

import com.pdstpo.unomas.model.entities.Comment;
import com.pdstpo.unomas.model.entities.Match;
import com.pdstpo.unomas.model.entities.User;
import com.pdstpo.unomas.model.enums.StateEnum;

import java.util.List;


public class NecesitamosJugadores extends State {

    @Override
    public void addPlayer(User user) {
        Match match = getMatchContext().getMatch();
        List<User> players = match.getPlayers();
        int requiredPlayers =  match.getSport().getRequiredPlayers();
        boolean isIn = players.stream().anyMatch(p -> p.getId().equals(user.getId()));

        if (isIn) {
            throw new IllegalStateException("El jugador ya forma parte del partido.");
        }

        List<User> candidates = getMatchContext().getMatchmakingStrategy().search(match);

        for (User candidate : candidates) {
            if (candidate.getId().equals(user.getId())) {
                match.getPlayers().add(user);

                if (match.getPlayers().size() == requiredPlayers) {
                    match.setState(StateEnum.PARTIDO_ARMADO);
                    getMatchContext().setState(new PartidoArmado());
                }

                return;
            }
        }

        throw new IllegalStateException("El jugador no califica para este partido.");
    }

    @Override
    public void removePlayer(User user) {
        User creator = getMatchContext().getMatch().getCreator();
        List<User> players = getMatchContext().getMatch().getPlayers();

        if (user.getId().equals(creator.getId())) {
            throw new IllegalArgumentException("El jugador con id " + user.getId() + " es el creador del partido, no puede ser eliminado.");
        }

        boolean removed = players.removeIf(u -> u.getId().equals(user.getId()));

        if (!removed) {
            throw new IllegalArgumentException("El jugador con id " + user.getId() + " no forma parte del partido.");
        }
    }

    @Override
    public void confirm(User requestingUser) {
        throw new IllegalStateException("No es posible confirmar un partido que aún no está armado.");
    }

    @Override
    public void cancel(User requestingUser) {
        User creator = getMatchContext().getMatch().getCreator();

        if (creator.getId().equals(requestingUser.getId())) {
            getMatchContext().getMatch().setState(StateEnum.CANCELADO);
            getMatchContext().setState(new Cancelado());
        } else {
            throw new IllegalArgumentException("El jugador con id " + requestingUser.getId() + " no es el creador del partido y por lo tanto no puede cancelarlo.");
        }
    }

    @Override
    public void addComment(Comment comment) {
        throw new IllegalStateException("No es posible comentar un partido aún no finalizado.");
    }

    @Override
    public void init() {
        throw new IllegalStateException("No es posible iniciar un partido que aún no está confirmado");
    }

    @Override
    public void end() {
        throw new IllegalStateException("No es posible finalizar un partido que aún no inició");
    }
}
