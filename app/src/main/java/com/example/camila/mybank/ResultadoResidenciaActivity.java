package com.example.camila.mybank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultadoResidenciaActivity extends AppCompatActivity {

    private TextView textViewResidenciaValorFinalPreco;
    private TextView textViewResidenciaValorParcelasPreco;
    private Button buttonNovaTransacaoResidencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_residencia);
        Intent intent = getIntent();
        double valorTotalR = intent.getDoubleExtra("Valor", 0);
        double valorParcelaR = intent.getDoubleExtra("Parcelas", 0);
        boolean podePagarR = intent.getBooleanExtra("podePagar", false);

        textViewResidenciaValorFinalPreco = (TextView) findViewById(R.id.TextViewResidenciaValorFinalPreco);
        textViewResidenciaValorParcelasPreco = (TextView) findViewById(R.id.TextViewResidenciaValorParcelasPreco);
        buttonNovaTransacaoResidencia = (Button) findViewById(R.id.ButtonNovaTransacaoResidencia);

        if(podePagarR){
            textViewResidenciaValorFinalPreco.setText(textViewResidenciaValorFinalPreco.getText().toString()+String.valueOf(valorTotalR));
            textViewResidenciaValorParcelasPreco.setText(textViewResidenciaValorParcelasPreco.getText().toString()+String.valueOf(valorParcelaR));
        } else {
            textViewResidenciaValorParcelasPreco.setText("Você não pode pagar, valor da parcela 30% da sua renda líquida");
        }


        buttonNovaTransacaoResidencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultadoResidenciaActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
