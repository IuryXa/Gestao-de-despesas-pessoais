package com.example.gerenciadordefinancas;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gerenciadordefinancas.modelos.Investimento;
import com.example.gerenciadordefinancas.modelos.Usuario;

public class Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_principal);

        Usuario usuario = new Usuario("","","");

        EditText valorInvestimentoText = findViewById(R.id.valorInvestimento);
        EditText nomeInvestimentoText = findViewById(R.id.nomeInvestimento);
        EditText proximoValorText = findViewById(R.id.proximoValor);

        float valorInvestimento = Float.parseFloat(valorInvestimentoText.getText().toString());
        String nomeInvestimento = nomeInvestimentoText.getText().toString();
        float proximoValor = Float.parseFloat(proximoValorText.getText().toString());

        Investimento investimento = new Investimento(usuario.getId(),nomeInvestimento,valorInvestimento);

        investimento.calcularNovaDiferenca(proximoValor);

        if (investimento.getDiferenca() < -20) {
            mandarNotificacao();
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void mandarNotificacao() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default")
                .setContentTitle("Investimento Alerta!")
                .setContentText("O seu investimento está entrando no vermelho")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        if (notificationManager != null) {
            notificationManager.notify(1, builder.build());
        }
    }
}