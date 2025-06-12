package com.pdstpo.unomas.model;

import com.pdstpo.unomas.model.entities.Comment;
import com.pdstpo.unomas.model.entities.Match;
import com.pdstpo.unomas.model.entities.User;
import com.pdstpo.unomas.model.enums.StateEnum;

import java.time.LocalDateTime;
import java.util.List;

public class Confirmado extends State {

    @Override
    public void addPlayer(User user) {
        throw new IllegalStateException("No es posible agregar un jugador a un partido que está confirmado.");
    }

    @Override
    public void removePlayer(User user) {
        User creator = getMatchContext().getMatch().getCreator();
        List<User> players = getMatchContext().getMatch().getPlayers();

        if (user.getId().equals(creator.getId())) {
            throw new IllegalArgumentException("El jugador con id " + user.getId() + " es el creador del partido, no puede ser eliminado.");
        }

        boolean removed = players.removeIf(u -> u.getId().equals(user.getId()));

        if (removed) {
            getMatchContext().setState(new NecesitamosJugadores());
            getMatchContext().getMatch().setState(StateEnum.NECESITAMOS_JUGADORES);
        } else {
            throw new IllegalArgumentException("El jugador con id " + user.getId() + " no forma parte del partido.");
        }
    }

    @Override
    public void confirm(User requestingUser) {
        throw new IllegalStateException("No es posible confirmar un partido ya confirmado.");
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

    @Override
    public void addComment(Comment comment) {
        throw new IllegalStateException("No es posible comentar un partido aún no finalizado.");
    }

    @Override
    public void init() {
        Match match = getMatchContext().getMatch();
        LocalDateTime matchStartTime = match.getStartTime();
        LocalDateTime now = LocalDateTime.now();

        if (now.isBefore(matchStartTime)) {
        } else {
            getMatchContext().setState(new EnJuego());
            getMatchContext().getMatch().setState(StateEnum.EN_JUEGO);
            System.out.println("Cambio de estado: EN_JUEGO");
        }
    }

    @Override
    public void end() {
        throw new IllegalStateException("No es posible finalizar un partido que aún no inició");
    }
}
