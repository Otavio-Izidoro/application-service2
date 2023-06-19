package com.estudo.applicationservice.controller;
import com.estudo.applicationservice.constants.PresenceLogs;
import com.estudo.applicationservice.helpers.CustomResponse;
import com.estudo.applicationservice.rest.vo.UserPresenceRequest;
import com.estudo.applicationservice.service.UserPresenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/presence")
public class UserPresenceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserPresenceController.class);

    @Autowired
    private UserPresenceService userPresenceService;


    @PostMapping(path = "/update")
    public ResponseEntity<CustomResponse> updatePresence(@RequestBody final UserPresenceRequest request) {
        final var response = userPresenceService.updatePresence(request);

        if(Objects.isNull(response)) {
            LOGGER.info(PresenceLogs.PRESENCA_EXISTS, request);
            final CustomResponse errorResponse = new CustomResponse(PresenceLogs.PRESENCA_EXISTS);
            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        }

        final CustomResponse sucessResponse = new CustomResponse(PresenceLogs.SUCESSFULLY_UPDATED);
        LOGGER.info(PresenceLogs.SUCESSFULLY_UPDATED, request);
        return new ResponseEntity<>(sucessResponse, HttpStatus.CREATED);
    }
}
