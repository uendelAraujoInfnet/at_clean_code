package com.uendel.tests;

import com.uendel.domain.Entrega;
import com.uendel.domain.TipoFrete;
import com.uendel.domain.frete.FreteExpresso;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FreteExpressoTest {

    @Test
    void deveAceitarTipoExpresso() {
        FreteExpresso frete = new FreteExpresso();
        assertTrue(frete.aceita(TipoFrete.EXP));
    }

    @Test
    void deveCalcularFreteExpressoCorretamente() {
        FreteExpresso frete = new FreteExpresso();
        Entrega entrega = new Entrega("Rua B", 8.0, TipoFrete.EXP, "Maria");
        assertEquals(22.0, frete.calcular(entrega));
    }
}
