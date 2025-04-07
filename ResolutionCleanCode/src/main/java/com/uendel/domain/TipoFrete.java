package com.uendel.domain;

public enum TipoFrete {

    EXP("Expresso"),
    PAD("Padrão"),
    ECO("Econômico");

    private final String descricao;

    TipoFrete(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
