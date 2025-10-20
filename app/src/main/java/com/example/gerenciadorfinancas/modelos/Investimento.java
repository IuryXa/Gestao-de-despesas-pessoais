package com.example.gerenciadorfinancas.modelos;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Investimento implements Serializable {
    private static int idGeral = 0;
    private int id;
    private String nome;
    private String categoria;
    private float valorInicial;
    private float valorAtual;
    private List<MiniInvestimento> valores;

    public static int getIdGeral() {
        return idGeral;
    }

    public static void setIdGeral(int idGeral) {
        Investimento.idGeral = idGeral;
    }

    public float getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(float valorInicial) {
        this.valorInicial = valorInicial;
    }

    public Investimento(String nome, String categoria, float valorInicial, String data){
        this.id = idGeral;
        idGeral++;
        this.nome = nome;
        this.categoria = categoria;
        this.valorInicial =valorInicial;
        this.valorAtual = valorInicial;
        this.valores = new ArrayList<>();
        MiniInvestimento investimento = new MiniInvestimento(data, valorInicial,valorInicial);
        this.valores.add(investimento);
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public float getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(float valorAtual) {
        this.valorAtual = valorAtual;
        MiniInvestimento investimento = new MiniInvestimento(getTodayDate(), valorAtual, valorInicial);
        this.valores.add(investimento);
    }

    public List<MiniInvestimento> getValores() {
        return valores;
    }

    public void setValores(List<MiniInvestimento> valores) {
        this.valores = valores;
    }


    public String getTodayDate() {
        // Get current date
        Calendar calendar = Calendar.getInstance();

        // Format it to "dd/MM/yyyy"
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = dateFormat.format(calendar.getTime());

        return formattedDate;
    }
}
