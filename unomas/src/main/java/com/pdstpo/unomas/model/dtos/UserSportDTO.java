package com.pdstpo.unomas.model.dtos;

import com.pdstpo.unomas.model.entities.UserSport;
import com.pdstpo.unomas.model.enums.LevelEnum;

public class UserSportDTO {
    private Integer sportId;
    private String sportName;
    private LevelEnum level;
    private boolean favorite;

    public Integer getSportId() {
        return sportId;
    }

    public void setSportId(Integer sportId) {
        this.sportId = sportId;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public void setLevel(LevelEnum level) {
        this.level = level;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public static UserSportDTO toDTO(UserSport userSport) {
        UserSportDTO dto = new UserSportDTO();

        dto.setSportId(userSport.getId().getSportId());
        dto.setSportName(userSport.getSport().getName());
        dto.setFavorite(userSport.isFavourite());
        dto.setLevel(userSport.getLevel());

        return dto;
    }
}
