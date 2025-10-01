package com.example.gerenciadordefinancas;

import android.os.Bundle;
import android.telecom.Call;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
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
            EditText nome = findViewById(R.id.inputNome);
            String nomeText = nome.getText().toString();
            EditText email = findViewById(R.id.inputEmail);
            String emailText = email.getText().toString();
            EditText senha = findViewById(R.id.inputSenha);
            String senhaText = senha.getText().toString();
            Button cadastroBtn = findViewById(R.id.btnFazerCadastro);

            if(nomeText.isEmpty() || emailText.isEmpty() || senhaText.isEmpty()){
                Toast.makeText(this, "Você deve preencher todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            String emailCript = Criptografia.Criptografar(emailText, senhaText);
            String senhaCript = Criptografia.Criptografar(senhaText, emailCript);
            String nomeCript = Criptografia.Criptografar(nomeText, emailCript);

            Usuario user = new Usuario(nomeCript,senhaCript,emailCript);

            Call<Void> call = ApiService.addUser(user);
            cadastroBtn.setText(getResources().getString(R.string.carregarBtn));
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        cadastroBtn.setText(getResources().getString(R.string.botao_cadastro));
                        Toast.makeText(MainActivity.this, "Usuario Cadastrado!", Toast.LENGTH_SHORT).show();
                        usuarioAtual = new usuario(nomeCript,senhaCript,emailCript,telefoneCript);

                        sideBarTesteNome.setText(Criptografia.Descriptografar(usuarioAtual.getNome(), usuarioAtual.getEmail()));
                        cadastroLayout.setVisibility(View.GONE);
                        aparecerLayout();
                    } else {
                        cadastroBtn.setText(getResources().getString(R.string.botao_cadastro));
                        //Loga o erro para mais detalhes
                        try {
                            String errorMessage = response.errorBody() != null ? response.errorBody().string() : "Erro desconhecido";
                            Log.e("API_ERROR", "Erro ao adicionar usuario: " + errorMessage);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Toast.makeText(MainActivity.this, "Erro ao adicionar usuario", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    cadastroBtn.setText(getResources().getString(R.string.botao_cadastro));
                    Toast.makeText(MainActivity.this, "Erro: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}