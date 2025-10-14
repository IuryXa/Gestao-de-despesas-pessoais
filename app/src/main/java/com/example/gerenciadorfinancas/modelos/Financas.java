package com.example.gerenciadorfinancas.modelos;

public class Financas {

    private static int idGeral = 0;
    private int id;
    private String nome;
    private String categoria;
    private float valor;
    private int tipo;

    public Financas(String nome, String categoria, float valor, int tipo){
        id = idGeral;
        idGeral++;
        this.nome = nome;
        this.categoria = categoria;
        this.valor = valor;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getIdGeral() {
        return idGeral;
    }

    public static void setIdGeral(int id) {
        Financas.idGeral = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
