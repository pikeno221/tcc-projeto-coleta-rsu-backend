package io.projetocoletarsu.model.enums;

import io.projetocoletarsu.exception.NotFoundException;
import org.springframework.http.HttpStatus;

public enum MaterialColeta {

    PLASTICO(1, "Plástico"), VIDRO(2, "Vidro"), PAPEL(3, "Papel"), ALUMINIO(4, "Alumínio"), ORGANICOS(5, "Organicos");

    private Integer codigo;
    private String descricao;

    private MaterialColeta(Integer codigo, String descricao) {

    }

    public Integer getCodigo() {
        return this.codigo;

    }

    public String getDescricao() {
        return this.descricao;

    }

    public static MaterialColeta toEnum(Integer codigo) throws NotFoundException {

        if (codigo == null)
            return null;

        for (MaterialColeta x : MaterialColeta.values()) {
            if (codigo.equals(x.getCodigo()))
                return x;

        }

        throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "Material coleta não encontrado");

    }
}
