package com.pdstpo.unomas.model;

import com.pdstpo.unomas.model.entities.Comment;
import com.pdstpo.unomas.model.entities.Match;
import com.pdstpo.unomas.model.entities.User;
import com.pdstpo.unomas.model.enums.StateEnum;

import java.time.LocalDateTime;

public class EnJuego extends State{

    @Override
    public void addPlayer(User user) {
        throw new IllegalStateException("No es posible agregar un jugador a un partido que ya comenzó.");
    }

    @Override
    public void removePlayer(User user) {
        throw new IllegalStateException("No es posible eliminaro un jugador de un partido que ya comenzó.");
    }

    @Override
    public void confirm(User requestingUser) {
        throw new IllegalStateException("No es posible confirmar un partido que ya comenzó.");
    }

    @Override
    public void cancel(User requestingUser) {
        throw new IllegalStateException("No es posible cancelar un partido que ya comenzó.");
    }

    @Override
    public void addComment(Comment comment) {
        throw new IllegalStateException("No es posible comentar un partido aún no finalizado.");
    }

    @Override
    public void init() {
        throw new IllegalStateException("El partido ya está en juego.");
    }

    @Override
    public void end() {
        Match match = getMatchContext().getMatch();
        int matchDurationMinutes = match.getDuration();
        LocalDateTime matchStartTime = match.getStartTime();
        LocalDateTime matchEndTime = matchStartTime.plusMinutes(matchDurationMinutes);
        LocalDateTime now = LocalDateTime.now();

        if (now.isBefore(matchEndTime)) {
        } else {
            getMatchContext().setState(new Finalizado());
            getMatchContext().getMatch().setState(StateEnum.FINALIZADO);
            System.out.println("Cambio de estado: FINALIZADO");

        }
    }
}
