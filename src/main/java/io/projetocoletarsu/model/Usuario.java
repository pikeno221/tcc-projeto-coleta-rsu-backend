package io.projetocoletarsu.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * Usuario
 */
@Validated
public class Usuario   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("nomeCompleto")
  private String nomeCompleto = null;

  @JsonProperty("email")
  private String email = null;

  @JsonProperty("senha")
  private String senha = null;

  @JsonProperty("celular")
  private String celular = null;

  @JsonProperty("endereco")
  private String endereco = null;

  @JsonProperty("ativo")
  private Boolean ativo = null;

  public Usuario id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Usuario nomeCompleto(String nomeCompleto) {
    this.nomeCompleto = nomeCompleto;
    return this;
  }

  /**
   * Get nomeCompleto
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
   * @return endereco
  **/
  @ApiModelProperty(value = "")


  public String getEndereco() {
    return endereco;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }

  public Usuario ativo(Boolean ativo) {
    this.ativo = ativo;
    return this;
  }

  /**
   * Status Usuario
   * @return ativo
  **/
  @ApiModelProperty(value = "Status Usuario")


  public Boolean isAtivo() {
    return ativo;
  }

  public void setAtivo(Boolean ativo) {
    this.ativo = ativo;
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
    return Objects.equals(this.id, usuario.id) &&
        Objects.equals(this.nomeCompleto, usuario.nomeCompleto) &&
        Objects.equals(this.email, usuario.email) &&
        Objects.equals(this.senha, usuario.senha) &&
        Objects.equals(this.celular, usuario.celular) &&
        Objects.equals(this.endereco, usuario.endereco) &&
        Objects.equals(this.ativo, usuario.ativo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nomeCompleto, email, senha, celular, endereco, ativo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Usuario {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    nomeCompleto: ").append(toIndentedString(nomeCompleto)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    senha: ").append(toIndentedString(senha)).append("\n");
    sb.append("    celular: ").append(toIndentedString(celular)).append("\n");
    sb.append("    endereco: ").append(toIndentedString(endereco)).append("\n");
    sb.append("    ativo: ").append(toIndentedString(ativo)).append("\n");
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

