package com.uendel.domain.frete;

import com.uendel.domain.Entrega;
import com.uendel.domain.TipoFrete;

public interface CalculadoraFrete {

    boolean aceita(TipoFrete tipoFrete);
    double calcular(Entrega entrega);
}
