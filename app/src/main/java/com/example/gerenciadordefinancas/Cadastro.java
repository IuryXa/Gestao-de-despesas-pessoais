package com.example.gerenciadordefinancas;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gerenciadordefinancas.modelos.Usuario;
import com.example.gerenciadordefinancas.network.Criptografia;

public class Cadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro);

        EditText inputNome = findViewById(R.id.inputNome);
        EditText inputEmail = findViewById(R.id.inputEmail);
        EditText inputSenha = findViewById(R.id.inputSenha);
        Button btnCadastro = findViewById(R.id.btnCadastro);

        btnCadastro.setOnClickListener(view->{
            String nome = inputNome.getText().toString();
            String email = inputEmail.getText().toString();
            String senha = inputSenha.getText().toString();

            String emailCripto = Criptografia.Criptografar(email, nome);
            String nomeCripto = Criptografia.Criptografar(nome, email);
            String senhaCripto = Criptografia.Criptografar(senha, nome);

            Usuario usuarioCripto = new Usuario(nomeCripto,emailCripto, senhaCripto);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}