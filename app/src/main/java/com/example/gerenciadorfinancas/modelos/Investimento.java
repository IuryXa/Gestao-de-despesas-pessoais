package com.example.gerenciadorfinancas.modelos;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Investimento implements Serializable {
    private static int idGeral = 0;
    private int id;
    private String nome;
    private String categoria;
    private float valorInicial;
    private String data;
    private float valorAtual;
    private float porcentagemAtual;
    private List<MiniInvestimento> valores;

    public float getPorcentagemAtual() {
        return porcentagemAtual;
    }

    public void setPorcentagemAtual(float porcentagemAtual) {
        this.porcentagemAtual = porcentagemAtual;
    }

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
        this.porcentagemAtual = (valorInicial/valorInicial*100)-100;
        this.data = data;
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
        this.porcentagemAtual = (valorAtual/valorInicial*100)-100;
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String formattedDate = dateFormat.format(calendar.getTime());

        return formattedDate;
    }

    public String getMes(){
        try {
            // Define the date format (dd/MM/yyyy)
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

            // Parse the input string into a Date object
            Date date = dateFormat.parse(this.data);

            // Create a Calendar instance to extract month and year
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            // Extract the month (Note: months are 0-indexed in Java, so we add 1)
            int month = calendar.get(Calendar.MONTH) + 1;

            // Extract the year
            int year = calendar.get(Calendar.YEAR);

            // Store the month and year in separate variables (or use them as needed)
            String monthStr = String.format("%02d", month);


            // Print the results (optional)
            return  monthStr;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String getAno(){
        try {
            // Define the date format (dd/MM/yyyy)
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

            // Parse the input string into a Date object
            Date date = dateFormat.parse(this.data);

            // Create a Calendar instance to extract month and year
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            // Extract the month (Note: months are 0-indexed in Java, so we add 1)
            int month = calendar.get(Calendar.MONTH) + 1;

            // Extract the year
            int year = calendar.get(Calendar.YEAR);

            // Store the month and year in separate variables (or use them as needed)
            String yearStr = String.valueOf(year);
            // Print the results (optional)
            return  yearStr;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
