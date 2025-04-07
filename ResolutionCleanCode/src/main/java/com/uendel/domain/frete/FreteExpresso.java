package com.uendel.domain.frete;

import com.uendel.domain.Entrega;
import com.uendel.domain.TipoFrete;

public class FreteExpresso implements CalculadoraFrete {

    @Override
    public boolean aceita(TipoFrete tipoFrete) {
        return tipoFrete == TipoFrete.EXP;
    }

    @Override
    public double calcular(Entrega entrega) {
        return entrega.getPeso() * 1.5 + 10;
    }
}
