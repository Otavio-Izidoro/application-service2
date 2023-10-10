package com.estudo.applicationservice.controller;
import com.estudo.applicationservice.constants.PresenceLogs;
import com.estudo.applicationservice.helpers.enums.DayOfWeek;
import com.estudo.applicationservice.rest.vo.*;
import com.estudo.applicationservice.service.UserPresenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/presence")
public class UserPresenceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserPresenceController.class);

    @Autowired
    private UserPresenceService userPresenceService;


    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            path = "/update")
    public ResponseEntity<CustomResponse> updatePresence(@RequestBody final UserPresenceRequest request) {
        final var response = userPresenceService.updatePresence(request);

        if(Objects.isNull(response)) {
            LOGGER.info(PresenceLogs.UPDATE_ERROR_PRESENCE, request.getDate());
            final CustomResponse errorResponse = new CustomResponse(PresenceLogs.UPDATE_ERROR_PRESENCE.replace("{}", request.getDate()));
            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        }

        final CustomResponse sucessResponse = new CustomResponse(PresenceLogs.SUCESSFULLY_UPDATED.replace("{}", request.getDate()));
        LOGGER.info(PresenceLogs.SUCESSFULLY_UPDATED, request.getDate());
        return new ResponseEntity<>(sucessResponse, HttpStatus.OK);
    }



    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "/class-content")
    public ResponseEntity<UserPresenceResponse> updateContentOfClass(@RequestBody final ClassContentRequest request){
        final var response = userPresenceService.updateContent(request);

        if(Objects.isNull(response)) {
            LOGGER.info(PresenceLogs.NON_EXISTENT_PRESENCE, request.getDate());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        LOGGER.info(PresenceLogs.UPDATED_CONTENT, request.getDate());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "/topic/{accountId}/{day}")
    public ResponseEntity<UserTopicResponse> getFrequencyByDay(
            @PathVariable final String accountId,
            @PathVariable final DayOfWeek day) {

        final var response = userPresenceService.getTopic(accountId,day);

        if(Objects.isNull(response)) {
            LOGGER.info(PresenceLogs.TOPIC_NOT_FOUND);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
