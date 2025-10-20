package com.example.gerenciadorfinancas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
        EditText editAno = findViewById(R.id.editAno);
        EditText editMes = findViewById(R.id.editMes);
        Button btnVoltar = findViewById(R.id.btnVoltarSaldo);
        Button btnMudarData = findViewById(R.id.btnMudarData);

        String anoEscolhido = editAno.getText().toString();
        String mesEscolhido = editMes.getText().toString();
        List<Financas> financas= usuario.getFinancas();
        List<Financas> financasEscolhidas = new ArrayList<>();

        for (int i = 0; i<financas.size();i++){
            String anoFinanca = financas.get(i).getAno();
            String mesFinanca = financas.get(i).getMes();
            if (anoFinanca.equals(anoEscolhido) && mesFinanca.equals(mesEscolhido)){
                financasEscolhidas.add(financas.get(i));
            }
        }

        String hoje = getTodayDate();

        saldoAtual.setText("R$"+usuario.getSaldoAtual());
        editAno.setText(getAno(hoje));
        editMes.setText(getMes(hoje));

// Create an adapter for the ListView
        FinancasAdapter adapter = new FinancasAdapter(this, financasEscolhidas);
        listFinancas.setAdapter(adapter);
        Intent intent = new Intent(this, DetalhesFinanca.class);
        Bundle bundle1 = new Bundle();

        // Set an OnClickListener for each item in the ListView
        listFinancas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Financas selectedFinanca = usuario.getFinancas().get(position);
                // You can perform actions on the selected item
                bundle1.putSerializable("usuario", usuario);
                intent.putExtra("dados", bundle1);
                intent.putExtra("financa", selectedFinanca.getId());
                startActivity(intent);
                finish();
            }
        });

        btnMudarData.setOnClickListener(view->{
            try {
                String anoEscolhido2 = editAno.getText().toString();
                String mesEscolhido2 = editMes.getText().toString();
                List<Financas> financasEscolhidas2 = new ArrayList<>();

                for (int i = 0; i<financas.size();i++){
                    String anoFinanca = financas.get(i).getAno();
                    String mesFinanca = financas.get(i).getMes();
                    if (anoFinanca.equals(anoEscolhido2) && mesFinanca.equals(mesEscolhido2)){
                        financasEscolhidas2.add(financas.get(i));
                    }
                }
                FinancasAdapter adapter2 = new FinancasAdapter(this, financasEscolhidas);
                listFinancas.setAdapter(adapter2);
            } catch (Exception e) {
                throw new RuntimeException(e);
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

    public String getTodayDate() {
        // Get current date
        Calendar calendar = Calendar.getInstance();

        // Format it to "dd/MM/yyyy"
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = dateFormat.format(calendar.getTime());

        return formattedDate;
    }

    public String getMes(String data){
        try {
            // Define the date format (dd/MM/yyyy)
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

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
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

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