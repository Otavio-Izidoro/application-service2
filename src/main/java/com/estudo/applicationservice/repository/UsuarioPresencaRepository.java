package com.estudo.applicationservice.repository;
import com.estudo.applicationservice.domain.UsuarioPresenca;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioPresencaRepository extends MongoRepository<UsuarioPresenca, String> {
    UsuarioPresenca findUsuarioPresencaById(String id);
}
