package com.estudo.applicationservice.rest.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public class OpenaiRequest {

    @JsonProperty
    @NotBlank
    private Message[] messages;

    @JsonProperty
    private String model;

    public Message[] getMessages() {
        return messages;
    }

    public void setMessages(final Message[] messages) {
        this.messages = messages;
    }

    public String getModel() {
        return model;
    }

    public void setModel(final String model) {
        this.model = model;
    }
}
