package com.pdstpo.unomas.model.dtos;

import com.pdstpo.unomas.model.entities.Match;
import com.pdstpo.unomas.model.entities.Sport;
import com.pdstpo.unomas.model.entities.User;
import com.pdstpo.unomas.model.enums.LevelEnum;
import com.pdstpo.unomas.model.enums.MatchmakingStrategyEnum;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

import java.time.LocalDateTime;

public class MatchCreateDTO {
    private Integer creatorId;
    private Integer sportId;
    private int duration;
    private Double lat;
    private Double lng;
    private LocalDateTime startTime;
    private MatchmakingStrategyEnum matchmaking;
    private LevelEnum maxLevel;
    private LevelEnum minLevel;

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Integer getSportId() {
        return sportId;
    }

    public LevelEnum getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(LevelEnum maxLevel) {
        this.maxLevel = maxLevel;
    }

    public LevelEnum getMinLevel() {
        return minLevel;
    }

    public void setMinLevel(LevelEnum minLevel) {
        this.minLevel = minLevel;
    }

    public void setSportId(Integer sportId) {
        this.sportId = sportId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public MatchmakingStrategyEnum getMatchmaking() {
        return matchmaking;
    }

    public void setMatchmaking(MatchmakingStrategyEnum matchmaking) {
        this.matchmaking = matchmaking;
    }

    public Match toEntity() {
        Match match = new Match();
        User creator = new User();
        Sport sport = new Sport();

        creator.setId(creatorId);
        sport.setId(sportId);

        match.setCreator(creator);
        match.setSport(sport);
        match.setDuration(duration);

        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
        Point point = geometryFactory.createPoint(new Coordinate(lng, lat));

        match.setLocation(point);
        match.setStartTime(startTime);
        match.setMatchmakingStrategy(matchmaking);
        match.setMaxLevel(maxLevel);
        match.setMinLevel(minLevel);

        return match;
    }
}
