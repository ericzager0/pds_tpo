package com.pdstpo.unomas.model;

import com.pdstpo.unomas.model.enums.StateEnum;

public class StateFactory {
    public static State create(StateEnum stateEnum) {
        return switch (stateEnum) {
            case NECESITAMOS_JUGADORES -> new NecesitamosJugadores();
            case EN_JUEGO -> new EnJuego();
            case FINALIZADO -> new Finalizado();
            case CANCELADO -> new Cancelado();
            case PARTIDO_ARMADO -> new PartidoArmado();
            case CONFIRMADO -> new Confirmado();
        };
    }
}
