package com.estudo.applicationservice.rest.vo;

import com.estudo.applicationservice.helpers.enums.DayOfWeek;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public class UserPresenceRequest {

    @JsonProperty
    @NotBlank
    private String accountId;
    @JsonProperty
    @NotBlank
    private boolean presence;
    @JsonProperty
    @NotBlank
    private String date;
    @JsonProperty("dayOfWeek")
    @NotBlank
    private DayOfWeek day;

    public boolean isPresence() {
        return presence;
    }

    public void setPresence(boolean presence) {
        this.presence = presence;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }
}
