package io.projetocoletarsu.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @JsonProperty("materiaisColeta")
    private String materiaisColeta;

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



    public Agendamento(StatusColeta status, Usuario usuario, String materiaisColeta, String observacoesUsuario, String observacoesSistema, @NotNull(message = "dataAgendada obrigatorio") Date dataAgendada, Date dataConclusao) {
        this.status = status;
        this.usuario = usuario;
        this.materiaisColeta = materiaisColeta;
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

}
