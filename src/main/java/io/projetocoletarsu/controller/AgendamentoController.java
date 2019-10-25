package io.projetocoletarsu.controller;

import io.projetocoletarsu.exception.ApiException;
import io.projetocoletarsu.model.AgendamentoDTO;
import io.projetocoletarsu.model.request.AtualizacaoStatusColetaRequest;
import io.projetocoletarsu.model.retorno.Retorno;
import io.projetocoletarsu.model.retorno.RetornoAgendamento;
import io.projetocoletarsu.model.retorno.RetornoAgendamentos;
import io.projetocoletarsu.service.AgendamentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/agendamentos")
@Api(value = "agendamentos", description = "")
public class AgendamentoController {

    private static final Logger log = LoggerFactory.getLogger(AgendamentoController.class);

    @Autowired
    private AgendamentoService service;

    @GetMapping
    public ResponseEntity<RetornoAgendamentos> buscarTodosAgendamentos(@ApiParam(value = "token", required = true) @RequestHeader(value = "token", required = true) String token, @ApiParam(value = "filtro", required = false) @RequestParam(value = "filtro", required = false) String filtro) {

        try {
            tokenValida(token);
            return ResponseEntity.ok(service.buscarTodosAgendamentos(filtro));


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
    public ResponseEntity<RetornoAgendamentos> buscarAgendamentosPorUsuario(@ApiParam(value = "token", required = true) @RequestHeader(value = "token", required = true) String token,
                                                                            @ApiParam(value = "Id do Usuário", required = true) @PathVariable("idUsuario") Integer idUsuario,
                                                                            @ApiParam(value = "filtro", required = false) @RequestParam("filtro") String filtro) {


        try {
            tokenValida(token);
            RetornoAgendamentos retorno = service.buscarAgendamentosPorUsuario(idUsuario, filtro);

            if (retorno.isSucesso())
                return ResponseEntity.ok(retorno);
            else
                return ResponseEntity.unprocessableEntity().body(retorno);

        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(new RetornoAgendamentos(false, e.getMessage(), null));

        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<RetornoAgendamento> buscarAgendamentoPorId(@ApiParam(value = "token", required = true) @RequestHeader String token, @ApiParam(value = "Id do Agendamento", required = true) @PathVariable Integer id) {
        try {
            tokenValida(token);
            RetornoAgendamento retorno = service.buscarAgendamentoPorId(id);

            if (retorno.isSucesso()) {
                return ResponseEntity.ok(retorno);
            }
        } catch (Exception e) {
            log.error("error ao fazer request", e);
        }
        return ResponseEntity.unprocessableEntity().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAgendamento(@ApiParam(value = "token", required = true) @RequestHeader String token, @ApiParam(value = "Id do Agendamento", required = true) @PathVariable Integer id) {
        try {
            tokenValida(token);
            RetornoAgendamento retorno = service.deletarAgendamento(id);

            if (retorno.isSucesso()) {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            log.error("error ao fazer request", e);
        }
        return ResponseEntity.unprocessableEntity().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RetornoAgendamento> atualizarStatusColetaAgendamento(@ApiParam(value = "token", required = true) @RequestHeader String token, @ApiParam(value = "Novo Status do Agendamento", required = true) @RequestBody AtualizacaoStatusColetaRequest statusColeta, @ApiParam(value = "Id do Agendamento", required = true) @PathVariable Integer id) {
        try {
            tokenValida(token);
            RetornoAgendamento retorno = service.atualizarStatusColetaAgendamento(id, statusColeta);

            if (retorno.isSucesso()) {
                return ResponseEntity.ok(retorno);
            } else {
                return ResponseEntity.unprocessableEntity().body(retorno);
            }
        } catch (Exception e) {
            log.error("Error ao fazer request", e);
        }
        return ResponseEntity.unprocessableEntity().build();
    }


    private void tokenValida(String token) throws ApiException {
        if (!token.equals("123")) {
            throw new ApiException(422, "Token Inválida! ");
        }
    }
}





