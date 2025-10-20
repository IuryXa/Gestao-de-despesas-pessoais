package com.example.gerenciadorfinancas;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gerenciadorfinancas.modelos.Usuario;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras().getBundle("dados");

        Usuario usuario = (Usuario) bundle.getSerializable("usuario");

        TextView titulo = findViewById(R.id.tituloHome);

        titulo.setText("Olá, "+ usuario.getNome());

        TextView saldo = findViewById(R.id.textSaldoAtual);

        if (usuario.getSaldoAtual() <0){
            saldo.setTextColor(Color.RED);
        } else if (usuario.getSaldoAtual() >0) {
            saldo.setTextColor(Color.GREEN);
        }

        saldo.setText("R$"+usuario.getSaldoAtual());

        ConstraintLayout saldoCard = findViewById(R.id.cardSaldo);
        ConstraintLayout investimentoCard = findViewById(R.id.cardInvestimento);
        Button btnAdicionarGanho = findViewById(R.id.btnGanhoHome);
        Button btnAdicionarDespesa = findViewById(R.id.btnDespesaHome);

        btnAdicionarGanho.setOnClickListener(view->{
            Intent intent2 = new Intent(this, InserirFinanca.class);
            Bundle bundle2 = new Bundle();
            bundle2.putSerializable("usuario", usuario);
            intent2.putExtra("dados",bundle2);
            intent2.putExtra("tipo","ganho");
            startActivity(intent2);
            finish();
        });

        btnAdicionarDespesa.setOnClickListener(view->{
            Intent intent2 = new Intent(this, InserirFinanca.class);
            Bundle bundle2 = new Bundle();
            bundle2.putSerializable("usuario", usuario);
            intent2.putExtra("dados",bundle2);
            intent2.putExtra("tipo","despesa");
            startActivity(intent2);
            finish();
        });

        saldoCard.setOnClickListener(view->{
            Intent intent2 = new Intent(this, Saldo.class);
            Bundle bundle2 = new Bundle();
            bundle2.putSerializable("usuario", usuario);
            intent2.putExtra("dados", bundle2);
            startActivity(intent2);
            finish();
        });

        investimentoCard.setOnClickListener(view->{
            Intent intent2 = new Intent(this, Investimentos.class);
            Bundle bundle2 = new Bundle();
            bundle2.putSerializable("usuario", usuario);
            intent2.putExtra("dados",bundle2);
            startActivity(intent2);
            finish();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}