package com.estudo.applicationservice.controller;

import com.estudo.applicationservice.rest.vo.OpenaiRequest;
import com.estudo.applicationservice.rest.vo.QuizResponse;
import com.estudo.applicationservice.service.OpenaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/openai")
public class ChatController {

    @Autowired
    private final OpenaiService openaiService;

    public ChatController(OpenaiService openaiService) {
        this.openaiService = openaiService;
    }

    @PostMapping(path = "/quiz", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QuizResponse> chat(@RequestBody final OpenaiRequest request) {
        return new ResponseEntity<>(openaiService.chat(request), HttpStatus.OK);
    }
}


