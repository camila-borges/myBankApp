package com.example.camila.mybank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultadoVeiculosActivity extends AppCompatActivity {

    private TextView valorTextView;
    private TextView parcelasTextView;
    private Button ButtonNovaTransacaoVeiculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_veiculos);
        Intent intent = getIntent();
        double valorTotal = intent.getDoubleExtra("Valor", 0);
        double valorParcela = intent.getDoubleExtra("Parcelas", 0);
        boolean podePagar = intent.getBooleanExtra("podePagar", false);

        valorTextView = (TextView) findViewById(R.id.TextViewVeiculoValorFinalPreco);
        parcelasTextView = (TextView) findViewById(R.id.TextViewVeiculoValorParcelasPreco);
        ButtonNovaTransacaoVeiculo = (Button) findViewById(R.id.ButtonNovaTransacaoVeiculo);

        if(podePagar){
            valorTextView.setText(valorTextView.getText().toString()+String.valueOf(valorTotal));
            parcelasTextView.setText(parcelasTextView.getText().toString()+String.valueOf(valorParcela));
        } else {
            parcelasTextView.setText("Você não pode pagar, valor da parcela 30% da sua renda líquida");
        }


        ButtonNovaTransacaoVeiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultadoVeiculosActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
