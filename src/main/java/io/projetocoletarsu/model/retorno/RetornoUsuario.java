package io.projetocoletarsu.model.retorno;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.projetocoletarsu.model.Usuario;

public class RetornoUsuario extends Retorno {

    @JsonIgnore
    private Integer id;

    private Usuario usuario;

    public RetornoUsuario() {
        super();

    }

    public RetornoUsuario(Boolean sucesso, String mensagem, Integer id, Usuario usuario) {
        super(sucesso, mensagem);
        this.id = id;
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
