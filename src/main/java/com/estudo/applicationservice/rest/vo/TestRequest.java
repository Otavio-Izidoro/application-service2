package com.estudo.applicationservice.rest.vo;

import com.estudo.applicationservice.helpers.enums.DayOfWeek;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public class TestRequest {

    @JsonProperty
    @NotBlank
    private String accountId;

    @JsonProperty
    @NotBlank
    private boolean presence;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public boolean isPresence() {
        return presence;
    }

    public void setPresence(boolean presence) {
        this.presence = presence;
    }
}
