package io.projetocoletarsu.controller;

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

import javax.validation.Valid;

@Controller
public class UsuarioApiController implements UsuarioApi {

    private static final Logger log = LoggerFactory.getLogger(UsuarioApiController.class);

    @Autowired
    private UsuarioService service;


    public ResponseEntity<Retorno> criarUsuario(@ApiParam(value = "Novo Usuario", required = true) @Valid @RequestBody Usuario usuario) {
        Retorno retorno = new Retorno(false, "");

        try {
            RetornoUsuario retornoService = service.criarUsuario(usuario);

            if (retornoService.isSucesso()) {
                return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}").buildAndExpand(retornoService.getId()).toUri()).build();
            } else {
                retorno.setMensagem(retornoService.getMensagem());
                return ResponseEntity.unprocessableEntity().body(retorno);
            }

        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(new Retorno(false, e.getMessage()));
        }
    }

    public ResponseEntity<RetornoTodosUsuarios> buscarTodosUsuarios() {

        try {
            return ResponseEntity.ok(service.buscarTodosUsuarios());

        } catch (ApiException e) {
            return ResponseEntity.unprocessableEntity().body(new RetornoTodosUsuarios(false, e.getMessage(), null));

        }
    }

    public ResponseEntity<RetornoUsuario> buscarUsuarioPorId(@ApiParam(value = "Id do Usuario", required = true) @PathVariable("idUsuario") Integer idUsuario) {

        try {
            RetornoUsuario retorno = service.buscarUsuarioPorId(idUsuario);

            if (retorno.isSucesso())
                return ResponseEntity.ok(retorno);
             else
                return ResponseEntity.unprocessableEntity().body(retorno);


        } catch (ApiException e) {
            return ResponseEntity.unprocessableEntity().body(new RetornoUsuario(false, e.getMessage(), null, null));

        }
    }

    public ResponseEntity<RetornoUsuario> atualizarUsuario(@ApiParam(value = "Usuario atualizado", required = true) @Valid @RequestBody Usuario usuario, @ApiParam(value = "ID do Usuario", required = true) @PathVariable("idUsuario") Integer idUsuario) {

        try {
            RetornoUsuario retorno = service.atualizarUsuario(idUsuario, usuario);

            if (retorno.isSucesso()) {
                return ResponseEntity.ok(retorno);
            } else {
                return ResponseEntity.unprocessableEntity().body(retorno);
            }
        } catch (ApiException e) {
            return ResponseEntity.unprocessableEntity().body(new RetornoUsuario(false, e.getMessage(), idUsuario, null));
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
            } else {

            }
        } catch (Exception e) {
            log.error("error ao fazer request", e);
        }
        return ResponseEntity.unprocessableEntity().build();

    }

    public ResponseEntity<RetornoUsuario> logarUsuario(@ApiParam(value = "Email do Usuario", required = true) @RequestHeader(value = "email", required = true) String email, @ApiParam(value = "Senha do Usuario", required = true) @RequestHeader(value = "senha", required = true) String senha) {
        try {
            RetornoUsuario retorno = service.logarUsuario(email, senha);

            if (retorno.isSucesso()) {
                return ResponseEntity.ok(retorno);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(retorno);
            }

        } catch (ApiException e) {
            return ResponseEntity.unprocessableEntity().body(new RetornoUsuario(false, e.getMessage(), null, null));
        }
    }


    public ResponseEntity<Retorno> recuperarSenha
            (@ApiParam(value = "email do usuario a ser recuperado a senha", required = true) @Valid @RequestHeader(value = "usuario", required = true) String
                     email) {
        try {
            Retorno retorno = service.recuperarSenhaUsuario(email);

            if (retorno.isSucesso()) {
                return ResponseEntity.ok(retorno);
            } else {
                return ResponseEntity.unprocessableEntity().body(retorno);
            }
        } catch (Exception e) {

            return ResponseEntity.unprocessableEntity().body(new Retorno(false, e.getMessage()));

        }


    }

}
