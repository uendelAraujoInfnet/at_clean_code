package com.uendel.tests;

import com.uendel.domain.Entrega;
import com.uendel.domain.TipoFrete;
import com.uendel.domain.frete.FreteEconomico;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FreteEconomicoTest {

    @Test
    void deveAceitarTipoEconomico() {
        FreteEconomico frete = new FreteEconomico();
        assertTrue(frete.aceita(TipoFrete.ECO));
    }

    @Test
    void deveCalcularFreteEconomicoCorretamente() {
        FreteEconomico frete = new FreteEconomico();
        Entrega entrega = new Entrega("Rua C", 5.0, TipoFrete.ECO, "Pedro");
        assertEquals(0.5, frete.calcular(entrega));
    }
}
