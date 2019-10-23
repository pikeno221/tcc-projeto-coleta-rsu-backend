package io.projetocoletarsu.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.projetocoletarsu.exception.NotFoundException;
import org.springframework.http.HttpStatus;

public enum StatusColeta {


    AGUARDANDO_CONFICAMACAO(1, "AGUARDANDO_CONFICAMACAO"), AGENDAMENTO_CONFIRMADO(2, "AGENDAMENTO_CONFIRMADO"), CANCELADO(3, "CANCELADO"), CONCLUIDO(4, "CONCLUIDO");

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

    public StatusColeta toEnum(Integer codigo) throws NotFoundException {

        if (codigo == null)
            return null;

        for (StatusColeta x : StatusColeta.values()) {
            if (codigo.equals(x.getCodigo()))
                return x;

        }

        throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "Status da coleta não encontrado");

    }


    public static StatusColeta fromText(String text){
        for(StatusColeta s : StatusColeta.values()){
            if(s.getDescricao().equals(text)){
                return s;
            }
        }

        throw new IllegalArgumentException();
    }



}
