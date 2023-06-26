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

        //TODO verificar como é o funcionamento quando nao passamos nenhum day, no caso é pra retornar todos
        //final var response = userFrequencyService.findById(accountId, day);
//
//        if(Objects.isNull(response)) {
//            LOGGER.info(PresenceLogs.PRESENCA_EXISTS, request);
//            final CustomResponse errorResponse = new CustomResponse(PresenceLogs.PRESENCA_EXISTS);
//            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
//        }
//
//        final CustomResponse sucessResponse = new CustomResponse(PresenceLogs.SUCESSFULLY_UPDATED);
//        LOGGER.info(PresenceLogs.SUCESSFULLY_UPDATED, request);
//        return new ResponseEntity<>(sucessResponse, HttpStatus.OK);

        return null;
    }

}
