package com.example.gerenciadorfinancas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gerenciadorfinancas.modelos.Usuario;

public class AdicionarNovoValor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adicionar_novo_valor);

        Bundle bundle = getIntent().getExtras().getBundle("dados");
        Usuario usuario = (Usuario) bundle.getSerializable("usuario");
        int index = getIntent().getExtras().getInt("investimento");

        TextView titulo = findViewById(R.id.textNomeDIAdd);
        EditText valor = findViewById(R.id.editValorNovoAdd);
        EditText data = findViewById(R.id.editDataNovoAdd);
        Button btnAdiconarValor = findViewById(R.id.btnAdicionarNovoValorDIAdd);

        titulo.setText(usuario.getInvestimento(index).getNome());
        data.setText(usuario.getInvestimento(index).getTodayDate());

        btnAdiconarValor.setOnClickListener(view->{
            float valorAtual = Float.parseFloat(valor.getText().toString());
            usuario.getInvestimento(index).setValorAtual(valorAtual);
            Bundle bundle1 = new Bundle();
            bundle1.putSerializable("usuario", usuario);
            Intent intent = new Intent(this, DetalhesInvestimento.class);
            intent.putExtra("dados", bundle1);
            intent.putExtra("investimento", index);
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