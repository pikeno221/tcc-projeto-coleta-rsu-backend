package io.projetocoletarsu.model.enums;

import io.projetocoletarsu.exception.NotFoundException;
import org.springframework.http.HttpStatus;

public enum StatusColeta {


    AGUARDANDO_CONFICAMACAO(1, "Aguardando Confirmação"), AGENDADO(2, "Agendado"), CANCELADO(3, "Papel"), CONCLUIDO(4, "Alumínio");

    private Integer codigo;
    private String descricao;

    private StatusColeta(Integer codigo, String descricao) {

    }

    public Integer getCodigo() {
        return this.codigo;

    }

    public String getDescricao() {
        return this.descricao;

    }

    public static StatusColeta toEnum(Integer codigo) throws NotFoundException {

        if (codigo == null)
            return null;

        for (StatusColeta x : StatusColeta.values()) {
            if (codigo.equals(x.getCodigo()))
                return x;

        }

        throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "Status da coleta não encontrado");

    }
}
