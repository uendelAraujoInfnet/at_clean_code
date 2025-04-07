package com.uendel.tests;

import com.uendel.domain.Entrega;
import com.uendel.domain.TipoFrete;
import com.uendel.domain.frete.CalculadoraFrete;
import com.uendel.domain.frete.FretePadrao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraFreteTest {

    @Test
    void implementacaoDeveCalcularSemErro() {
        CalculadoraFrete frete = new FretePadrao();
        Entrega entrega = new Entrega("Rua H", 10, TipoFrete.PAD, "Cliente");

        double valor = frete.calcular(entrega);

        assertTrue(valor > 0, "O valor do frete deve ser positivo");
    }

    @Test
    void implementacaoDeveAceitarTipoEsperado() {
        CalculadoraFrete frete = new FretePadrao();

        assertTrue(frete.aceita(TipoFrete.PAD));
        assertFalse(frete.aceita(TipoFrete.ECO));
    }
}