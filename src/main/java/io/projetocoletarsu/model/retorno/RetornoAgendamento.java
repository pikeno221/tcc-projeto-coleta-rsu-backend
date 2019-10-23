package io.projetocoletarsu.model.retorno;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.projetocoletarsu.model.Agendamento;

public class RetornoAgendamento extends Retorno {

    @JsonIgnore
    private Integer id;
    private Agendamento agendamento;

    public RetornoAgendamento() {
        super();
    }

    public RetornoAgendamento(Boolean sucesso, String mensagem, Integer id, Agendamento agendamento) {
        super(sucesso, mensagem);
        this.id = id;
        this.agendamento = agendamento;
    }

    public RetornoAgendamento(Integer id, Agendamento agendamento) {
        this.id = id;
        this.agendamento = agendamento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }
}

