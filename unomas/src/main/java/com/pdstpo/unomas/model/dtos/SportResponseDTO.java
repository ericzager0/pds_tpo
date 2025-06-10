package com.pdstpo.unomas.model.dtos;

import com.pdstpo.unomas.model.entities.Sport;

public class SportResponseDTO {
    private Integer id;
    private String name;
    private int requiredPlayers;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRequiredPlayers() {
        return requiredPlayers;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRequiredPlayers(int requiredPlayers) {
        this.requiredPlayers = requiredPlayers;
    }

    public static SportResponseDTO toDTO(Sport sport) {
        SportResponseDTO sportResponseDTO = new SportResponseDTO();

        sportResponseDTO.setId(sport.getId());
        sportResponseDTO.setName(sport.getName());
        sportResponseDTO.setRequiredPlayers(sport.getRequiredPlayers());

        return sportResponseDTO;
    }
}
