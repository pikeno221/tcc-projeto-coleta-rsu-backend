package io.projetocoletarsu.exception;

public class BDException extends ApiException {
    private int code;

    public BDException(int code, String msg) {
        super(code, msg);
        this.code = code;
    }
}
