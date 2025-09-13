package com.example.gerenciadordefinancas.modelos;

import java.util.ArrayList;
import java.util.List;

public class Investimento {
    private int id;
    private int idUsuario;
    private String nome;
    private float valorInicial;
    private float valorAtual;
    private ArrayList<Float>historicoValores;
    private float diferenca;

    public Investimento(int idUsuario, String nome, float valorInicial)
    {
        this.idUsuario=idUsuario;
        this.nome=nome;
        this.valorInicial= valorInicial;
        this.valorAtual = valorInicial;
        this.historicoValores = new ArrayList<>();
        this.historicoValores.add(valorInicial);
        this.diferenca = ((valorAtual/valorInicial)-100)*100;
    }

    public void calcularNovaDiferenca(float novoValor)
    {
        this.valorAtual = novoValor;
        this.diferenca= ((valorAtual/valorInicial)-100)*100;
        this.historicoValores.add(valorAtual);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public float getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(float valorInicial) {
        this.valorInicial = valorInicial;
    }

    public float getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(float valorAtual) {
        this.valorAtual = valorAtual;
    }

    public ArrayList<Float> getHistoricoValores() {
        return historicoValores;
    }

    public void setHistoricoValores(ArrayList<Float> historicoValores) {
        this.historicoValores = historicoValores;
    }

    public float getDiferenca() {
        return diferenca;
    }

    public void setDiferenca(float diferenca) {
        this.diferenca = diferenca;
    }
}
