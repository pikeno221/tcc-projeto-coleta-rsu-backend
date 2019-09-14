package io.projetocoletarsu.service;

import io.projetocoletarsu.controller.UsuarioApiController;
import io.projetocoletarsu.exception.PersistirDadosException;
import io.projetocoletarsu.model.Usuario;
import io.projetocoletarsu.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private static final Logger log = LoggerFactory.getLogger(UsuarioApiController.class);

    @Autowired
    private UsuarioRepository repository;

    public void criarUsuario(Usuario usuario) throws PersistirDadosException {
        try {
            repository.save(usuario);
        } catch (Exception e) {
            log.error("Error ao persistir os dados", e);
            throw new PersistirDadosException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erro ao persistir dados.");
        }

    }
}
