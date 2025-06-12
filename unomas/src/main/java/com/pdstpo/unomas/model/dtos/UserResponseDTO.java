package com.pdstpo.unomas.model.dtos;

import com.pdstpo.unomas.model.entities.User;
import com.pdstpo.unomas.model.entities.UserSport;
import org.locationtech.jts.geom.Point;

import java.util.ArrayList;
import java.util.List;

public class UserResponseDTO {
    private Integer id;
    private String username;
    private String email;
    private double lat;
    private double lng;
    private List<UserSportDTO> sports;

    public List<UserSportDTO> getSports() {
        return sports;
    }

    public void setSports(List<UserSportDTO> sports) {
        this.sports = sports;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public static UserResponseDTO toDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();

        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());

        Point point = user.getLocation();

        if (point != null) {
            dto.setLat(point.getY());
            dto.setLng(point.getX());
        }

        List<UserSportDTO> userSportDTOS = new ArrayList<>();

        for (UserSport userSport : user.getUserSports()) {
            userSportDTOS.add(UserSportDTO.toDTO(userSport));
        }

        dto.setSports(userSportDTOS);

        return dto;
    }
}
