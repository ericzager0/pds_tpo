package com.pdstpo.unomas.model.dtos;

import com.pdstpo.unomas.model.enums.LevelEnum;

public class AddUserSportDTO {
    private Integer userId;
    private Integer sportId;
    private boolean favorite;
    private LevelEnum level;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSportId() {
        return sportId;
    }

    public void setSportId(Integer sportId) {
        this.sportId = sportId;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public void setLevel(LevelEnum level) {
        this.level = level;
    }
}
