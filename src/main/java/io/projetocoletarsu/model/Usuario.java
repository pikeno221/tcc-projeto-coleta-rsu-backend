package io.projetocoletarsu.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

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

    @JsonProperty("cpf")
    @Column(length = 200)
    @NotNull(message = "cpf obrigatorio")
    private String cpf;

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
    private String endereco;

    @JsonProperty("statusUsuario")
    @NotNull(message = "statusUsuario obrigatorio")
    private StatusUsuario statusUsuario = null;

    public Usuario id(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * Get id
     *
     * @return id
     **/
    @ApiModelProperty(value = "")


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario nomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
        return this;
    }

    /**
     * Get nomeCompleto
     *
     * @return nomeCompleto
     **/
    @ApiModelProperty(value = "")


    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public Usuario email(String email) {
        this.email = email;
        return this;
    }

    /**
     * Get email
     *
     * @return email
     **/
    @ApiModelProperty(value = "")


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Usuario senha(String senha) {
        this.senha = senha;
        return this;
    }

    /**
     * Get senha
     *
     * @return senha
     **/
    @ApiModelProperty(value = "")


    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario celular(String celular) {
        this.celular = celular;
        return this;
    }

    /**
     * Get celular
     *
     * @return celular
     **/
    @ApiModelProperty(value = "")


    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Usuario endereco(String endereco) {
        this.endereco = endereco;
        return this;
    }

    /**
     * Get endereco
     *
     * @return endereco
     **/


    @ApiModelProperty(value = "")


    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public StatusUsuario getStatusUsuario() {
        return statusUsuario;
    }

    public void setStatusUsuario(StatusUsuario statusUsuario) {
        this.statusUsuario = statusUsuario;
    }

    /**
     * Status Usuario
     *
     * @return statusUsuario
     **/
    @ApiModelProperty(value = "Status Usuario")


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Usuario usuario = (Usuario) o;
        return Objects.equals(this.nomeCompleto, usuario.nomeCompleto) &&
                Objects.equals(this.email, usuario.email) &&
                Objects.equals(this.senha, usuario.senha) &&
                Objects.equals(this.celular, usuario.celular) &&
                Objects.equals(this.endereco, usuario.endereco) &&
                Objects.equals(this.statusUsuario, usuario.statusUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nomeCompleto, email, senha, celular, endereco, statusUsuario);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Usuario {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    cpf: ").append(toIndentedString(cpf)).append("\n");
        sb.append("    nomeCompleto: ").append(toIndentedString(nomeCompleto)).append("\n");
        sb.append("    email: ").append(toIndentedString(email)).append("\n");
        sb.append("    senha: ").append(toIndentedString(senha)).append("\n");
        sb.append("    celular: ").append(toIndentedString(celular)).append("\n");
        sb.append("    endereco: ").append(toIndentedString(endereco)).append("\n");
        sb.append("    statusUsuario: ").append(toIndentedString(statusUsuario)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

