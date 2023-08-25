package com.estudo.applicationservice.controller;
import com.estudo.applicationservice.constants.PresenceLogs;
import com.estudo.applicationservice.helpers.CustomResponse;
import com.estudo.applicationservice.rest.vo.ClassContentRequest;
import com.estudo.applicationservice.rest.vo.UserPresenceRequest;
import com.estudo.applicationservice.rest.vo.UserPresenceResponse;
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


    @RequestMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            path = "/update")
    public ResponseEntity<CustomResponse> updatePresence(final UserPresenceRequest request) {
        final var response = userPresenceService.updatePresence(request);

        if(Objects.isNull(response)) {
            LOGGER.info(PresenceLogs.UPDATE_ERROR_PRESENCE, request);
            final CustomResponse errorResponse = new CustomResponse(PresenceLogs.UPDATE_ERROR_PRESENCE);
            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        }

        final CustomResponse sucessResponse = new CustomResponse(PresenceLogs.SUCESSFULLY_UPDATED);
        LOGGER.info(PresenceLogs.SUCESSFULLY_UPDATED, request);
        return new ResponseEntity<>(sucessResponse, HttpStatus.OK);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "/class-content")
    public ResponseEntity<UserPresenceResponse> updateContentOfClass(@RequestBody final ClassContentRequest request){
        final var response = userPresenceService.updateContent(request);

        if(Objects.isNull(response)) {
            LOGGER.info(PresenceLogs.NON_EXISTENT_PRESENCE, request);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        LOGGER.info(PresenceLogs.UPDATED_CONTENT, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(
            path = "/testeGET")
    public String getTesteGet(){
        return "testeGET";
    }
    @RequestMapping(
            path = "/teste")
    public String getTeste(){
        return "testePOST sem body";
    }

    @PostMapping(
            path = "/teste2")
    public String getTeste2(@RequestBody final String string){
        return string;
    }
}
