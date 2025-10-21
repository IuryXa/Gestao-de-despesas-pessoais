package com.example.gerenciadorfinancas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gerenciadorfinancas.modelos.Investimento;
import com.example.gerenciadorfinancas.modelos.MiniInvestimento;
import com.example.gerenciadorfinancas.modelos.Usuario;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class DetalhesInvestimento extends AppCompatActivity {

    private LineChart lineChart; // Declare the LineChart

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detalhes_investimento);

        Bundle bundle = getIntent().getExtras().getBundle("dados");
        Usuario usuario =(Usuario) bundle.getSerializable("usuario");
        Investimento investimento = usuario.getInvestimento(getIntent().getExtras().getInt("investimento"));

        // Initialize LineChart
        lineChart = findViewById(R.id.lineChart);

        // Set up the chart data
        setChartData(investimento);

        TextView nome = findViewById(R.id.textNomeDI);
        TextView categoria = findViewById(R.id.textTipoDI);
        TextView valorAtual = findViewById(R.id.textValorDI);
        TextView dataUltima = findViewById(R.id.textDataDI);
        Button btnExcluir = findViewById(R.id.btnExcluirInvestimentoDI);
        Button btnVoltar = findViewById(R.id.btnVoltarDetalheInvestimento);
        Button btnAdicionarValor = findViewById(R.id.btnAdicionarNovoValorDI);

        nome.setText(investimento.getNome());
        categoria.setText(investimento.getCategoria());
        valorAtual.setText(""+investimento.getValorAtual());
        dataUltima.setText(investimento.getValores().get(investimento.getValores().size()-1).getData());

        btnAdicionarValor.setOnClickListener(view->{
            Bundle bundle1 = new Bundle();
            bundle1.putSerializable("usuario", usuario);
            Intent intent = new Intent(this, AdicionarNovoValor.class);
            intent.putExtra("dados", bundle1);
            intent.putExtra("investimento", getIntent().getExtras().getInt("investimento"));
            startActivity(intent);
            finish();
        });

        btnVoltar.setOnClickListener(view->{
            Bundle bundle1 = new Bundle();
            bundle1.putSerializable("usuario", usuario);
            Intent intent = new Intent(this, Investimentos.class);
            intent.putExtra("dados",bundle1);
            startActivity(intent);
            finish();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void setChartData(Investimento investimento) {
        List<MiniInvestimento> valores = investimento.getValores();
        List<Entry> entries = new ArrayList<>();
        List<String> dates = new ArrayList<>();

        // Prepare the data points for the LineChart
        for (int i = 0; i < valores.size(); i++) {
            MiniInvestimento valor = valores.get(i);
            entries.add(new Entry(i, valor.getValor()));
            dates.add(valor.getData());
        }

        // Create a dataset
        LineDataSet dataSet = new LineDataSet(entries, "Investment History");
        dataSet.setColor(getResources().getColor(R.color.green)); // Set line color
        dataSet.setValueTextColor(getResources().getColor(R.color.black)); // Set value text color
        dataSet.setValueTextSize(10f); // Set value text size

        // Set the LineChart data
        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);
        lineChart.invalidate(); // Refresh the chart

        // Set up X Axis
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(dates)); // Use dates as x-axis labels
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);

        // Set up Y Axis
        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setGranularity(10f);
        lineChart.getAxisRight().setEnabled(false); // Disable right axis
    }
}