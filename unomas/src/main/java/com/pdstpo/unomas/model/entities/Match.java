package com.pdstpo.unomas.model.entities;

import com.pdstpo.unomas.model.enums.*;
import jakarta.persistence.*;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User creator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sport_id", nullable = false)
    private Sport sport;

    private int duration;

    @Column(nullable = false, columnDefinition = "geography(Point,4326)")
    private Point location;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Convert(converter = StateConverter.class)
    @Column(name = "state", nullable = false)
    private StateEnum state;

    @Convert(converter = MatchmakingStrategyConverter.class)
    @Column(name = "matchmaking", nullable = false)
    private MatchmakingStrategyEnum matchmakingStrategy;

    @Convert(converter = LevelEnumConverter.class)
    @Column(name = "max_level")
    private LevelEnum maxLevel;

    @Convert(converter = LevelEnumConverter.class)
    @Column(name = "min_level")
    private LevelEnum minLevel;

    @Column(name = "min_radius")
    private Integer minRadius;

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

    @Column(name = "min_matches")
    private Integer minMatches;

    @ManyToMany
    @JoinTable(
            name = "match_users",
            joinColumns = @JoinColumn(name = "match_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> players;

    @OneToMany(mappedBy="match", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Comment> comments = new ArrayList<>();

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public StateEnum getState() {
        return state;
    }

    public void setState(StateEnum state) {
        this.state = state;
    }

    public MatchmakingStrategyEnum getMatchmakingStrategy() {
        return matchmakingStrategy;
    }

    public void setMatchmakingStrategy(MatchmakingStrategyEnum matchmakingStrategy) {
        this.matchmakingStrategy = matchmakingStrategy;
    }

    public List<User> getPlayers() {
        return players;
    }

    public void setPlayers(List<User> players) {
        this.players = players;
    }
}
