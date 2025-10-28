package com.example.gerenciadorfinancas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gerenciadorfinancas.modelos.Financas;
import com.example.gerenciadorfinancas.modelos.FinancasAdapter;
import com.example.gerenciadorfinancas.modelos.Investimento;
import com.example.gerenciadorfinancas.modelos.InvestimentoAdapter;
import com.example.gerenciadorfinancas.modelos.MiniInvestimento;
import com.example.gerenciadorfinancas.modelos.Usuario;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Investimentos extends AppCompatActivity {

    private LineChart lineChartGeral;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_investimentos);

        Bundle bundle = getIntent().getExtras().getBundle("dados");
        Usuario usuario = (Usuario) bundle.getSerializable("usuario");
        // Initialize the LineChart
        lineChartGeral = findViewById(R.id.lineChartGeral);

        ListView listInvestimento = findViewById(R.id.listInvestimentos);
        EditText ano = findViewById(R.id.editAnoInves);
        EditText mes = findViewById(R.id.editMesInves);
        Button btnMudarData = findViewById(R.id.btnMudarDataInves);

        List<Investimento> investimentos= usuario.getInvestimentos();
        List<Investimento> investimentosEscolhidos = new ArrayList<>();
        String hoje = getTodayDate();
        ano.setText(getAno(hoje));
        mes.setText(getMes(hoje));
        String anoEscolhido = ano.getText().toString();
        String mesEscolhido = mes.getText().toString();

        for (int i = 0; i<investimentos.size();i++){
            String anoFinanca = investimentos.get(i).getAno();
            String mesFinanca = investimentos.get(i).getMes();
            if (anoFinanca.equals(anoEscolhido) && mesFinanca.equals(mesEscolhido)){
                investimentosEscolhidos.add(investimentos.get(i));
            }
        }

        Button btnVoltar = findViewById(R.id.btnVoltarInves);
        Button btnAdicionarInvestimento = findViewById(R.id.btnAduicionarInves);
        InvestimentoAdapter adapter = new InvestimentoAdapter(this, usuario.getInvestimentos());
        listInvestimento.setAdapter(adapter);
        setChartData(investimentosEscolhidos);

        btnMudarData.setOnClickListener(view->{
            try {
                String anoEscolhido2 = ano.getText().toString();
                String mesEscolhido2 = mes.getText().toString();
                List<Investimento> investimentosEscolhidos2 = new ArrayList<>();

                for (int i = 0; i<investimentos.size();i++){
                    String anoFinanca = investimentos.get(i).getAno();
                    String mesFinanca = investimentos.get(i).getMes();
                    if (anoFinanca.equals(anoEscolhido2) && mesFinanca.equals(mesEscolhido2)){
                        investimentosEscolhidos2.add(investimentos.get(i));
                    }
                }
                InvestimentoAdapter adapter2 = new InvestimentoAdapter(this, investimentosEscolhidos2);
                listInvestimento.setAdapter(adapter2);
                setChartData(investimentosEscolhidos2);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        Intent intent = new Intent(this, DetalhesInvestimento.class);
        Bundle bundle1 = new Bundle();

        listInvestimento.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Investimento selectedInvestimento = usuario.getInvestimentos().get(position);
                bundle1.putSerializable("usuario", usuario);
                intent.putExtra("dados", bundle1);
                intent.putExtra("investimento", selectedInvestimento.getId());
                startActivity(intent);
                finish();
            }
        });

        btnAdicionarInvestimento.setOnClickListener(view->{
            Intent intent1 = new Intent(this, AdicionarInvestimento.class);
            bundle1.putSerializable("usuario", usuario);
            intent1.putExtra("dados", bundle1);
            startActivity(intent1);
        });


        btnVoltar.setOnClickListener(view->{
            Intent intent1 = new Intent(this, Home.class);
            bundle1.putSerializable("usuario", usuario);
            intent1.putExtra("dados", bundle1);
            startActivity(intent1);
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
    private void setChartData(List<Investimento> investimentos) {
        List<Entry> allEntries = new ArrayList<>();
        List<String> dates = new ArrayList<>();
        int index = 0;

        // Loop through all investments and get the data points
        for (Investimento investimento : investimentos) {
            List<MiniInvestimento> valores = investimento.getValores();
            for (MiniInvestimento valor : valores) {
                allEntries.add(new Entry(index++, valor.getValor()));
                dates.add(valor.getData());
            }
        }

        // Create a dataset for the LineChart
        LineDataSet dataSet = new LineDataSet(allEntries, "Investment Overview");
        dataSet.setColor(getResources().getColor(R.color.blue)); // Set the line color
        dataSet.setValueTextColor(getResources().getColor(R.color.black)); // Set value text color
        dataSet.setValueTextSize(10f); // Set value text size

        // Create the line data
        LineData lineData = new LineData(dataSet);

        // Set the LineChart data
        lineChartGeral.setData(lineData);

        // Set up the X Axis
        XAxis xAxis = lineChartGeral.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(dates)); // Use dates as x-axis labels
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);

        // Set up the Y Axis
        YAxis leftAxis = lineChartGeral.getAxisLeft();
        leftAxis.setGranularity(10f);
        lineChartGeral.getAxisRight().setEnabled(false); // Disable the right axis

        // Refresh the chart
        lineChartGeral.invalidate();
    }
}