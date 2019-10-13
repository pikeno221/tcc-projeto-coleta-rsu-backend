package io.projetocoletarsu.service;

import io.projetocoletarsu.exception.ApiException;
import io.projetocoletarsu.model.Agendamento;
import io.projetocoletarsu.model.AgendamentoDTO;
import io.projetocoletarsu.model.retorno.RetornoAgendamento;
import io.projetocoletarsu.model.retorno.RetornoAgendamentos;
import io.projetocoletarsu.model.retorno.RetornoUsuario;
import io.projetocoletarsu.repository.AgendamentoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);


    public RetornoAgendamentos buscarTodosAgendamentos() throws ApiException {
        RetornoAgendamentos retorno = new RetornoAgendamentos();

        try {
            retorno.setAgendamentos(repository.findAll());
            retorno.setSucesso(true);

            if (retorno.getAgendamentos().isEmpty()) {
                retorno.setMensagem("Nenhum Agendamnto Encontrado");
            } else {
                retorno.setMensagem("Agendamentos buscados com sucesso");
            }

            return retorno;

        } catch (Exception e) {
            log.error("Erro ao buscar os dados", e);
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erro ao buscar os dados.");
        }

    }

    public RetornoAgendamento criarAgendamento(AgendamentoDTO agendamentoRequest) throws ApiException {
        RetornoAgendamento retorno = new RetornoAgendamento();

        try {
            Agendamento agendamento = new Agendamento();

            RetornoUsuario usuario = usuarioService.buscarUsuarioPorId(agendamentoRequest.getIdUsuario());

            if (usuario.isSucesso()) {
                agendamento = agendamentoRequest.dtoToObject(agendamentoRequest, usuario.getUsuario());
                if (agendamentoValidoParaInsercao(agendamento)) {
                    Agendamento agendamentoCriado = repository.save(agendamento);
                    retorno.setSucesso(true);
                    retorno.setMensagem("Agendamento criado com sucesso");
                    retorno.setId(agendamentoCriado.getId());

                } else {
                    retorno.setSucesso(false);
                    retorno.setMensagem("Usuario, Email, Telefone ja existente");
                }

            } else {
                retorno.setSucesso(false);
                retorno.setMensagem("Usuario Não Encontrado");
            }

            return retorno;

        } catch (Exception e) {
            log.error("Error ao persistir os dados", e);
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erro ao persistir dados.");
        }
    }

    private boolean agendamentoValidoParaInsercao(Agendamento agendamento) {
        return true;
    }

    public RetornoAgendamentos buscarAgendamentosPorUsuario(Integer idUsuario) throws ApiException {
        RetornoAgendamentos retorno = new RetornoAgendamentos();
        Optional<List<Agendamento>> agendamentos;

        try {
            agendamentos = repository.findAgendamentosByUsuarioIdOrderByDataAgendada(idUsuario);

        } catch (Exception e) {
            log.error("Error ao buscar os dados", e);
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erro ao buscar os dados.");

        }

        if (agendamentos.isPresent()) {
            retorno.setAgendamentos(agendamentos.get());
            retorno.setMensagem("Sucesso ao buscar agendamentos");
            retorno.setSucesso(true);
        } else {
            retorno.setAgendamentos(null);
            retorno.setMensagem("Nenhum Agendamento Encontrado");
        }

        return retorno;
    }
}
