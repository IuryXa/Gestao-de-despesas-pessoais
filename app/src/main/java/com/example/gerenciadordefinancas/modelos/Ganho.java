package com.example.gerenciadordefinancas.modelos;

public class Ganho extends Financa{
    public Ganho(int id, float valor, String categoria, String dataHora, Boolean fixo, int idUsuario)
    {
        this.setId(id);
        this.setValor(valor);
        this.setCategoria(categoria);
        this.setDataHora(dataHora);
        this.setFixo(fixo);
        this.setIdUsuario(idUsuario);
    }
}


