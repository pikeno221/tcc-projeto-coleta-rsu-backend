package io.projetocoletarsu.controller;

import io.projetocoletarsu.exception.PersistirDadosException;
import io.projetocoletarsu.model.Retorno;
import io.projetocoletarsu.model.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.projetocoletarsu.service.UsuarioService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

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

    public ResponseEntity<Void> criarUsuario(@ApiParam(value = "Novo Usuario" ,required=true )  @Valid @RequestBody Usuario usuario) {
        try {
            service.criarUsuario(usuario);
            throw new PersistirDadosException(404, "tst");
            //return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
        } catch (PersistirDadosException e) {
            return (ResponseEntity<Void>) ResponseEntity.unprocessableEntity();
        }
    }

    public ResponseEntity<List<Usuario>> buscarTodosUsuarios() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Usuario>>(objectMapper.readValue("[ {  \"senha\" : \"senha\",  \"ativo\" : true,  \"endereco\" : \"endereco\",  \"celular\" : \"celular\",  \"id\" : 0,  \"email\" : \"email\",  \"nomeCompleto\" : \"nomeCompleto\"}, {  \"senha\" : \"senha\",  \"ativo\" : true,  \"endereco\" : \"endereco\",  \"celular\" : \"celular\",  \"id\" : 0,  \"email\" : \"email\",  \"nomeCompleto\" : \"nomeCompleto\"} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Usuario>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Usuario>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Usuario> buscarUsuarioPorId(@ApiParam(value = "Id do Usuario",required=true) @PathVariable("idUsuario") Integer idUsuario) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Usuario>(objectMapper.readValue("{  \"senha\" : \"senha\",  \"ativo\" : true,  \"endereco\" : \"endereco\",  \"celular\" : \"celular\",  \"id\" : 0,  \"email\" : \"email\",  \"nomeCompleto\" : \"nomeCompleto\"}", Usuario.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Usuario>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Usuario>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Retorno> atualizarUsuario(@ApiParam(value = "Usuario atualizado" ,required=true )  @Valid @RequestBody Usuario body, @ApiParam(value = "ID do Usuario",required=true) @PathVariable("idUsuario") Integer idUsuario) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                
                return new ResponseEntity<Retorno>(objectMapper.readValue("{  \"mensagem\" : \"Erro ao atualizar usuario.\",  \"sucesso\" : false}", Retorno.class), HttpStatus.OK);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Retorno(false, "usuario nao encontrado"));
            }
        }

        return new ResponseEntity<Retorno>(HttpStatus.NOT_IMPLEMENTED);
    }


    public ResponseEntity<Void> deletarUsuario(@ApiParam(value = "Id do Usuario",required=true) @PathVariable("idUsuario") Integer idUsuario) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Retorno> logarUsuario(@ApiParam(value = "Email do Usuario" ,required=true) @RequestHeader(value="usuario", required=true) String usuario,@ApiParam(value = "Senha do Usuario" ,required=true) @RequestHeader(value="senha", required=true) String senha) {
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



    public ResponseEntity<Void> logoutUsuario() {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }


    public ResponseEntity<Retorno> recuperarSenha(@ApiParam(value = "email do usuario a ser recuperado a senha" ,required=true )  @Valid @RequestBody Retorno body) {
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
