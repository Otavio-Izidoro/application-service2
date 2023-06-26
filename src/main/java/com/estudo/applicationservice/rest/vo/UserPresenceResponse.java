package com.estudo.applicationservice.rest.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserPresenceResponse {

    @JsonProperty
    private String accountId;
    @JsonProperty
    private boolean presence;
    @JsonProperty
    private String date;

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

}
