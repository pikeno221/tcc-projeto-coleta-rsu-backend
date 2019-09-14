package io.projetocoletarsu.model.retorno;

import io.projetocoletarsu.model.Usuario;

import java.util.List;

public class RetornoTodosUsuarios extends Retorno {

    private List<Usuario> usuarios;

    public RetornoTodosUsuarios(Boolean sucesso, String mensagem, List<Usuario> usuarios) {
        super(sucesso, mensagem);
        this.usuarios = usuarios;

    }

    public RetornoTodosUsuarios() {

    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
