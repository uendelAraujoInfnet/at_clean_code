package com.uendel.service;

import com.uendel.domain.Entrega;
import com.uendel.domain.frete.CalculadoraFrete;
import com.uendel.domain.TipoFrete;
import com.uendel.exception.FreteInvalidoException;

import java.util.List;
import java.util.Locale;

public class EtiquetaService {

    private final List<CalculadoraFrete> calculadoras;

    public EtiquetaService(List<CalculadoraFrete> calculadoras) {
        if (calculadoras == null || calculadoras.isEmpty()) {
            throw new IllegalArgumentException("É necessário fornecer ao menos uma estratégia de frete.");
        }
        this.calculadoras = calculadoras;
    }

    public double calcularFrete(Entrega entrega) {
        CalculadoraFrete calculadora = calculadoras.stream()
                .filter(c -> c.aceita(entrega.getTipoFrete()))
                .findFirst()
                .orElseThrow(() -> new FreteInvalidoException(entrega.getTipoFrete()));

        return calculadora.calcular(entrega);
    }

    public String gerarEtiqueta(Entrega entrega) {
        double valorFrete = calcularFrete(entrega);
        return "Destinatário: " + entrega.getDestinatario() +
                "\nEndereço: " + entrega.getEndereco() +
                "\nValor do Frete: R$ " + String.format(Locale.US, "%.2f", valorFrete);
    }

    public String gerarResumoPedido(Entrega entrega) {
        double valorFrete = calcularFrete(entrega);
        return "Pedido para " + entrega.getDestinatario() +
                " com frete tipo " + entrega.getTipoFrete().getDescricao() +
                " no valor de R$ " + String.format(Locale.US, "%.2f", valorFrete);
    }
}
