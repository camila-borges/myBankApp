package com.example.camila.mybank;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ResidenciaActivity extends AppCompatActivity {

    private RadioGroup RadioGroup2;
    private EditText EditTextResidenciaPreco;
    private EditText EditTextResidenciaEntrada;
    private EditText EditTextResidenciaParcelas;
    private EditText EditTextResidenciaRendaMensal;
    private Button ButtonVeiculoCalcular;
    private boolean isResidenciaNovo = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_residencia);
        RadioGroup2 = (RadioGroup) findViewById(R.id.RadioGroup2);;
        EditTextResidenciaPreco = (EditText) findViewById(R.id.EditTextResidenciaPreco);
        EditTextResidenciaEntrada = (EditText) findViewById(R.id.EditTextResidenciaEntrada);
        EditTextResidenciaParcelas = (EditText) findViewById(R.id.EditTextResidenciaParcelas);
        EditTextResidenciaRendaMensal = (EditText) findViewById(R.id.EditTextResidenciaRendaMensal);
        ButtonVeiculoCalcular = (Button) findViewById(R.id.ButtonVeiculoCalcular);
        calcResidenciaTotal();
    }

    public void calcResidenciaTotal() {
        RadioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (i == R.id.RadioButtonResidenciaNovo) { isResidenciaNovo = true; }
                else { isResidenciaNovo = false; }
            }
        });

        ButtonVeiculoCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (EditTextResidenciaPreco.getText().toString().isEmpty() || EditTextResidenciaEntrada.getText().toString().isEmpty() ||
                        EditTextResidenciaParcelas.getText().toString().isEmpty() || EditTextResidenciaRendaMensal.getText().toString().isEmpty()) {
                    Toast.makeText(ResidenciaActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                } if(Double.parseDouble(EditTextResidenciaEntrada.getText().toString()) < 20 ||
                        Double.parseDouble(EditTextResidenciaEntrada.getText().toString()) > 100){
                    Toast.makeText(ResidenciaActivity.this, "Valor de entrada deve ser maior que 20%", Toast.LENGTH_SHORT).show();
                } else {
                    double residenciaPreco = Double.parseDouble(EditTextResidenciaPreco.getText().toString());
                    double residenciaEntrada = Double.parseDouble(EditTextResidenciaEntrada.getText().toString());
                    double residenciaParcelas = Double.parseDouble(EditTextResidenciaParcelas.getText().toString());
                    double residenciaRendaMensal = Double.parseDouble(EditTextResidenciaRendaMensal.getText().toString());
                    double residenciaJuros = 0;
                    double habitese = 0;
                    double transferencia = 0;

                    if(isResidenciaNovo){
                        habitese = residenciaPreco * 0.05;
                        transferencia = residenciaPreco * 0.02;
                    }

                    if(residenciaRendaMensal < 3500){ residenciaJuros = 0.03; }
                    else if(residenciaRendaMensal >= 3500.01 && residenciaRendaMensal <= 5000){ residenciaJuros = 0.025; }
                    else if(residenciaRendaMensal > 5000.01){ residenciaJuros = 0.02; }

                    double totalResidencia = residenciaPreco + (residenciaPreco * residenciaPreco) + habitese+ transferencia;
                    double totalEntradaR = ((residenciaPreco/100) * residenciaEntrada);
                    double valorParcelaR = (totalResidencia - totalEntradaR)/residenciaParcelas;

                    double valorMaximo = (residenciaRendaMensal*0.3);
                    boolean podePagar = valorMaximo > valorParcelaR;

                    Intent intent = new Intent(ResidenciaActivity.this, ResultadoVeiculosActivity.class);
                    intent.putExtra("Valor", totalResidencia);
                    intent.putExtra("Parcelas", valorParcelaR);
                    intent.putExtra("podePagar", podePagar);
                    startActivity(intent);
                }
            }
        });
    }
}
