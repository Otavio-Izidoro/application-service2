package com.estudo.applicationservice.rest.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UserTopicResponse {

    @JsonProperty
    private List<String> topics;

    public UserTopicResponse(final List<String> topics) {
        this.topics = topics;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }
}
