package com.example.gerenciadorfinancas.modelos;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.gerenciadorfinancas.R;

import java.util.List;

public class InvestimentoAdapter extends ArrayAdapter<Investimento> {

    public InvestimentoAdapter(Context context, List<Investimento> investimentoList){
        super(context, 0, investimentoList);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_investimento, parent, false);
        }

        Investimento investimento = getItem(position);

        TextView nomeInvestimento = convertView.findViewById(R.id.investimento_nome);
        TextView categoriaInvestimento = convertView.findViewById(R.id.investimento_categoria);
        TextView valorInvestimento = convertView.findViewById(R.id.investimento_valor);
        TextView porcentagemInvestimento = convertView.findViewById(R.id.investimento_porcentagem);

        if (investimento.getValores().get(investimento.getValores().size() -1).getPorcentagem() < 0){
            porcentagemInvestimento.setTextColor(Color.RED);
        } else if (investimento.getValores().get(investimento.getValores().size() -1).getPorcentagem() > 0) {
            porcentagemInvestimento.setTextColor(Color.GREEN);
        }

        nomeInvestimento.setText("Nome: "+investimento.getNome());
        categoriaInvestimento.setText("Categoria: "+investimento.getCategoria());
        valorInvestimento.setText("Valor Atual: R$"+investimento.getValorAtual());
        porcentagemInvestimento.setText("Variação: "+investimento.getValores().get(investimento.getValores().size() -1).getPorcentagem()+"%");

        return convertView;
    }
}
