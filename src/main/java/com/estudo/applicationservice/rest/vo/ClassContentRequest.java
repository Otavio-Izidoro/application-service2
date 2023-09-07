package com.estudo.applicationservice.rest.vo;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public class ClassContentRequest {

    @JsonProperty
    @NotBlank
    private String accountId;

    @JsonProperty
    @NotBlank
    private String date;

    @JsonProperty
    @NotBlank
    private String topic;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
