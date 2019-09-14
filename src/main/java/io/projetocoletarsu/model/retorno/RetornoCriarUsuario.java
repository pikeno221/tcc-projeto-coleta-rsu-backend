package io.projetocoletarsu.model.retorno;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class RetornoCriarUsuario extends Retorno {

    @JsonIgnore
    private int id;

    public RetornoCriarUsuario() {
        super();

    }

    public RetornoCriarUsuario(Boolean sucesso, String mensagem) {
        super(sucesso, mensagem);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
