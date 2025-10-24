package com.example.gerenciadorfinancas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gerenciadorfinancas.modelos.Investimento;
import com.example.gerenciadorfinancas.modelos.Usuario;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AdicionarInvestimento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adicionar_investimento);

        Bundle bundle = getIntent().getExtras().getBundle("dados");
        Usuario usuario = (Usuario) bundle.getSerializable("usuario");

        EditText nomeInvestimento = findViewById(R.id.editNomeInvestimento);
        EditText dataInvestimento = findViewById(R.id.editDataInvestimento);
        EditText categoriaInvestimento = findViewById(R.id.editCategoriaInvestimento);
        EditText valorInvestimento = findViewById(R.id.editValorInvestimento);
        Button btnAdicionarInvestimento = findViewById(R.id.btnSalvarInvestimento);
        Button btnVoltarInvestimento = findViewById(R.id.btnVoltarInvestimento);

        dataInvestimento.setText(getTodayDate());

        btnAdicionarInvestimento.setOnClickListener(view->{
            String nome = nomeInvestimento.getText().toString();
            String data = dataInvestimento.getText().toString();
            String categoria = categoriaInvestimento.getText().toString();
            float valor = Float.parseFloat(valorInvestimento.getText().toString());

            Investimento investimento = new Investimento(nome,categoria,valor,data);
            usuario.getInvestimentos().add(investimento);
            Intent intent = new Intent(this, Investimentos.class);
            Bundle bundle1 = new Bundle();
            bundle1.putSerializable("usuario", usuario); // Correctly add usuario
            intent.putExtra("dados", bundle1); // Correctly pass bundle1
            startActivity(intent);
        });

        btnVoltarInvestimento.setOnClickListener(view->{
            Intent intent = new Intent(this, Investimentos.class);
            Bundle bundle1 = new Bundle();
            bundle1.putSerializable("usuario", usuario); // Correctly add usuario
            intent.putExtra("dados", bundle1); // Correctly pass bundle1
            startActivity(intent);
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

    public String getMes(String data){
        try {
            // Define the date format (dd/MM/yyyy)
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

            // Parse the input string into a Date object
            Date date = dateFormat.parse(data);

            // Create a Calendar instance to extract month and year
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            // Extract the month (Note: months are 0-indexed in Java, so we add 1)
            int month = calendar.get(Calendar.MONTH) + 1;

            // Extract the year
            int year = calendar.get(Calendar.YEAR);

            // Store the month and year in separate variables (or use them as needed)
            String monthStr = String.format("%02d", month);


            // Print the results (optional)
            return  monthStr;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String getAno(String data){
        try {
            // Define the date format (dd/MM/yyyy)
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

            // Parse the input string into a Date object
            Date date = dateFormat.parse(data);

            // Create a Calendar instance to extract month and year
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            // Extract the month (Note: months are 0-indexed in Java, so we add 1)
            int month = calendar.get(Calendar.MONTH) + 1;

            // Extract the year
            int year = calendar.get(Calendar.YEAR);

            // Store the month and year in separate variables (or use them as needed)
            String yearStr = String.valueOf(year);
            // Print the results (optional)
            return  yearStr;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}