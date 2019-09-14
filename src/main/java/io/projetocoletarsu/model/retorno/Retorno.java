package io.projetocoletarsu.model.retorno;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * Retorno
 */
@Validated
public class Retorno   {
  @JsonProperty("sucesso")
  private Boolean sucesso = null;

  @JsonProperty("mensagem")
  private String mensagem = null;

  public Retorno(Boolean sucesso, String mensagem) {
    this.sucesso = sucesso;
    this.mensagem = mensagem;

  }

  public Retorno(){

  }

  /**
   * Get sucesso
   * @return sucesso
  **/
  @ApiModelProperty(value = "")


  public Boolean isSucesso() {
    return sucesso;
  }

  public void setSucesso(Boolean sucesso) {
    this.sucesso = sucesso;
  }

  public Retorno mensagem(String mensagem) {
    this.mensagem = mensagem;
    return this;
  }

  /**
   * Get mensagem
   * @return mensagem
  **/
  @ApiModelProperty(value = "")


  public String getMensagem() {
    return mensagem;
  }

  public void setMensagem(String mensagem) {
    this.mensagem = mensagem;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Retorno retorno = (Retorno) o;
    return Objects.equals(this.sucesso, retorno.sucesso) &&
        Objects.equals(this.mensagem, retorno.mensagem);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sucesso, mensagem);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Retorno {\n");
    
    sb.append("    sucesso: ").append(toIndentedString(sucesso)).append("\n");
    sb.append("    mensagem: ").append(toIndentedString(mensagem)).append("\n");
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

