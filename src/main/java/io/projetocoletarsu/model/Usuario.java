package io.projetocoletarsu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.projetocoletarsu.model.enums.StatusUsuario;
import io.projetocoletarsu.model.enums.TipoPessoa;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;


/**
 * Usuario
 */
@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonProperty("nomeCompleto")
    @Column(length = 200)
    @NotNull(message = "nome obrigatorio")
    private String nomeCompleto;

    @JsonProperty("email")
    @Column(length = 100)
    @NotNull(message = "email obrigatorio")
    private String email;

    @JsonProperty("senha")
    @Column(length = 25)
    @NotNull(message = "senha obrigatorio")
    private String senha;

    @JsonProperty("celular")
    @Column(length = 25)
    @NotNull(message = "celular obrigatorio")
    private String celular = null;

    @JsonProperty("endereco")
    @Column(length = 300)
    @NotNull(message = "endereco obrigatorio")
    private String endereco = null;

    @JsonProperty("tipoPessoa")
    @NotNull(message = "tipoPessoa obrigatorio")
    private TipoPessoa tipoPessoa = null;

    @JsonProperty("statusUsuario")
    @NotNull(message = "statusUsuario obrigatorio")
    private StatusUsuario statusUsuario = null;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<Agendamento> agendamentos = null;

    public Usuario() {
    }

    public Usuario(@NotNull(message = "nome obrigatorio") String nomeCompleto, @NotNull(message = "email obrigatorio") String email, @NotNull(message = "senha obrigatorio") String senha, @NotNull(message = "celular obrigatorio") String celular, @NotNull(message = "endereco obrigatorio") String endereco, @NotNull(message = "tipoPessoa obrigatorio") TipoPessoa tipoPessoa, @NotNull(message = "statusUsuario obrigatorio") StatusUsuario statusUsuario) {
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.senha = senha;
        this.celular = celular;
        this.endereco = endereco;
        this.tipoPessoa = tipoPessoa;
        this.statusUsuario = statusUsuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public TipoPessoa getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(TipoPessoa tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public StatusUsuario getStatusUsuario() {
        return statusUsuario;
    }

    public void setStatusUsuario(StatusUsuario statusUsuario) {
        this.statusUsuario = statusUsuario;
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }
}

