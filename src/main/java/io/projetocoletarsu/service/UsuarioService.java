package io.projetocoletarsu.service;

import io.projetocoletarsu.controller.UsuarioApiController;
import io.projetocoletarsu.exception.BDException;
import io.projetocoletarsu.model.Usuario;
import io.projetocoletarsu.model.retorno.RetornoUsuario;
import io.projetocoletarsu.model.retorno.RetornoTodosUsuarios;
import io.projetocoletarsu.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private static final Logger log = LoggerFactory.getLogger(UsuarioApiController.class);

    @Autowired
    private UsuarioRepository repository;

    public RetornoUsuario criarUsuario(Usuario usuario) throws BDException {
        RetornoUsuario retorno = new RetornoUsuario();

        try {
            if (usuarioValidoParaInsercao(usuario)) {
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
            throw new BDException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erro ao persistir dados.");
        }

    }

    public RetornoTodosUsuarios buscarTodosUsuarios() throws BDException {
        RetornoTodosUsuarios retorno = new RetornoTodosUsuarios();

        try {
            retorno.setUsuarios(repository.findAll());
            retorno.setSucesso(true);

            if (retorno.getUsuarios().isEmpty()) {
                retorno.setMensagem("Nenhum Usuario Encontrado");
            } else {
                retorno.setMensagem("Usuarios buscados com sucesso");
            }

            return retorno;

        } catch (Exception e) {
            log.error("Erro ao buscar os dados", e);
            throw new BDException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erro ao buscar os dados.");
        }

    }

    public RetornoUsuario buscarUsuarioPorId(Integer idUsuario) throws BDException {
        RetornoUsuario retorno = new RetornoUsuario();

        try {
            Optional<Usuario> usuario = repository.findById(idUsuario);

            if (usuario.isPresent()) {
                retorno.setUsuario(usuario.get());
                retorno.setMensagem("Sucesso ao buscar usuario");
                retorno.setSucesso(true);
            } else {
                retorno.setUsuario(null);
                retorno.setMensagem("Usuario Nao Encontrado");
                retorno.setSucesso(false);
            }

            return retorno;

        } catch (Exception e) {
            log.error("Erro ao buscar os dados", e);
            throw new BDException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erro ao buscar os dados.");

        }

    }
    private boolean usuarioValidoParaInsercao(Usuario usuario) {
        return buscarUsuarioPorCpf(usuario.getCpf()) == null && buscarUsuarioPorEmail(usuario.getEmail()) == null && buscarUsuarioPorTelefone(usuario.getCelular()) == null;
    }

    private Usuario buscarUsuarioPorTelefone(String celular) {
        return repository.findByCelular(celular);
    }

    public Usuario buscarUsuarioPorCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return repository.findByEmail(email);
    }



}
