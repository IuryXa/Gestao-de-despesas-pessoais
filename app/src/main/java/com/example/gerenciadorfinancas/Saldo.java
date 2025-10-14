package com.example.gerenciadorfinancas;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gerenciadorfinancas.modelos.Financas;
import com.example.gerenciadorfinancas.modelos.FinancasAdapter;
import com.example.gerenciadorfinancas.modelos.Usuario;

public class Saldo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_saldo);

        Bundle bundle = getIntent().getExtras().getBundle("dados");
        Usuario usuario = (Usuario) bundle.getSerializable("usuario");

        TextView saldoAtual = findViewById(R.id.saldoValorSaldo);
        ListView listFinancas = findViewById(R.id.listFinancas);
        Button btnVoltar = findViewById(R.id.btnVoltarSaldo);

        saldoAtual.setText("R$"+usuario.getSaldoAtual());

// Create an adapter for the ListView
        FinancasAdapter adapter = new FinancasAdapter(this, usuario.getFinancas());
        listFinancas.setAdapter(adapter);

        // Set an OnClickListener for each item in the ListView
        listFinancas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Financas selectedFinanca = usuario.getFinancas().get(position);
                // You can perform actions on the selected item
                Toast.makeText(Saldo.this, "Selected: " + selectedFinanca.getNome(), Toast.LENGTH_SHORT).show();
            }
        });

        btnVoltar.setOnClickListener(view->{
            //Expandir
            finish();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}