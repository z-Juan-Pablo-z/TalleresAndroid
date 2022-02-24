package com.example.concesionario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Cliente extends AppCompatActivity {
    EditText etidentificacion,etnombre,etcorreo,etcontraseña,etcontraseña2;
    Button btnguardar,btnconsultar,btnanular,btncancelar,btnregresar;
    TextView tvactivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
        getActionBar().hide();
        etidentificacion = findViewById(R.id.etIdentificacion);
        etnombre = findViewById(R.id.etNombre);
        etcorreo = findViewById(R.id.etCorreo);
        etcontraseña = findViewById(R.id.etContraseña);
        etcontraseña2 = findViewById(R.id.etContraseña2);
        btnguardar = findViewById(R.id.btnGuardar);
        btnconsultar = findViewById(R.id.btnConsultar);
        btnanular = findViewById(R.id.btnAnular);
        btncancelar = findViewById(R.id.btnCancelar);
        btnregresar = findViewById(R.id.btnRegresar);
        tvactivo = findViewById(R.id.tvactivo);
    }
     public void Guardar(View view) {
        String identificacion,nombre,correo,clave1,clave2;
        identificacion=etidentificacion.getText().toString();
         nombre=etnombre.getText().toString();
         correo=etcorreo.getText().toString();
         clave1=etcontraseña.getText().toString();
         clave2=etcontraseña2.getText().toString();
         if (identificacion.isEmpty()|| nombre.isEmpty() ||
                 correo.isEmpty()||clave1.isEmpty()||clave2.isEmpty()){
             Toast.makeText(this, "TODOS LOS DATOS SON REQUERIDOS.", Toast.LENGTH_SHORT).show();
             etidentificacion.requestFocus();
         }else{
             if (clave1.equals(clave2)){
                 Toast.makeText(this, "Las claves no coinciden", Toast.LENGTH_SHORT).show();
                 etcontraseña2.requestFocus();
             }else{
                 Conexion_Consesionario admin = new Conexion_Consesionario(this,"concecionario.bd",null,1);
             }
         }



     }
}