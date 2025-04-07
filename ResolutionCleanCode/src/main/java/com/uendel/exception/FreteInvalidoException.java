package com.uendel.exception;

import com.uendel.domain.TipoFrete;

public class FreteInvalidoException extends RuntimeException{

    public FreteInvalidoException(TipoFrete tipoFrete) {
        super("Tipo de frete não suportado: " + tipoFrete);
    }
}
