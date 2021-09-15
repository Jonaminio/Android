package com.example.imc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText etPeso;
    private EditText etAltura;
    private Button btCalcular;
    private Button btLimpar;
    private TextView tvResultado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPeso = (EditText) findViewById(R.id.etPeso);
        etAltura = (EditText) findViewById(R.id.etAltura);
        tvResultado = (TextView) findViewById(R.id.tvResultado);
        btCalcular = (Button) findViewById(R.id.btCalcular);
        btLimpar = (Button) findViewById(R.id.btLimpar);

        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btCalcularOnClick();
            }
        });

        btLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btLimparOnClick();
            }
        });


    }

    private void btLimparOnClick() {
    }
    protected void btCalcularOnClick() {
        if (etPeso.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Campo [Peso] deve ser preenchido.", Toast.LENGTH_LONG).show();
            etPeso.requestFocus();
            return;
        }
        if (etAltura.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Campo [Altura] deve ser preenchido.", Toast.LENGTH_LONG).show();
            etAltura.requestFocus();
            return;
        }
        double peso = Double.parseDouble(etPeso.getText().toString());
        double altura = Double.parseDouble(etAltura.getText().toString());
        double imc = peso / Math.pow(altura, 2);
        tvResultado.setText(new DecimalFormat("O.00").format(imc));
    }

    }