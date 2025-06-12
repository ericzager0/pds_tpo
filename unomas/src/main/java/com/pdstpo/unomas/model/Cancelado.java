package com.pdstpo.unomas.model;

import com.pdstpo.unomas.model.entities.Comment;
import com.pdstpo.unomas.model.entities.User;

public class Cancelado extends State {

    @Override
    public void addPlayer(User user) {
        throw new IllegalStateException("No es posible agregar un jugador a un partido que está cancelado.");
    }

    @Override
    public void removePlayer(User user) {
        throw new IllegalStateException("No es posible eliminar un jugador de un partido que está cancelado.");
    }

    @Override
    public void confirm(User requestingUser) {
        throw new IllegalStateException("No es posible confirmar un partido que está cancelado.");
    }

    @Override
    public void cancel(User requestingUser) {
        throw new IllegalStateException("No es posible cancelar un partido que está cancelado.");
    }

    @Override
    public void addComment(Comment comment) {
        throw new IllegalStateException("No es posible comentar un partido aún no finalizado.");
    }

    @Override
    public void init() {
        throw new IllegalStateException("No es iniciar un partido cancelado.");
    }

    @Override
    public void end() {
        throw new IllegalStateException("No es finalizar un partido cancelado.");
    }
}
