package com.pdstpo.unomas.model.dtos;

import com.pdstpo.unomas.model.entities.Match;
import com.pdstpo.unomas.model.entities.User;
import com.pdstpo.unomas.model.enums.LevelEnum;
import com.pdstpo.unomas.model.enums.MatchmakingStrategyEnum;
import com.pdstpo.unomas.model.enums.StateEnum;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MatchResponseDTO {
    private Integer id;
    private Integer creatorId;
    private String creatorUsername;
    private Integer sportId;
    private String sportName;
    private int requiredPlayers;
    private int duration;
    private Double lat;
    private Double lng;
    private LocalDateTime startTime;
    private MatchmakingStrategyEnum matchmaking;
    private StateEnum state;
    private int currentPlayers;
    private LevelEnum maxLevel;
    private LevelEnum minLevel;
    private Integer minRadius;
    private Integer minMatches;
    private List<UserPlayerDTO> players;

    public List<UserPlayerDTO> getPlayers() {
        return players;
    }

    public void setPlayers(List<UserPlayerDTO> players) {
        this.players = players;
    }

    public Integer getMinRadius() {
        return minRadius;
    }

    public void setMinRadius(Integer minRadius) {
        this.minRadius = minRadius;
    }

    public Integer getMinMatches() {
        return minMatches;
    }

    public void setMinMatches(Integer minMatches) {
        this.minMatches = minMatches;
    }

    public Integer getId() {
        return id;
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

    public int getCurrentPlayers() {
        return currentPlayers;
    }

    public void setCurrentPlayers(int currentPlayers) {
        this.currentPlayers = currentPlayers;
    }

    public int getRequiredPlayers() {
        return requiredPlayers;
    }

    public void setRequiredPlayers(int requiredPlayers) {
        this.requiredPlayers = requiredPlayers;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorUsername() {
        return creatorUsername;
    }

    public void setCreatorUsername(String creatorUsername) {
        this.creatorUsername = creatorUsername;
    }

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

    public StateEnum getState() {
        return state;
    }

    public void setState(StateEnum state) {
        this.state = state;
    }

    public static MatchResponseDTO toDTO(Match match) {
        MatchResponseDTO dto = new MatchResponseDTO();
        List<UserPlayerDTO> playersDtos = new ArrayList<>();

        dto.setId(match.getId());
        dto.setCreatorId(match.getCreator().getId());
        dto.setCreatorUsername(match.getCreator().getUsername());
        dto.setSportId(match.getSport().getId());
        dto.setSportName(match.getSport().getName());
        dto.setRequiredPlayers(match.getSport().getRequiredPlayers());
        dto.setDuration(match.getDuration());

        Point point = match.getLocation();

        if (point != null) {
            dto.setLat(point.getY());
            dto.setLng(point.getX());
        }

        dto.setStartTime(match.getStartTime());
        dto.setMatchmaking(match.getMatchmakingStrategy());
        dto.setState(match.getState());
        dto.setCurrentPlayers(match.getPlayers() != null ? match.getPlayers().size() : 0);
        dto.setMaxLevel(match.getMaxLevel());
        dto.setMinLevel(match.getMinLevel());
        dto.setMinRadius(match.getMinRadius());
        dto.setMinMatches(match.getMinMatches());

        for (User user : match.getPlayers()) {
            playersDtos.add(new UserPlayerDTO(user.getUsername()));
        }

        dto.setPlayers(playersDtos);

        return dto;
    }
}
