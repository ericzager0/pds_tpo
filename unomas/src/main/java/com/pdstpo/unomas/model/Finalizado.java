package com.pdstpo.unomas.model;

import com.pdstpo.unomas.model.entities.Comment;
import com.pdstpo.unomas.model.entities.Match;
import com.pdstpo.unomas.model.entities.User;

import java.util.List;

public class Finalizado extends State {

    @Override
    public void addPlayer(User user) {
        throw new IllegalStateException("No es posible agregar un jugador a un partido que finalizó.");
    }

    @Override
    public void removePlayer(User user) {
        throw new IllegalStateException("No es posible eliminar un jugador de un partido que finalizó.");
    }

    @Override
    public void confirm(User requestingUser) {
        throw new IllegalStateException("No es posible confirmar un partido que finalizó.");
    }

    @Override
    public void cancel(User requestingUser) {
        throw new IllegalStateException("No es posible cancelar un partido que finalizó.");
    }

    @Override
    public void addComment(Comment comment) {
        Match match = getMatchContext().getMatch();
        List<User> players = match.getPlayers();

        boolean exists = players.stream().anyMatch(player -> player.getId().equals(comment.getUser().getId()));

        if (exists) {
            match.getComments().add(comment);
        } else {
            throw new IllegalArgumentException("El jugador con id " + comment.getUser().getId() + " no participó del partido y por lo tanto no puede comentar.");
        }
    }

    @Override
    public void init() {
        throw new IllegalStateException("No es posible iniciar un partido que ya finalizó.");
    }

    @Override
    public void end() {
        throw new IllegalStateException("El partido ya está finalizado.");
    }
}
