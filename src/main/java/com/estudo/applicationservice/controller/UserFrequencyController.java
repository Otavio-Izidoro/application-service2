package com.estudo.applicationservice.controller;

import com.estudo.applicationservice.constants.PresenceLogs;
import com.estudo.applicationservice.helpers.enums.DayOfWeek;
import com.estudo.applicationservice.rest.vo.UserFrequencyResponse;
import com.estudo.applicationservice.service.UserFrequencyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/frequency")
public class UserFrequencyController{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserFrequencyController.class);

    @Autowired
    private UserFrequencyService userFrequencyService;


    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "/{accountId}/{day}")
    public ResponseEntity<UserFrequencyResponse> getFrequencyByDay(
            @PathVariable final String accountId,
            @PathVariable final DayOfWeek day) {

        final var response = userFrequencyService.getFrequencyByAccountId(accountId,day);

        if(Objects.isNull(response)) {
            LOGGER.info(PresenceLogs.PRESENCE_NOT_FOUND, accountId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        LOGGER.info("[GET] Sucess to get frequency from accountId {} ", accountId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
