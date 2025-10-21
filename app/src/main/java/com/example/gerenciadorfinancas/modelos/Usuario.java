package com.example.gerenciadorfinancas.modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable {
    private String nome;
    private float saldoAtual;
    private List<Financas> financas;
    private List<Investimento> investimentos;

    public List<Investimento> getInvestimentos() {
        return investimentos;
    }

    public void setInvestimentos(List<Investimento> investimentos) {
        this.investimentos = investimentos;
    }

    public Usuario(){
        this.financas = new ArrayList<>();
        this.investimentos = new ArrayList<>();
        this.saldoAtual = 0;
    }

    public void setInvestimento(Investimento investimento){
        this.investimentos.add(investimento);

    }

    public Investimento getInvestimento(int index){
        return this.investimentos.get(index);
    }

    public void setFinanca(Financas financa){
        this.financas.add(financa);
        if (financa.getTipo() == 0){
            saldoAtual += financa.getValor();
        } else if (financa.getTipo() == 1) {
            saldoAtual -= financa.getValor();
        }
    }

    public void exluirFinanca(int index){

        if (financas.get(index).getTipo() == 0){
            saldoAtual -= financas.get(index).getValor();
        } else if (financas.get(index).getTipo() == 1) {
            saldoAtual += financas.get(index).getValor();
        }

        this.financas.remove(index);
        for(int i = index; i< financas.size();i++){
            financas.get(i).setId(financas.get(i).getId() - 1);
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
