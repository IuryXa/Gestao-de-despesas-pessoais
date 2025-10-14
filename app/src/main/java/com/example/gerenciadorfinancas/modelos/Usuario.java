package com.example.gerenciadorfinancas.modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable {
    private String nome;
    private float saldoAtual;
    private List<Financas> financas;

    public Usuario(){
        this.financas = new ArrayList<>();
        this.saldoAtual = 0;
    }

    public void setFinanca(Financas financa){
        this.financas.add(financa);
        if (financa.getTipo() == 0){
            saldoAtual += financa.getValor();
        } else if (financa.getTipo() == 1) {
            saldoAtual -= financa.getValor();
        }
    }

    public Financas getFinanca(int index){
        return this.financas.get(index);
    }

    public float getSaldoAtual() {
        return saldoAtual;
    }

    public void setSaldoAtual(float saldoAtual) {
        this.saldoAtual = saldoAtual;
    }

    public List<Financas> getFinancas() {
        return financas;
    }

    public void setFinancas(List<Financas> financas) {
        this.financas = financas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
