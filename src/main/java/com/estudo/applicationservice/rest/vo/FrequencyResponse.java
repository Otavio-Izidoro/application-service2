package com.estudo.applicationservice.rest.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FrequencyResponse {

    @JsonProperty
    private String accountId;
    @JsonProperty
    private String content;
    @JsonProperty
    private Double frequencyPercentage;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Double getFrequencyPercentage() {
        return frequencyPercentage;
    }

    public void setFrequencyPercentage(Double frequencyPercentage) {
        this.frequencyPercentage = frequencyPercentage;
    }
}
