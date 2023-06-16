package com.estudo.applicationservice.rest.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public class UserPresenceRequest {

    @JsonProperty
    @NotBlank
    private String accountId;
    @JsonProperty
    @NotBlank
    private boolean presence;
    @JsonProperty ("date")
    @NotBlank
    private String weekDay;

    public boolean isPresence() {
        return presence;
    }

    public void setPresence(boolean presence) {
        this.presence = presence;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
