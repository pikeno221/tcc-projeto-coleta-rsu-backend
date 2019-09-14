package io.projetocoletarsu.exception;

public class PersistirDadosException extends ApiException {
    private int code;

    public PersistirDadosException(int code, String msg) {
        super(code, msg);
        this.code = code;
    }
}
