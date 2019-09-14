package io.projetocoletarsu.service;

import io.projetocoletarsu.controller.UsuarioApiController;
import io.projetocoletarsu.exception.PersistirDadosException;
import io.projetocoletarsu.model.retorno.RetornoCriarUsuario;
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

    public RetornoCriarUsuario criarUsuario(Usuario usuario) throws PersistirDadosException {
        RetornoCriarUsuario retorno = new RetornoCriarUsuario();

        try {
            if (buscarUsuarioPorCpf(usuario.getCpf()) == null) {
                Usuario usuarioCriado = repository.save(usuario);
                retorno.setSucesso(true);
                retorno.setMensagem("Usuario criado com sucesso");
                retorno.setId(usuarioCriado.getId());

            } else {
                retorno.setSucesso(false);
                retorno.setMensagem("Usuario ja existente");
            }

            return retorno;

        } catch (Exception e) {
            log.error("Error ao persistir os dados", e);
            throw new PersistirDadosException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erro ao persistir dados.");
        }

    }

    public Usuario buscarUsuarioPorCpf(String cpf) {
        return repository.findByCpf(cpf);
    }
}
