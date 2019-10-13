package io.projetocoletarsu.controller;

import io.projetocoletarsu.exception.ApiException;
import io.projetocoletarsu.model.AgendamentoDTO;
import io.projetocoletarsu.model.retorno.Retorno;
import io.projetocoletarsu.model.retorno.RetornoAgendamento;
import io.projetocoletarsu.model.retorno.RetornoAgendamentos;
import io.projetocoletarsu.service.AgendamentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/agendamentos")
@Api(value = "agendamentos", description = "")
public class AgendamentoController {

    @Autowired
    private AgendamentoService service;

    @GetMapping
    public ResponseEntity<RetornoAgendamentos> buscarTodosAgendamentos(@ApiParam(value = "token", required = true) @RequestHeader(value = "token", required = true) String token) {

        try {
            tokenValida(token);
            return ResponseEntity.ok(service.buscarTodosAgendamentos());


        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(new RetornoAgendamentos(false, e.getMessage(), null));
        }
    }


    @PostMapping
    public ResponseEntity<Retorno> criarAgendamento(@ApiParam(value = "Novo Agendamento", required = true) @Valid @RequestBody AgendamentoDTO agendamento) {

        try {
            RetornoAgendamento retornoService = service.criarAgendamento(agendamento);

            if (retornoService.isSucesso()) {
                return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}").buildAndExpand(retornoService.getId()).toUri()).build();
            } else {
                Retorno retorno = new Retorno(false, "");
                retorno.setMensagem(retornoService.getMensagem());
                return ResponseEntity.unprocessableEntity().body(retorno);
            }

        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(new Retorno(false, e.getMessage()));
        }
    }


    @GetMapping("/usuarios/{idUsuario}")
    public ResponseEntity<RetornoAgendamentos> buscarAgendamentosPorUsuario(@ApiParam(value = "token", required = true) @RequestHeader(value = "token", required = true) String token, @ApiParam(value = "Id do Usuário", required = true) @PathVariable("idUsuario") Integer idUsuario) {


        try {
            tokenValida(token);
            RetornoAgendamentos retorno = service.buscarAgendamentosPorUsuario(idUsuario);

            if (retorno.isSucesso())
                return ResponseEntity.ok(retorno);
            else
                return ResponseEntity.unprocessableEntity().body(retorno);

        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(new RetornoAgendamentos(false, e.getMessage(), null));

        }

    }

    private void tokenValida(String token) throws ApiException {
        if (!token.equals("123")) {
            throw new ApiException(422, "Token Inválida! ");
        }
    }

}





