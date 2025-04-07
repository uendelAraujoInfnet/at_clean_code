package com.uendel.tests;

import com.uendel.domain.Entrega;
import com.uendel.domain.TipoFrete;
import com.uendel.domain.frete.CalculadoraFrete;
import com.uendel.domain.frete.FreteEconomico;
import com.uendel.domain.frete.FreteExpresso;
import com.uendel.domain.frete.FretePadrao;
import com.uendel.exception.FreteInvalidoException;
import com.uendel.service.EtiquetaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EtiquetaServiceTest {

    private EtiquetaService etiquetaService;

    @BeforeEach
    void setUp() {
        List<CalculadoraFrete> calculadoras = Arrays.asList(
                new FreteExpresso(),
                new FretePadrao(),
                new FreteEconomico()
        );
        etiquetaService = new EtiquetaService(calculadoras);
    }

    @Test
    void deveCalcularFretePadrao() {
        Entrega entrega = new Entrega("Rua D", 5.0, TipoFrete.PAD, "Carlos");
        double valor = etiquetaService.calcularFrete(entrega);
        assertEquals(6.0, valor);
    }

    @Test
    void deveGerarEtiquetaCorretamente() {
        Entrega entrega = new Entrega("Rua E", 4.0, TipoFrete.EXP, "Joana");
        String etiqueta = etiquetaService.gerarEtiqueta(entrega);

        assertTrue(etiqueta.contains("Destinatário: Joana"));
        assertTrue(etiqueta.contains("Endereço: Rua E"));
        assertTrue(etiqueta.contains("Valor do Frete: R$ 16.00"));
    }

    @Test
    void deveGerarResumoCorretamente() {
        Entrega entrega = new Entrega("Rua F", 3.0, TipoFrete.ECO, "Lucas");
        String resumo = etiquetaService.gerarResumoPedido(entrega);

        assertEquals("Pedido para Lucas com frete tipo Econômico no valor de R$ -1.70", resumo);
    }

    @Test
    void deveLancarExcecaoParaFreteNaoSuportado() {
        CalculadoraFrete freteFake = new CalculadoraFrete() {
            @Override
            public boolean aceita(TipoFrete tipoFrete) {
                return false;
            }

            @Override
            public double calcular(Entrega entrega) {
                return 0;
            }
        };

        EtiquetaService serviceComFalha = new EtiquetaService(List.of(freteFake));
        Entrega entrega = new Entrega("Rua G", 2.0, TipoFrete.PAD, "SemFrete");

        assertThrows(FreteInvalidoException.class, () ->
                serviceComFalha.calcularFrete(entrega)
        );
    }
}
