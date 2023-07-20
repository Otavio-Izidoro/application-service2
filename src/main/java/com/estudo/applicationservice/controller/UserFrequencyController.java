package com.estudo.applicationservice.controller;

import com.estudo.applicationservice.helpers.enums.DayOfWeek;
import com.estudo.applicationservice.rest.vo.FrequencyResponse;
import com.estudo.applicationservice.service.UserFrequencyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/frequency")
public class UserFrequencyController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserFrequencyController.class);

    @Autowired
    private UserFrequencyService userFrequencyService;


    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "/{accountId}")
    public ResponseEntity<List<FrequencyResponse>> getFrequencyByDay(
            final String accountId,
            final DayOfWeek day) {

        //Será estruturado a função que da um GET para pegar a frequência do usuário
        return null;
    }

}
