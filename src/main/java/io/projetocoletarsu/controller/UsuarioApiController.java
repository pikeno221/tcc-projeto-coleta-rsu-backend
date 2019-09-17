package io.projetocoletarsu.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.projetocoletarsu.exception.ApiException;
import io.projetocoletarsu.model.Usuario;
import io.projetocoletarsu.model.retorno.Retorno;
import io.projetocoletarsu.model.retorno.RetornoTodosUsuarios;
import io.projetocoletarsu.model.retorno.RetornoUsuario;
import io.projetocoletarsu.service.UsuarioService;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@Controller
public class UsuarioApiController implements UsuarioApi {

    private static final Logger log = LoggerFactory.getLogger(UsuarioApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private UsuarioService service;

    @Autowired
    public UsuarioApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Retorno> criarUsuario(@ApiParam(value = "Novo Usuario", required = true) @Valid @RequestBody Usuario usuario) {
        try {
            RetornoUsuario retornoService = service.criarUsuario(usuario);

            if (retornoService.isSucesso()) {
                return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(retornoService.getId())
                        .toUri()).build();
            } else {
                return ResponseEntity.unprocessableEntity().body(retornoService);
            }

        } catch (ApiException e) {
            return ResponseEntity.unprocessableEntity().body(new RetornoTodosUsuarios(false, "Erro ao criar usuario", null));
        }
    }

    public ResponseEntity<RetornoTodosUsuarios> buscarTodosUsuarios() {
        try {
            return ResponseEntity.ok(service.buscarTodosUsuarios());

        } catch (ApiException e) {
            return ResponseEntity.unprocessableEntity().body(new RetornoTodosUsuarios(false, "Erro ao buscar usuarios", null));
        }
    }

    public ResponseEntity<RetornoUsuario> buscarUsuarioPorId(@ApiParam(value = "Id do Usuario", required = true) @PathVariable("idUsuario") Integer idUsuario) {

        try {
            RetornoUsuario retorno = service.buscarUsuarioPorId(idUsuario);
            if (retorno.isSucesso()) {
                return ResponseEntity.ok(retorno);
            } else {
                return ResponseEntity.unprocessableEntity().body(retorno);
            }

        } catch (ApiException e) {
            return ResponseEntity.unprocessableEntity().body(new RetornoUsuario(false, "Erro ao buscar usuario", null, null));
        }
    }

    public ResponseEntity<Retorno> atualizarUsuario(@ApiParam(value = "Usuario atualizado", required = true) @Valid @RequestBody Usuario usuario, @ApiParam(value = "ID do Usuario", required = true) @PathVariable("idUsuario") Integer idUsuario) {
        try {
            RetornoUsuario retorno = service.atualizarUsuario(idUsuario, usuario);

            if (retorno.isSucesso()) {
                return ResponseEntity.ok(retorno);
            } else {
                return ResponseEntity.unprocessableEntity().body(retorno);
            }
        } catch (ApiException e) {
            return ResponseEntity.unprocessableEntity().body(new RetornoUsuario(false, "Erro ao atualizar usuario", null, null));
        }
    }


    public ResponseEntity<Void> deletarUsuario(@ApiParam(value = "Id do Usuario", required = true) @PathVariable("idUsuario") Integer idUsuario) {
        try {
            RetornoUsuario retorno = service.deletarUsuario(idUsuario);

            if (retorno.isSucesso()) {

                if (retorno.getUsuario() == null) {
                    return ResponseEntity.noContent().build();
                } else {
                    return ResponseEntity.ok().build();

                }
            }
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();

        }

        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Retorno> logarUsuario(@ApiParam(value = "Email do Usuario", required = true) @RequestHeader(value = "usuario", required = true) String usuario, @ApiParam(value = "Senha do Usuario", required = true) @RequestHeader(value = "senha", required = true) String senha) {
        try {
            RetornoUsuario retorno = service.logarUsuario(usuario, senha);

            if (retorno.isSucesso()) {
                return ResponseEntity.ok(retorno);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(retorno);
            }

        } catch (ApiException e) {
            return ResponseEntity.unprocessableEntity().body(new Retorno(false, "Usuario e senha incorreto"));
        }
    }


    public ResponseEntity<Retorno> recuperarSenha
            (@ApiParam(value = "email do usuario a ser recuperado a senha", required = true) @Valid @RequestBody Retorno
                     body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Retorno>(objectMapper.readValue("{  \"mensagem\" : \"mensagem\",  \"sucesso\" : true}", Retorno.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Retorno>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Retorno>(HttpStatus.NOT_IMPLEMENTED);
    }

}
