package com.uendel.domain;

import com.uendel.exception.DadosInvalidosException;
import com.uendel.exception.FreteInvalidoException;
import com.uendel.exception.PesoInvalidoException;

public class Entrega {

    private final String endereco;
    private final double peso;
    private final TipoFrete tipoFrete;
    private final String destinatario;

    public Entrega(String endereco, double peso, TipoFrete tipoFrete, String destinatario) {

        if (endereco == null || endereco.isBlank()){
            throw new DadosInvalidosException("endereço");
        }
        if (peso <= 0){
            throw new PesoInvalidoException(peso);
        }
        if(tipoFrete == null){
            throw new FreteInvalidoException((null));
        }
        if (destinatario == null || destinatario.isBlank()){
            throw new DadosInvalidosException("destinatário");
        }

        this.endereco = endereco;
        this.peso = peso;
        this.tipoFrete = tipoFrete;
        this.destinatario = destinatario;
    }

    public String getEndereco() {
        return endereco;
    }

    public double getPeso() {
        return peso;
    }

    public TipoFrete getTipoFrete() {
        return tipoFrete;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public boolean isFreteGratis(){
        return tipoFrete == TipoFrete.ECO && peso < 2;
    }

    public Entrega aplicarFretePromocional(){
        double novoPeso = peso > 10 ? peso - 1 : peso;
        return new Entrega(endereco, novoPeso, tipoFrete, destinatario);
    }
}
