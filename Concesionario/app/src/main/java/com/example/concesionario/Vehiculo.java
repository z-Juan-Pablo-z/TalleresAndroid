package com.example.concesionario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Vehiculo extends AppCompatActivity {
    EditText etPlaca,etMarca,etModelo,etValor;
    Button btnSave,btnConsultar,btnAnular,btnCancelar,btnRegresar;
    int sw=0;
    long resp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehiculo);
        getSupportActionBar().hide();

        etPlaca = findViewById(R.id.etplaca);
        etMarca = findViewById(R.id.etmarca);
        etModelo = findViewById(R.id.etmodelo);
        etValor = findViewById(R.id.etvalor);
        btnSave = findViewById(R.id.btnsave);
        btnConsultar = findViewById(R.id.btnconsultar);
        btnAnular = findViewById(R.id.btnanular);
        btnCancelar = findViewById(R.id.btncancelar);
        btnRegresar = findViewById(R.id.btnregresar);
        sw=0;

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiar();
            }
        });
        btnConsultar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Consultar();
            }
        });
        btnRegresar.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){
                volver();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                guardarVehiculo();
            }
        });
    }

    public void limpiarCampos(){
        etPlaca.setText("");
        etMarca.setText("");
        etModelo.setText("");
        etValor.setText("");
        etPlaca.requestFocus();
        sw=0;
    }
    public void limpiar(){
        limpiarCampos();
    }
    public void Consultar(){
        String placa;
        placa = etPlaca.getText().toString();
        if (placa.isEmpty()){
            Toast.makeText(this, "La placa es requerida", Toast.LENGTH_SHORT).show();
            etPlaca.requestFocus();
        }else{
            Conexion_Consesionario admin = new Conexion_Consesionario(this,"concecionario1.bd",null,1);
            SQLiteDatabase db = admin.getReadableDatabase();
            Cursor fila = db.rawQuery("Select * from TblVehiculo where placa='"+placa+"'",null);
            if (fila.moveToNext()){
                sw=1;
                etMarca.setText(fila.getString(1));
                etModelo.setText(fila.getString(2));
                etValor.setText(fila.getString(3));
                Toast.makeText(this, "Vehiculo encontrado", Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(this, "Vehiculo no encontrado", Toast.LENGTH_SHORT).show();
            }
            db.close();
        }
    }
    public void guardarVehiculo(){
        String placa,marca,modelo,valor;
        placa = etPlaca.getText().toString();
        marca = etMarca.getText().toString();
        modelo = etModelo.getText().toString();
        valor = etValor.getText().toString();
        if (placa.isEmpty()||marca.isEmpty()||modelo.isEmpty()||valor.isEmpty()){
            Toast.makeText(this, "TODOS LOS DATOS SON REQUERIDOS", Toast.LENGTH_SHORT).show();
            etPlaca.requestFocus();
        }else{
            Conexion_Consesionario admin = new Conexion_Consesionario(this,"concecionario1.bd",null,1);
            SQLiteDatabase db=admin.getWritableDatabase();
            ContentValues variable = new ContentValues();
            variable.put("placa",placa);
            variable.put("marca",marca);
            variable.put("modelo",modelo);
            variable.put("valor",valor);
            if (sw==0){
                resp=db.insert("TblVehiculo",null,variable);
            }else{
                sw=0;
                resp=db.update("TblVehiculo",variable,"placa='"+placa+"'",null);
            }
            if (resp>0){
                Toast.makeText(this, "Vehiculo Guardado", Toast.LENGTH_SHORT).show();
                limpiar();
            }else{
                Toast.makeText(this, "Error al guardar el vehiculo", Toast.LENGTH_SHORT).show();
            }
            db.close();
        }

    }
    public void volver(){
        Intent intmain = new Intent(this,MainActivity.class);
        startActivity(intmain);
    }


}