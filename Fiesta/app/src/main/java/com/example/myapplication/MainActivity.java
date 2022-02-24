package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioButton rbVIp,rbPalco,rbLaterales,rbGeneral;
    CheckBox cbAguardiente,cbRon,cbWhisky,cbCuota;
    TextView tvEntrada,tvValorAlcohol,tvTotal,tvAguardiente,tvRon,tvWhisky;
    EditText etPersonas,etCantidadBotellas;
    Button btnCalcular,btnLimpiar;
    private String cuota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        rbGeneral = findViewById(R.id.rbgeneral);
        rbLaterales = findViewById(R.id.rblaterales);
        rbPalco = findViewById(R.id.rbpalco);
        rbVIp = findViewById(R.id.rbvip);
        cbAguardiente = findViewById(R.id.cbaguardiente);
        cbRon = findViewById(R.id.cbron);
        cbWhisky = findViewById(R.id.cbwhisky);
        tvEntrada = findViewById(R.id.tventrada);
        tvValorAlcohol = findViewById(R.id.tvvaloralcohol);
        tvTotal = findViewById(R.id.tvTotal);
        tvAguardiente=findViewById(R.id.tvaguardiente);
        tvRon=findViewById(R.id.tvron);
        tvWhisky=findViewById(R.id.tvwhisky);
        etCantidadBotellas = findViewById(R.id.etcantidadbotellas);
        etPersonas = findViewById(R.id.etpersonas);
        btnCalcular = findViewById(R.id.btncalculate);
        btnLimpiar = findViewById(R.id.btnlimpiar);
        cbCuota = findViewById(R.id.cbcuota);

    }
    public void CalcularEntradas(View view){
        if (rbVIp.isChecked()){
            tvEntrada.setText("50000");
        }else{
            if (rbPalco.isChecked()){
                rbPalco.setText("35000");
            }else{
                if (rbLaterales.isChecked()){
                    rbLaterales.setText("20000");
                }else{
                    rbGeneral.setText("25000");
                }
            }
        }
        etCantidadBotellas.requestFocus();
        etCantidadBotellas.setText("1");
    }
    public void CalcularTrago(View view){
        String A,R,W,T,C;
        A=tvAguardiente.getText().toString();
        R=tvRon.getText().toString();
        W=tvWhisky.getText().toString();
        C=etCantidadBotellas.getText().toString();
        int aguardiente,ron,whisky,total,cantidad,total2;
        if(cbAguardiente.isChecked()){
                aguardiente=Integer.parseInt(A);
        }else{
            aguardiente=0;
        }
        if(cbRon.isChecked()){
            ron=Integer.parseInt(R);
        }else{
            ron=0;
        }
        if(cbWhisky.isChecked()){
            whisky=Integer.parseInt(W);
        }else{
            whisky=0;
        }
        if (C.isEmpty()){
            etCantidadBotellas.requestFocus();
            Toast.makeText(this, "Digitar la cantidad de botellas", Toast.LENGTH_SHORT).show();
            cantidad=Integer.parseInt(C);

        }else{
            cantidad=Integer.parseInt(C);
        }
        if (cantidad<1){
            etCantidadBotellas.requestFocus();
            Toast.makeText(this, "Es necesario digitar una cantidad positiva", Toast.LENGTH_SHORT).show();
        }else{
            total=aguardiente+ron+whisky;
            total2=total*cantidad;
            T=String.valueOf(total2);
            tvValorAlcohol.setText(T);
        }
    }
    public void CalcularPropina(View view,String cuota){
        String E,T;
        int entrada,trago,total,C;
        E=tvEntrada.getText().toString();
        T=tvValorAlcohol.getText().toString();
        entrada=Integer.parseInt(E);
        trago=Integer.parseInt(T);
        if (cbCuota.isChecked()){
            total=entrada+trago;
            C=(total*(10/100));
            cuota=String.valueOf(C);
        }
    }
    public void CalcularTotal(View view){
        String entrada,trago,propina,total;
        int e,t,p,m;
        entrada=tvEntrada.getText().toString();
        trago=tvValorAlcohol.getText().toString();
        propina = this.cuota;
        e=Integer.parseInt(entrada);
        t=Integer.parseInt(trago);
        p=Integer.parseInt(propina);
        m=e+t+p;
        total=String.valueOf(m);
        tvTotal.setText(total);
    }
}