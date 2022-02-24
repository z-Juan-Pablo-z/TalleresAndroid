package com.example.arriendo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioButton rbHouse,rbApartment,rbFarm;
    EditText etCantidadHabitaciones;
    TextView tvArriendo,tvParking,tvSolucion,tvvhabitacion;
    CheckBox cbParking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         rbHouse = findViewById(R.id.idrbhouse);
        rbApartment = findViewById(R.id.idrbapartment);
        rbFarm = findViewById(R.id.idrbfarm);
        tvArriendo = findViewById(R.id.tvarriendo);
        tvParking = findViewById(R.id.tvparking);
        etCantidadHabitaciones=findViewById(R.id.etcanthabitaciones);
        cbParking=findViewById(R.id.cbparking);
        tvvhabitacion=findViewById(R.id.tvvhabitacion);
        tvSolucion = findViewById(R.id.tvSolucion);
    }
    public void CalcularArriendo(View view){


        if (rbHouse.isChecked()){
            tvArriendo.setText("400000");
        }else{
            if (rbApartment.isChecked()){
                tvArriendo.setText("300000");
            }else{
                if (rbFarm.isChecked()){
                    tvArriendo.setText("600000");
                }
            }
        }
    }
    public  void CalcularHabitaciones(View view){
        String cantidad;
        cantidad= etCantidadHabitaciones.getText().toString();
        int cant;
        cant=Integer.parseInt(cantidad);


        if (cant<3){
            tvvhabitacion.setText("100000");
        }else{
            if (cant==3 || cant == 4){
                tvvhabitacion.setText("200000");
            }else{
                if (cant>4){
                    tvvhabitacion.setText("300000");
                }
            }
        }

    }
    public void ValidarOpcional(View view){
        if (cbParking.isChecked()){
            tvParking.setText("100000");
        }else{
            tvParking.setText("0");
        }

    }

    public void CalcularTotal(View view){

        String cantidad,tvarriendo,tvhabitacion,tvparking;
        tvarriendo= tvArriendo.getText().toString();
        tvhabitacion= tvArriendo.getText().toString();
        tvparking= tvParking.getText().toString();
        cantidad = etCantidadHabitaciones.getText().toString();
        int arriendo;
        arriendo=Integer.parseInt(tvarriendo);

        if (cantidad.isEmpty()){
            etCantidadHabitaciones.requestFocus();
            Toast.makeText(this, "Necesita digitar la cantidad de habitaciones", Toast.LENGTH_SHORT).show();
        }
        else
        {
            int cant,valor,parking,total;
            cant=Integer.parseInt(cantidad);

            if (cant<1)
            {
                Toast.makeText(this, "Cantidad de habitaciones deben ser mayores a 0", Toast.LENGTH_SHORT).show();
                etCantidadHabitaciones.requestFocus();
            }
            else
            {
                valor=Integer.parseInt(tvhabitacion);
                parking=Integer.parseInt(tvparking);
                total = arriendo+valor+parking;
                String m = String.valueOf(total);

                tvSolucion.setText(m);

            }
        }
    }
    public void Limpiar(View view){
        tvArriendo.setText("400000");
        tvParking.setText("0");
        tvSolucion.setText("0");
        tvvhabitacion.setText("0");
        etCantidadHabitaciones.setText("");
        etCantidadHabitaciones.requestFocus();

        rbHouse.setChecked(true);
        cbParking.setChecked(false);

    }

}