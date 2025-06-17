package com.pdstpo.unomas.model.dtos;

public class UserPlayerDTO {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserPlayerDTO(String username) {
        this.username = username;
    }
}
