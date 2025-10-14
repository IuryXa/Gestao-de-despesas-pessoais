package com.example.gerenciadorfinancas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gerenciadorfinancas.modelos.Usuario;

public class OfflineInformacoes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_offline_informacoes);

        EditText nomeOffline = findViewById(R.id.editOfflineNome);
        Button btnEntrarOffline = findViewById(R.id.btnOfflineEntrar);
        Button btnVoltarOffline = findViewById(R.id.btnVoltarOffline);

        btnEntrarOffline.setOnClickListener(view->{
            String nome = nomeOffline.getText().toString();
            if (nome.isEmpty()){
                Toast.makeText(this, "É necessario inserir um nome!", Toast.LENGTH_SHORT).show();
            }else{
                Usuario usuarioAtual = new Usuario();
                usuarioAtual.setNome(nome);
                Intent intent = new Intent(this, Home.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("usuario", usuarioAtual);
                intent.putExtra("dados", bundle);
                startActivity(intent);
            }
        });

        btnVoltarOffline.setOnClickListener(view->{
            finish();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}