package com.estudo.applicationservice.rest.vo;

public class Usage {

    private Integer completionTokens;
    private Integer promptTokens;
    private Integer totalTokens;

    public Integer getCompletionTokens() {
        return completionTokens;
    }

    public Integer getPromptTokens() {
        return promptTokens;
    }

    public Integer getTotalTokens() {
        return totalTokens;
    }

    public void setCompletionTokens(Integer completionTokens) {
        this.completionTokens = completionTokens;
    }

    public void setPromptTokens(Integer promptTokens) {
        this.promptTokens = promptTokens;
    }

    public void setTotalTokens(Integer totalTokens) {
        this.totalTokens = totalTokens;
    }

}
