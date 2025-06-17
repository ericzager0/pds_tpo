package com.pdstpo.unomas.model.entities;

import com.pdstpo.unomas.model.enums.LevelEnum;
import com.pdstpo.unomas.model.enums.LevelEnumConverter;
import jakarta.persistence.*;

@Entity
@Table(name = "user_sports")
public class UserSport {

    @EmbeddedId
    private UserSportId id = new UserSportId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("sportId")
    private Sport sport;

    @Convert(converter = LevelEnumConverter.class)
    @Column(name = "level")
    private LevelEnum level;

    private boolean favourite;

    public UserSportId getId() {
        return id;
    }

    public void setId(UserSportId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public void setLevel(LevelEnum level) {
        this.level = level;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }
}
