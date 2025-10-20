package com.example.gerenciadorfinancas.modelos;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Financas implements Serializable {

    private static int idGeral = 0;
    private int id;
    private String nome;
    private String categoria;
    private String data;
    private float valor;
    private int tipo;

    public Financas(String nome, String categoria, float valor, int tipo, String data){
        id = idGeral;
        idGeral++;
        this.data = data;
        this.nome = nome;
        this.categoria = categoria;
        this.valor = valor;
        this.tipo = tipo;
    }

    public String getMes(){
        try {
            // Define the date format (dd/MM/yyyy)
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

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
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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
