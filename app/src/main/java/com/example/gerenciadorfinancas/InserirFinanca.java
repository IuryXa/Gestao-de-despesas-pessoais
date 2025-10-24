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

import com.example.gerenciadorfinancas.modelos.Financas;
import com.example.gerenciadorfinancas.modelos.Usuario;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class InserirFinanca extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inserir_financa);

        Bundle bundle = getIntent().getExtras().getBundle("dados");
        Usuario usuario = (Usuario) bundle.getSerializable("usuario");

        TextView titulo = findViewById(R.id.textFinancaPage);
        EditText nomeFinanca = findViewById(R.id.editNomeFinanca);
        EditText categoriaFinanca = findViewById(R.id.editCategoriaFinanca);
        EditText valorFinanca = findViewById(R.id.editValorFinanca);
        EditText dataFinanca = findViewById(R.id.editDataFinanca);
        Button btnSalver = findViewById(R.id.btnSalvarFinanca);
        Button btnVoltar = findViewById(R.id.btnVoltarFinanca);

        String tipo = getIntent().getExtras().get("tipo").toString();

        dataFinanca.setText(getTodayDate());

        if (tipo.equals("ganho"))
        {
            titulo.setText("Insira o ganho desejado");
            nomeFinanca.setHint("Digite o nome do ganho:");
            categoriaFinanca.setHint("Digite a categoria do ganho:");
            valorFinanca.setHint("Digite o valor do ganho:");
            btnSalver.setOnClickListener(view->{
                String nome = nomeFinanca.getText().toString();
                String categoria = categoriaFinanca.getText().toString();
                float valor = Float.parseFloat(valorFinanca.getText().toString());
                String data = dataFinanca.getText().toString();

                Financas financa = new Financas(nome, categoria,valor, 0, data);
                usuario.setFinanca(financa);

                Intent intent2 = new Intent(this, Home.class);
                Bundle bundle2 = new Bundle();
                bundle2.putSerializable("usuario", usuario);
                intent2.putExtra("dados", bundle2);
                startActivity(intent2);
                finish();

            });
        }else {
            titulo.setText("Insira a despesa desejada");
            nomeFinanca.setHint("Digite o nome da despesa:");
            categoriaFinanca.setHint("Digite a categoria da despesa:");
            valorFinanca.setHint("Digite o valor da despesa:");
            btnSalver.setOnClickListener(view->{
                String nome = nomeFinanca.getText().toString();
                String categoria = categoriaFinanca.getText().toString();
                float valor = Float.parseFloat(valorFinanca.getText().toString());
                String data = dataFinanca.getText().toString();

                Financas financa = new Financas(nome, categoria, valor, 1, data);
                usuario.setFinanca(financa);
                Intent intent2 = new Intent(this, Home.class);
                Bundle bundle2 = new Bundle();
                bundle2.putSerializable("usuario", usuario);
                intent2.putExtra("dados", bundle2);
                startActivity(intent2);
                finish();
            });
        }

        btnVoltar.setOnClickListener(view->{
            Intent intent2 = new Intent(this, Home.class);
            Bundle bundle2 = new Bundle();
            bundle2.putSerializable("usuario", usuario);
            intent2.putExtra("dados", bundle2);
            startActivity(intent2);
            finish();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public String getTodayDate() {
        // Get current date
        Calendar calendar = Calendar.getInstance();

        // Format it to "dd/MM/yyyy"
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String formattedDate = dateFormat.format(calendar.getTime());

        return formattedDate;
    }
}