package io.projetocoletarsu.controller;

import io.projetocoletarsu.model.Agendamento;
import io.projetocoletarsu.model.AgendamentoDTO;
import io.projetocoletarsu.model.retorno.Retorno;
import io.projetocoletarsu.model.retorno.RetornoAgendamento;
import io.projetocoletarsu.model.retorno.RetornoTodosAgendamentos;
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
    public ResponseEntity<RetornoTodosAgendamentos> buscarTodosAgendamentos() {

        try {
            return ResponseEntity.ok(service.buscarTodosAgendamentos());

        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(new RetornoTodosAgendamentos(false, e.getMessage(), null));
        }
    }

    @PostMapping
    public ResponseEntity<Retorno> criarAgendamento(@ApiParam(value = "Novo Agendamento", required = true) @Valid @RequestBody AgendamentoDTO agendamento) {
        Retorno retorno = new Retorno(false, "");

        try {
            RetornoAgendamento retornoService = service.criarAgendamento(agendamento);

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

}





