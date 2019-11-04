package io.projetocoletarsu.model.enums;

import io.projetocoletarsu.exception.NotFoundException;
import org.springframework.http.HttpStatus;

public enum TipoPessoa {

    PESSOA_FISICA(1, "Pessoa Física"), PESSOA_JURIDICA(2, "PESSOA_JURIDICA");

    private Integer codigo;
    private String descricao;

    private TipoPessoa(Integer codigo, String descricao) {

    }

    public Integer getCodigo() {
        return this.codigo;

    }

    public String getDescricao() {
        return this.descricao;

    }

    public static TipoPessoa toEnum(Integer codigo) throws NotFoundException {

        if (codigo == null)
            return null;

        for (TipoPessoa x : TipoPessoa.values()) {
            if (codigo.equals(x.getCodigo()))
                return x;

        }

        throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "Estado Pagamento nao encontrado");

    }
}
