package com.estudo.applicationservice.service;

import com.estudo.applicationservice.configuration.OpenaiRestClient;
import com.estudo.applicationservice.constants.GPTConstants;
import com.estudo.applicationservice.rest.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class OpenaiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpenaiService.class);
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

    public QuizResponse chatQuiz(final QuizRequest quizRequest) {
        final OpenaiRequest request = new OpenaiRequest();
        request.setMessages(buildOpenaiMessages(quizRequest));
        request.setModel(this.model);

        LOGGER.info("Enviando request para API do GPT");
        final var choices = Arrays.stream(openaiClient.chat(baseUrl, apiKey, request).getChoices()).toList().get(0);

        LOGGER.info("Montando Quiz");

        return getQuiz(choices);
    }

    private Message[] buildOpenaiMessages(final QuizRequest quizRequest){
        final var messages = new Message[1];
        messages[0] = new Message();
        messages[0].setRole(GPTConstants.GPT_ROLE);
        messages[0].setContent(GPTConstants.QUIZ_PROMPT_TEMPLATE.replace("{}", quizRequest.getTopic()));

        return messages;
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
