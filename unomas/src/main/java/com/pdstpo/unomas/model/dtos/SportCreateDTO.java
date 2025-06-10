package com.pdstpo.unomas.model.dtos;

import com.pdstpo.unomas.model.entities.Sport;

public class SportCreateDTO {
    private String name;
    private int requiredPlayers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRequiredPlayers() {
        return requiredPlayers;
    }

    public void setRequiredPlayers(int requiredPlayers) {
        this.requiredPlayers = requiredPlayers;
    }

    public Sport toEntity() {
        Sport sport = new Sport();

        sport.setName(name);
        sport.setRequiredPlayers(requiredPlayers);

        return sport;
    }
}
