package com.pdstpo.unomas.model.dtos;

public class SearchMatchDTO {
    private Integer sportId;
    private Integer requestingUserId;

    public Integer getSportId() {
        return sportId;
    }

    public void setSportId(Integer sportId) {
        this.sportId = sportId;
    }

    public Integer getRequestingUserId() {
        return requestingUserId;
    }

    public void setRequestingUserId(Integer requestingUserId) {
        this.requestingUserId = requestingUserId;
    }
}
