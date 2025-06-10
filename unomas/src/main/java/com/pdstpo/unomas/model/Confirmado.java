package com.pdstpo.unomas.model;

import com.pdstpo.unomas.model.entities.User;

public class Confirmado extends State {

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
}
