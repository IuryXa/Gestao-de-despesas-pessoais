package com.example.gerenciadordefinancas.modelos;

import java.util.ArrayList;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private ArrayList<Investimento> investimentos;

    public Usuario(String nome, String email, String senha){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.investimentos = new ArrayList<>();
    }

    public void setInvestimento(Investimento investimento)
    {
        this.investimentos.add(investimento);
    }

    public ArrayList<Investimento> getInvestimentos() {
        return investimentos;
    }

    public void setInvestimentos(ArrayList<Investimento> investimentos) {
        this.investimentos = investimentos;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
