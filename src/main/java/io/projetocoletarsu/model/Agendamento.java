package io.projetocoletarsu.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.projetocoletarsu.model.enums.MaterialColeta;
import io.projetocoletarsu.model.enums.StatusColeta;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "agendamentos")
public class Agendamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonProperty("status")
    private StatusColeta status;

    @JsonProperty("usuario")
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


    @JsonProperty("materialColeta")
    @NotNull(message = "materialColeta obrigatorio")
    private MaterialColeta materialColeta;

    @JsonProperty("observacoesUsuario")
    @Column(length = 500)
    private String observacoesUsuario;

    @JsonProperty("observacoesSistema")
    @Column(length = 500)
    private String observacoesSistema;

    @JsonProperty("dataAgendada")
    @NotNull(message = "dataAgendada obrigatorio")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dataAgendada;


    @JsonProperty("dataConclusao")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dataConclusao;


    public Agendamento() {

    }

    public Agendamento(StatusColeta status, Usuario usuario, @NotNull(message = "materialColeta obrigatorio") MaterialColeta materialColeta, String observacoesUsuario, String observacoesSistema, @NotNull(message = "dataAgendada obrigatorio") Date dataAgendada, Date dataConclusao) {
        this.status = status;
        this.usuario = usuario;
        this.materialColeta = materialColeta;
        this.observacoesUsuario = observacoesUsuario;
        this.observacoesSistema = observacoesSistema;
        this.dataAgendada = dataAgendada;
        this.dataConclusao = dataConclusao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StatusColeta getStatus() {
        return status;
    }

    public void setStatus(StatusColeta status) {
        this.status = status;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public MaterialColeta getMaterialColeta() {
        return materialColeta;
    }

    public void setMaterialColeta(MaterialColeta materialColeta) {
        this.materialColeta = materialColeta;
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
        return new Agendamento(agendamentoDTO.getStatus(), usuario,agendamentoDTO.getMaterialColeta(), agendamentoDTO.getObservacoesUsuario(), agendamentoDTO.getObservacoesSistema(), agendamentoDTO.getDataAgendada(), agendamentoDTO.getDataConclusao());
    }
}
