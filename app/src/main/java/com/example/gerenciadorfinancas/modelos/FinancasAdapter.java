// FinancasAdapter.java
package com.example.gerenciadorfinancas.modelos;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ArrayAdapter;

import com.example.gerenciadorfinancas.modelos.Financas;
import com.example.gerenciadorfinancas.R;

import java.util.List;

public class FinancasAdapter extends ArrayAdapter<Financas> {

    public FinancasAdapter(Context context, List<Financas> financasList) {
        super(context, 0, financasList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Reuse convertView if possible
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_financa, parent, false);
        }

        // Get the current Financa object
        Financas financa = getItem(position);

        // Set values to the TextViews
        TextView nomeTextView = convertView.findViewById(R.id.financa_nome);
        TextView categoriaTextView = convertView.findViewById(R.id.financa_categoria);
        TextView valorTextView = convertView.findViewById(R.id.financa_valor);

        if (financa.getTipo() == 0){
            valorTextView.setTextColor(Color.GREEN);
        } else if (financa.getTipo() == 1) {
            valorTextView.setTextColor(Color.RED);
        }

        nomeTextView.setText("Nome: "+financa.getNome());
        categoriaTextView.setText("Categoria: "+financa.getCategoria());
        valorTextView.setText("Valor: R$ " + financa.getValor());

        return convertView;
    }
}
