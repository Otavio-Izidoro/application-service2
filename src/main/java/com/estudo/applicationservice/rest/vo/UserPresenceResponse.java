package com.estudo.applicationservice.rest.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserPresenceResponse {

    @JsonProperty
    private String accountId;
    @JsonProperty
    private boolean presence;
    @JsonProperty ("date")
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
