package com.uendel.tests;

import com.uendel.domain.Entrega;
import com.uendel.domain.TipoFrete;
import com.uendel.exception.DadosInvalidosException;
import com.uendel.exception.PesoInvalidoException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EntregaTest {

    @Test
    void deveCriarEntregaValida() {
        Entrega entrega = new Entrega("Rua 1", 5.0, TipoFrete.PAD, "Uendel");
        assertEquals("Uendel", entrega.getDestinatario());
        assertEquals("Rua 1", entrega.getEndereco());
        assertEquals(5.0, entrega.getPeso());
        assertEquals(TipoFrete.PAD, entrega.getTipoFrete());
    }

    @Test
    void naoDevePermitirPesoZeroOuNegativo() {
        assertThrows(PesoInvalidoException.class, () ->
                new Entrega("Rua 2", 0, TipoFrete.EXP, "Carlos")
        );

        assertThrows(PesoInvalidoException.class, () ->
                new Entrega("Rua 2", -5, TipoFrete.ECO, "Carlos")
        );
    }

    @Test
    void naoDevePermitirEnderecoVazio() {
        assertThrows(DadosInvalidosException.class, () ->
                new Entrega("", 2, TipoFrete.PAD, "Carlos")
        );
    }

    @Test
    void naoDevePermitirDestinatarioVazio() {
        assertThrows(DadosInvalidosException.class, () ->
                new Entrega("Rua 3", 2, TipoFrete.PAD, "")
        );
    }

    @Test
    void deveIndicarFreteGratisQuandoEconomicoEAbaixoDeDoisKg() {
        Entrega entrega = new Entrega("Rua 4", 1.9, TipoFrete.ECO, "Ana");
        assertTrue(entrega.isFreteGratis());
    }

    @Test
    void naoDeveIndicarFreteGratisParaPadraoOuPesoMaior() {
        Entrega entrega1 = new Entrega("Rua 5", 3.0, TipoFrete.ECO, "Ana");
        Entrega entrega2 = new Entrega("Rua 5", 1.5, TipoFrete.PAD, "Ana");

        assertFalse(entrega1.isFreteGratis());
        assertFalse(entrega2.isFreteGratis());
    }

    @Test
    void deveAplicarPromocaoParaPesoMaiorQue10() {
        Entrega original = new Entrega("Rua Promo", 12.0, TipoFrete.EXP, "Lucas");
        Entrega comDesconto = original.aplicarFretePromocional();

        assertEquals(11.0, comDesconto.getPeso());
    }

    @Test
    void naoDeveAplicarPromocaoParaPesoMenorOuIgualA10() {
        Entrega original = new Entrega("Rua Promo", 10.0, TipoFrete.EXP, "Lucas");
        Entrega comDesconto = original.aplicarFretePromocional();

        assertEquals(10.0, comDesconto.getPeso());
    }
}
