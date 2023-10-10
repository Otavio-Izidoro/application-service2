package com.estudo.applicationservice.rest.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuizRequest {

    @JsonProperty
    private String topic;

    public String getTopic() { return topic; }

    public void setTopic(final String topic) { this.topic = topic; }
}
