package com.estudo.applicationservice.controller;

import com.estudo.applicationservice.constants.PresenceLogs;
import com.estudo.applicationservice.helpers.CustomResponse;
import com.estudo.applicationservice.rest.vo.TestRequest;
import com.estudo.applicationservice.rest.vo.UserPresenceRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;


@RestController
public class TestController {

    @Value("${openai.api.key}")
    private String apiKey;
    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @PostMapping(
            path = "/novoTeste"
    )
    public ResponseEntity<String> testPost(@RequestBody TestRequest request) {

        LOGGER.info(request.toString());

        return ResponseEntity.ok("Test deu certo");
    }

    @GetMapping(
            path = "/novoTesteGet"
    )
    public ResponseEntity<String> testGet() {

        return ResponseEntity.ok(apiKey);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            path = "/update")
    public ResponseEntity<String> updatePresenceTEst(@RequestBody UserPresenceRequest request) {

        LOGGER.info(request.toString());

        return ResponseEntity.ok("Test deu certo");
    }
}
