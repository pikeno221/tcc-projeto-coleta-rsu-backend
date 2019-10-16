package io.projetocoletarsu.model.enums;

import io.projetocoletarsu.exception.NotFoundException;
import org.springframework.http.HttpStatus;

public enum StatusUsuario {

    PENDENTE_APROVACAO(1, "Pendente Aprovacao"), ATIVO(2, "Ativo"), INATVO(3, "Inativo"), NEGADO(4, "NEGADO");

    private Integer codigo;
    private String descricao;

    private StatusUsuario(Integer codigo, String descricao) {

    }

    public Integer getCodigo() {
        return this.codigo;

    }

    public String getDescricao() {
        return this.descricao;

    }

    public static StatusUsuario toEnum(Integer codigo) throws NotFoundException {

        if (codigo == null)
            return null;

        for (StatusUsuario x : StatusUsuario.values()) {
            if (codigo.equals(x.getCodigo()))
                return x;

        }

        throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "Estado Pagamento nao encontrado");

    }
}
