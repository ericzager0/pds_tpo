package com.pdstpo.unomas.model;

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

        match.getPlayers().add(user);

        if (match.getPlayers().size() == requiredPlayers) {
            getMatchContext().setState(new PartidoArmado());
            match.setState(StateEnum.PARTIDO_ARMADO);
        }
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
            getMatchContext().setState(new Cancelado());
            getMatchContext().getMatch().setState(StateEnum.CANCELADO);
        } else {
            throw new IllegalArgumentException("El jugador con id " + requestingUser.getId() + " no es el creador del partido y por lo tanto no puede cancelarlo.");
        }
    }
}
