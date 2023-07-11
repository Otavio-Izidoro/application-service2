package com.estudo.applicationservice.rest.vo;

public class OpenaiResponse {

    private String id;
    private String object;
    private Integer created;
    private String model;
    private Choice[] choices;
    private Usage usage;

    public String getId() {
        return id;
    }

    public String getObject() {
        return object;
    }

    public Integer getCreated() {
        return created;
    }

    public String getModel() {
        return model;
    }

    public Choice[] getChoices() {
        return choices;
    }

    public Usage getUsage() {
        return usage;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setChoices(Choice[] choices) {
        this.choices = choices;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }

}
