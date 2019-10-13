package io.projetocoletarsu.service;

import io.projetocoletarsu.exception.ApiException;
import io.projetocoletarsu.model.Agendamento;
import io.projetocoletarsu.model.AgendamentoDTO;
import io.projetocoletarsu.model.retorno.RetornoAgendamento;
import io.projetocoletarsu.model.retorno.RetornoTodosAgendamentos;
import io.projetocoletarsu.model.retorno.RetornoUsuario;
import io.projetocoletarsu.repository.AgendamentoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);


    public RetornoTodosAgendamentos buscarTodosAgendamentos() throws ApiException {
        RetornoTodosAgendamentos retorno = new RetornoTodosAgendamentos();

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

}
