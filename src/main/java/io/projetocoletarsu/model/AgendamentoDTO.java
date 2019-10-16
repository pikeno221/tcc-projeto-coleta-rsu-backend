package io.projetocoletarsu.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.projetocoletarsu.model.enums.StatusColeta;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class AgendamentoDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonProperty("status")
    @NotNull(message = "status obrigatorio")
    private StatusColeta status;

    @JsonProperty("usuario")
    @NotNull(message = "idUsuario obrigatorio")
    private Integer idUsuario;


    @JsonProperty("materiaisColeta")
    @NotNull(message = "materiaisColeta obrigatorio")
    private String materiaisColeta;

    @JsonProperty("observacoesUsuario")
    private String observacoesUsuario;

    @JsonProperty("observacoesSistema")
    private String observacoesSistema;

    @JsonProperty("dataAgendada")
    @NotNull(message = "dataAgendada obrigatorio")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dataAgendada;


    @JsonProperty("dataConclusao")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dataConclusao;


    public AgendamentoDTO() {

    }

    public AgendamentoDTO(@NotNull(message = "status obrigatorio") StatusColeta status, @NotNull(message = "idUsuario obrigatorio") Integer idUsuario, @NotNull(message = "materiaisColeta obrigatorio") String materiaisColeta, String observacoesUsuario, String observacoesSistema, @NotNull(message = "dataAgendada obrigatorio") Date dataAgendada, Date dataConclusao) {
        this.status = status;
        this.idUsuario = idUsuario;
        this.materiaisColeta = materiaisColeta;
        this.observacoesUsuario = observacoesUsuario;
        this.observacoesSistema = observacoesSistema;
        this.dataAgendada = dataAgendada;
        this.dataConclusao = dataConclusao;
    }

    public StatusColeta getStatus() {
        return status;
    }

    public void setStatus(StatusColeta status) {
        this.status = status;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getMateriaisColeta() {
        return materiaisColeta;
    }

    public void setMateriaisColeta(String materiaisColeta) {
        this.materiaisColeta = materiaisColeta;
    }

    public String getObservacoesUsuario() {
        return observacoesUsuario;
    }

    public void setObservacoesUsuario(String observacoesUsuario) {
        this.observacoesUsuario = observacoesUsuario;
    }

    public String getObservacoesSistema() {
        return observacoesSistema;
    }

    public void setObservacoesSistema(String observacoesSistema) {
        this.observacoesSistema = observacoesSistema;
    }

    public Date getDataAgendada() {
        return dataAgendada;
    }

    public void setDataAgendada(Date dataAgendada) {
        this.dataAgendada = dataAgendada;
    }

    public Date getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(Date dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public Agendamento dtoToObject(AgendamentoDTO agendamentoDTO, Usuario usuario) {
        return new Agendamento(agendamentoDTO.getStatus(), usuario, agendamentoDTO.getMateriaisColeta(), agendamentoDTO.getObservacoesUsuario(), agendamentoDTO.getObservacoesSistema(), agendamentoDTO.getDataAgendada(), agendamentoDTO.getDataConclusao());
    }
}

