package com.uendel.tests;

import com.uendel.domain.Entrega;
import com.uendel.domain.TipoFrete;
import com.uendel.domain.frete.FretePadrao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FretePadraoTest {

    @Test
    void deveAceitarTipoPadrao() {
        FretePadrao frete = new FretePadrao();
        assertTrue(frete.aceita(TipoFrete.PAD));
    }

    @Test
    void deveCalcularFretePadraoCorretamente() {
        FretePadrao frete = new FretePadrao();
        Entrega entrega = new Entrega("Rua A", 10.0, TipoFrete.PAD, "João");
        assertEquals(12.0, frete.calcular(entrega));
    }
}
