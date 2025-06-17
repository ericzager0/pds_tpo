package com.pdstpo.unomas.model.dtos;

import com.pdstpo.unomas.model.enums.StateEnum;

public class MatchUpdateDTO {
    private StateEnum state;
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public StateEnum getState() {
        return state;
    }

    public void setState(StateEnum state) {
        this.state = state;
    }
}
