package com.estudo.applicationservice.controller;
import com.estudo.applicationservice.UsuarioPresencaService;
import com.estudo.applicationservice.domain.UsuarioPresenca;
import com.estudo.applicationservice.repository.UsuarioPresencaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/presenca")
public class UsuarioPresencaController {

    @Autowired
    private UsuarioPresencaService usuarioPresencaService;

    @PostMapping
    public ResponseEntity<String> registrarPresenca(@RequestBody UsuarioPresenca usuarioPresenca) {
        final String mensagem = usuarioPresencaService.registrarPresenca(usuarioPresenca);
        return ResponseEntity.status(HttpStatus.OK).body(mensagem);
    }
}
