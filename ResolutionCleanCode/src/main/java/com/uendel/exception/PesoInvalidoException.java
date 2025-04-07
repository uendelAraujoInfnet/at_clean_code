package com.uendel.exception;

public class PesoInvalidoException extends RuntimeException{

    public PesoInvalidoException(double peso){
        super("Peso invalido: " + peso + ". O peso deve ser maior que zero.");
    }
}
