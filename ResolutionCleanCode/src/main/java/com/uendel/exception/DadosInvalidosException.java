package com.uendel.exception;

public class DadosInvalidosException extends RuntimeException{

    public DadosInvalidosException(String campo) {
        super("O campo '" + campo + "' é obrigatório e não pode estar vazio.");
    }
}
