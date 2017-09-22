package com.example.camila.mybank;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radiogroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radiogroup = (RadioGroup) findViewById(R.id.RadioGroupMain);

        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (i == R.id.RadioButtonVeiculo) {
                    Intent intent = new Intent(MainActivity.this, VeiculosActivity.class);
                    startActivity(intent);
                } else if (i == R.id.RadioButtonResidencia) {
                    Intent intent = new Intent(MainActivity.this, ResidenciaActivity.class);
                    startActivity(intent);
                }
            }
        });
    }


}
