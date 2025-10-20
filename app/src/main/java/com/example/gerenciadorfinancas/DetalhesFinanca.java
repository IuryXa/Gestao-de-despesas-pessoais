package com.example.gerenciadorfinancas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gerenciadorfinancas.modelos.Financas;
import com.example.gerenciadorfinancas.modelos.Usuario;

public class DetalhesFinanca extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detalhes_financa);

        Bundle bundle = getIntent().getExtras().getBundle("dados");
        Usuario usuario = (Usuario) bundle.getSerializable("usuario");
        int tipo = getIntent().getExtras().getInt("financa");

        TextView nomeFinanca = findViewById(R.id.textNomeFinancaDF);
        TextView tipoFinanca = findViewById(R.id.textTipoDF);
        TextView dataFinanca = findViewById(R.id.textDataDF);
        TextView valorFinanca = findViewById(R.id.textValorDF);
        Button btnExcluir = findViewById(R.id.btnExcluirFinancaDF);
        Button btnvoltar = findViewById(R.id.btnVoltarDetalheFinanca);

        Financas financaAtual = usuario.getFinanca(tipo);

        nomeFinanca.setText(financaAtual.getNome());
        tipoFinanca.setText(financaAtual.getCategoria());
        dataFinanca.setText(financaAtual.getData());
        valorFinanca.setText(""+financaAtual.getValor());

        Intent intent = new Intent(this, Saldo.class);
        Bundle bundle1 = new Bundle();

        btnExcluir.setOnClickListener(view->{
            usuario.exluirFinanca(financaAtual.getId());
            bundle1.putSerializable("usuario", usuario);
            intent.putExtra("dados", bundle1);
            startActivity(intent);
            finish();
        });

        btnvoltar.setOnClickListener(view->{
            bundle1.putSerializable("usuario", usuario);
            intent.putExtra("dados", bundle1);
            startActivity(intent);
            finish();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}