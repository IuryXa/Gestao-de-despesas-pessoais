package com.example.gerenciadorfinancas.modelos;

import java.io.Serializable;

public class MiniInvestimento implements Serializable {

    private String data;
    private float valor;
    private float porcentagem;

    public MiniInvestimento(String data, float valor, float valorAnterior){
        this.data = data;
        this.valor = valor;
        this.porcentagem = (valor/valorAnterior*100)-100;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public float getPorcentagem() {
        return porcentagem;
    }

    public void setPorcentagem(float porcentagem) {
        this.porcentagem = porcentagem;
    }
}
