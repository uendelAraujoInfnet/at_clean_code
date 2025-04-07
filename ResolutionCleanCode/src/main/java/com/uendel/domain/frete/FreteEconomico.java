package com.uendel.domain.frete;

import com.uendel.domain.Entrega;
import com.uendel.domain.TipoFrete;

public class FreteEconomico implements CalculadoraFrete{

    @Override
    public boolean aceita(TipoFrete tipoFrete){
        return tipoFrete == TipoFrete.ECO;
    }

    @Override
    public double calcular(Entrega entrega) {
        return entrega.getPeso() * 1.1 - 5;
    }
}
