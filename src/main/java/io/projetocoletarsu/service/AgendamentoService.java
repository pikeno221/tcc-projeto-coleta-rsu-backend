package io.projetocoletarsu.service;

import io.projetocoletarsu.exception.ApiException;
import io.projetocoletarsu.model.Agendamento;
import io.projetocoletarsu.model.AgendamentoDTO;
import io.projetocoletarsu.model.enums.StatusColeta;
import io.projetocoletarsu.model.request.AtualizacaoStatusColetaRequest;
import io.projetocoletarsu.model.retorno.RetornoAgendamento;
import io.projetocoletarsu.model.retorno.RetornoAgendamentos;
import io.projetocoletarsu.model.retorno.RetornoUsuario;
import io.projetocoletarsu.repository.AgendamentoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);


    public RetornoAgendamentos buscarTodosAgendamentos(String filtro) throws ApiException {
        RetornoAgendamentos retorno = new RetornoAgendamentos();

        try {

            StatusColeta statusColetaQueryParameter = StatusColetaQueryParameterToStatusColetaEnum(filtro);
            if (statusColetaQueryParameter != null) {
                retorno.setAgendamentos(repository.findAgendamentosByStatus(statusColetaQueryParameter));
            } else {
                retorno.setAgendamentos(repository.findAll());
            }
            retorno.setSucesso(true);

            if (retorno.getAgendamentos().isEmpty()) {
                retorno.setMensagem("Nenhum Agendamnto Encontrado");
            } else {
                retorno.setMensagem("Agendamentos buscados com sucesso");
            }

            return retorno;

        } catch (Exception e) {
            log.error("Erro ao buscar os dados", e);
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
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

    public RetornoAgendamento deletarAgendamento(Integer id) throws ApiException {
        RetornoAgendamento retorno = new RetornoAgendamento();

        Optional<Agendamento> agendamentoBanco = repository.findById(id);

        if (agendamentoBanco.isPresent()) {
            try {
                repository.delete(agendamentoBanco.get());
                retorno.setSucesso(true);
                retorno.setMensagem("Sucesso ao realizar delecao do agendamento");

            } catch (Exception e) {
                log.error("Error ao excluir agendamento.", e);
                throw new ApiException(422, "Erro ao realizar delecao do agendamento");
            }
        } else {
            retorno.setSucesso(false);
            retorno.setMensagem("Agendamento não encontrado");
        }

        return retorno;
    }

    public RetornoAgendamento atualizarStatusColetaAgendamento(Integer id, AtualizacaoStatusColetaRequest statusColetaRequest) throws ApiException {
        try {
            RetornoAgendamento retorno = buscarAgendamentoPorId(id);
            ;

            if (statusColetaRequest.getStatusColeta().equals(StatusColeta.CONCLUIDO) || statusColetaRequest.getStatusColeta().equals(StatusColeta.CANCELADO)) {
                retorno.getAgendamento().setDataConclusao(new Date());
            }
            retorno.getAgendamento().setStatus(statusColetaRequest.getStatusColeta());
            if (retorno.isSucesso()) {
                retorno.setAgendamento(repository.save(retorno.getAgendamento()));
                retorno.setSucesso(true);
                retorno.setMensagem("Status Coleta atualizado com sucesso");
            }

            return retorno;

        } catch (Exception e) {
            log.error("Erro ao atualizar o status do agendamento", e);
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erro ao buscar os dados.");

        }


    }

    public RetornoAgendamento buscarAgendamentoPorId(Integer id) throws ApiException {
        RetornoAgendamento retorno = new RetornoAgendamento();
        Optional<Agendamento> agendamento;

        try {
            agendamento = repository.findById(id);

        } catch (Exception e) {
            log.error("Erro ao buscar os dados", e);
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erro ao buscar os dados.");

        }

        if (agendamento.isPresent()) {
            retorno.setAgendamento(agendamento.get());
            retorno.setMensagem("Sucesso ao buscar agendamento");
            retorno.setSucesso(true);
        } else {
            retorno.setAgendamento(null);
            retorno.setMensagem("Agendamento não encontrado");
            retorno.setSucesso(false);
        }

        return retorno;

    }

    private StatusColeta StatusColetaQueryParameterToStatusColetaEnum(String queryParameterStatusColeta) throws ApiException {

        if (queryParameterStatusColeta != null && !queryParameterStatusColeta.isEmpty()) {
            try {
                return StatusColeta.valueOf(queryParameterStatusColeta);
            } catch (Exception e) {
                log.error("Erro ao converter filtro de coleta vindo do query param", e);
                throw new ApiException(HttpStatus.BAD_REQUEST.value(), "Erro ao converter filtro de coleta vindo do query param");

            }
        }

    return null;

    }


}
