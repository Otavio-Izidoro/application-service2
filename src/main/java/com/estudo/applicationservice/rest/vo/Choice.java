package com.estudo.applicationservice.rest.vo;

public class Choice {

    private Integer index;
    private String finishReason;
    private Message message;

    public Integer getIndex() {
        return index;
    }

    public String getFinishReason() {
        return finishReason;
    }

    public Message getMessage() {
        return message;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public void setFinishReason(String finishReason) {
        this.finishReason = finishReason;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

}
