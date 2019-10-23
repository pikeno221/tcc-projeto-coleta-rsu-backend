package io.projetocoletarsu.model.request;

import io.projetocoletarsu.model.enums.StatusColeta;

import java.io.Serializable;

public class AtualizacaoStatusColetaRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private StatusColeta statusColeta;


    public AtualizacaoStatusColetaRequest() {

    }


    public AtualizacaoStatusColetaRequest(StatusColeta statusColeta) {
        this.statusColeta = statusColeta;
    }

    public StatusColeta getStatusColeta() {
        return statusColeta;
    }

    public void setStatusColeta(StatusColeta statusColeta) {
        this.statusColeta = statusColeta;
    }
}
