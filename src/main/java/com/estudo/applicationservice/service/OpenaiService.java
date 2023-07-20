package com.estudo.applicationservice.service;

import com.estudo.applicationservice.configuration.OpenaiRestClient;
import com.estudo.applicationservice.rest.vo.Choice;
import com.estudo.applicationservice.rest.vo.OpenaiRequest;
import com.estudo.applicationservice.rest.vo.QuizResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class OpenaiService {

    @Autowired
    private final OpenaiRestClient openaiClient;

    @Value("${openai.api.url}")
    private String baseUrl;
    @Value("${openai.api.key}")
    private String apiKey;
    @Value("${openai.api.model}")
    private String model;

    public OpenaiService(final OpenaiRestClient openaiClient) {
        this.openaiClient = openaiClient;
    }

    public QuizResponse chatQuiz(final OpenaiRequest request) {
        request.setModel(this.model);
        final var choices = Arrays.stream(openaiClient.chat(baseUrl, apiKey, request).getChoices()).toList().get(0);

        return getQuiz(choices);
    }

    private QuizResponse getQuiz(final Choice choice){
        final String content = choice.getMessage().getContent();
        final List<String> splitContent = Arrays.stream(content.split("\n\n")).toList();
        return buildQuizResponse(splitContent);
    }

    private QuizResponse buildQuizResponse (final List<String> content){
        final QuizResponse response = new QuizResponse();
        final var LAST_ELEMENT_POSITION = content.size()-1;

        final var answers = Arrays.stream(content.get(LAST_ELEMENT_POSITION).split(" ,")).toList();

        response.setAnswers(answers);
        response.setQuestions(content.subList(0,LAST_ELEMENT_POSITION));
        return response;
    }

}
