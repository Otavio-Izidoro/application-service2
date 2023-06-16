package com.estudo.applicationservice.controller;
import com.estudo.applicationservice.rest.vo.UserPresenceRequest;
import com.estudo.applicationservice.rest.vo.UserPresenceResponse;
import com.estudo.applicationservice.service.UserPresenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/presenca")
public class UserPresenceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserPresenceController.class);

    @Autowired
    private UserPresenceService userPresenceService;


//    @PostMapping
//    public ResponseEntity<String> registrarPresenca(@RequestBody UsuarioPresenca usuarioPresenca) {
//        final String mensagem = usuarioPresencaService.registrarPresenca(usuarioPresenca);
//        return ResponseEntity.status(HttpStatus.OK).body(mensagem);
//    }

    @PostMapping(path = "/atualizar")
    public ResponseEntity<UserPresenceResponse> atualizarPresenca(@RequestBody final UserPresenceRequest request) {
        final var response = userPresenceService.atualizarPresenca(request);

        final String SUCESSFULLY_UPDATED = "Presença Atualizada!";
        LOGGER.info(SUCESSFULLY_UPDATED, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
