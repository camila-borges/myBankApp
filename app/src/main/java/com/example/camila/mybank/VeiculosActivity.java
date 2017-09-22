package com.example.camila.mybank;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class VeiculosActivity extends AppCompatActivity {

    private RadioGroup radioGroup1;
    private EditText EditTextVeiculoPreco;
    private EditText EditTextVeiculoEntrada;
    private EditText EditTextVeiculoParcelas;
    private EditText EditTextVeiculoRendaMensal;
    private TextView TextViewVeiculoJuros;
    private Button ButtonVeiculoCalcular;
    private boolean isCarroNovo = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veiculos);
        radioGroup1 = (RadioGroup) findViewById(R.id.RadioGroup1);
        EditTextVeiculoPreco = (EditText) findViewById(R.id.EditTextVeiculoPreco);
        EditTextVeiculoEntrada = (EditText) findViewById(R.id.EditTextVeiculoEntrada);
        EditTextVeiculoParcelas = (EditText) findViewById(R.id.EditTextVeiculoParcelas);
        EditTextVeiculoRendaMensal = (EditText) findViewById(R.id.EditTextVeiculoRendaMensal);
        ButtonVeiculoCalcular = (Button) findViewById(R.id.ButtonVeiculoCalcular);
        calcVeiculoTotal();
    }

    public void calcVeiculoTotal() {
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (i == R.id.RadioButtonVeiculoNovo) { isCarroNovo = true; }
                else { isCarroNovo = false; }
            }
        });

        ButtonVeiculoCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (EditTextVeiculoPreco.getText().toString().isEmpty() || EditTextVeiculoEntrada.getText().toString().isEmpty() ||
                        EditTextVeiculoParcelas.getText().toString().isEmpty() || EditTextVeiculoRendaMensal.getText().toString().isEmpty()) {
                    Toast.makeText(VeiculosActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                } if(Double.parseDouble(EditTextVeiculoEntrada.getText().toString()) < 5 ||
                        Double.parseDouble(EditTextVeiculoEntrada.getText().toString()) > 100){
                    Toast.makeText(VeiculosActivity.this, "Valor de entrada inválido", Toast.LENGTH_SHORT).show();
                } else {
                    double veiculoPreco = Double.parseDouble(EditTextVeiculoPreco.getText().toString());
                    double veiculoEntrada = Double.parseDouble(EditTextVeiculoEntrada.getText().toString());
                    double veiculoParcelas = Double.parseDouble(EditTextVeiculoParcelas.getText().toString());
                    double veiculoRendaMensal = Double.parseDouble(EditTextVeiculoRendaMensal.getText().toString());
                    double veiculoJuros = 0;
                    double emplacamento = 0;
                    double ipva = 0;

                    if(isCarroNovo){
                        emplacamento = veiculoPreco * 0.01;
                        ipva = veiculoPreco * 0.04;
                    }

                    if(veiculoRendaMensal < 3500){ veiculoJuros = 0.06; }
                    else if(veiculoRendaMensal >= 3500.01 && veiculoRendaMensal <= 5000){ veiculoJuros = 0.05; }
                    else if(veiculoRendaMensal > 5000.01){ veiculoJuros = 0.04; }

                    double totalVeiculo = veiculoPreco + (veiculoPreco * veiculoJuros) + emplacamento + ipva;
                    double totalEntrada = ((veiculoPreco/100) * veiculoEntrada);
                    double valorParcela = (totalVeiculo - totalEntrada)/veiculoParcelas;

                    double valorMaximo = (veiculoRendaMensal*0.3);
                    boolean podePagar = valorMaximo > valorParcela;

                    Intent intent = new Intent(VeiculosActivity.this, ResultadoVeiculosActivity.class);
                    intent.putExtra("Valor", totalVeiculo);
                    intent.putExtra("Parcelas", valorParcela);
                    intent.putExtra("podePagar", podePagar);
                    startActivity(intent);
                }
            }
        });
    }

}
