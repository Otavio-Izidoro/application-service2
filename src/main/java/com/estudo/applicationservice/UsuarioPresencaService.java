package com.estudo.applicationservice;

import com.estudo.applicationservice.domain.UsuarioPresenca;
import com.estudo.applicationservice.repository.UsuarioPresencaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioPresencaService {

    @Autowired
    private UsuarioPresencaRepository repository;

    public String registrarPresenca(UsuarioPresenca usuarioPresenca) {
        UsuarioPresenca existingPresenca = repository.findById(usuarioPresenca.getId()).orElse(null);

        if (existingPresenca == null) {
            repository.save(usuarioPresenca);
            return "Frequência registrada com sucesso!";
        } else {
            if (existingPresenca.isPresenca() != usuarioPresenca.isPresenca()) {
                existingPresenca.setPresenca(usuarioPresenca.isPresenca());
                repository.save(existingPresenca);
                return "Frequência atualizada com sucesso!";
            } else {
                return "A frequência já está atualizada!";
            }
        }
    }
}
