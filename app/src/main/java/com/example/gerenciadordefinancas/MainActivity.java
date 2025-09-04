package com.example.gerenciadordefinancas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnCadastro = findViewById(R.id.btnCadastro);
        Button btnOffline = findViewById(R.id.btnOffline);

        btnLogin.setOnClickListener(view ->{
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        });

        btnCadastro.setOnClickListener(view ->{
            Intent intent = new Intent(this, Cadastro.class);
            startActivity(intent);
        });

        btnOffline.setOnClickListener(view->{
            Intent intent = new Intent(this, Principal.class);
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}