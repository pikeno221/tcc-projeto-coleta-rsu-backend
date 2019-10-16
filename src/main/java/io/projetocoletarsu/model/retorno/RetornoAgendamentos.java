package io.projetocoletarsu.model.retorno;

import io.projetocoletarsu.model.Agendamento;

import java.util.List;

public class RetornoAgendamentos extends Retorno {

    private List<Agendamento> agendamentos;

    public RetornoAgendamentos() {

    }

    public RetornoAgendamentos(Boolean sucesso, String mensagem, List<Agendamento> agendamentos) {
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
