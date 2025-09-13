package com.example.gerenciadordefinancas.modelos;

public class Custo extends Financa{
    public Custo(int id, float valor, String categoria, String dataHora, Boolean fixo, int idUsuario)
    {
        this.setId(id);
        this.setValor(valor);
        this.setCategoria(categoria);
        this.setDataHora(dataHora);
        this.setFixo(fixo);
        this.setIdUsuario(idUsuario);
    }
}


