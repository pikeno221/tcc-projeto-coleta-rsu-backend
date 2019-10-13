package io.projetocoletarsu.model.retorno;

import io.projetocoletarsu.model.Agendamento;

import java.util.List;

public class RetornoTodosAgendamentos extends Retorno {

    private List<Agendamento> agendamentos;

    public RetornoTodosAgendamentos() {

    }

    public RetornoTodosAgendamentos(Boolean sucesso, String mensagem, List<Agendamento> agendamentos) {
        super(sucesso, mensagem);
        this.agendamentos = agendamentos;
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }
}
