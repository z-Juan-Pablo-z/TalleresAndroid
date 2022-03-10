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
import android.widget.TextView;
import android.widget.Toast;

public class Cliente extends AppCompatActivity {
    EditText etidentificacion,etnombre,etcorreo,etcontraseña,etcontraseña2;
    Button btnguardar,btnconsultar,btnanular,btncancelar,btnregresar;
    TextView tvactivo;
    long resp;
    int sw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
        getSupportActionBar().hide();
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
        sw=0;
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
             if (!clave1.equals(clave2)){
                 Toast.makeText(this, "Las claves no coinciden", Toast.LENGTH_SHORT).show();
                 etcontraseña2.requestFocus();
             }else{
                 Conexion_Consesionario admin = new Conexion_Consesionario(this,"concecionario.bd",null,1);
                 SQLiteDatabase db=admin.getWritableDatabase();
                 ContentValues dato = new ContentValues();
                 dato.put("Identificacion",identificacion);
                 dato.put("nombre",nombre);
                 dato.put("usuario",correo);
                 dato.put("clave",clave1);
                 if(sw==0){
                     resp=db.insert("TblCliente",null,dato);
                 }else {
                     sw = 0;
                     resp = db.update("TblCliente",dato, "identificacion= '"+identificacion+"'",null);
                 }
                 if (resp>0){
                     Toast.makeText(this, "Registro guardado", Toast.LENGTH_SHORT).show();
                     limpiarCampos();
                 }else{
                     Toast.makeText(this, "Error guardando registro", Toast.LENGTH_SHORT).show();
                 }
                 db.close();
             }
         }



     }

    public void limpiarCampos(){
        sw=0;
        etidentificacion.setText("");
        etidentificacion.requestFocus();
        etcontraseña.setText("");
        etcorreo.setText("");
        etcontraseña2.setText("");
        etnombre.setText("");



     }
     public void consultar(View view){
        consultarCliente();
     }

     public void consultarCliente(){
        String identificacion;
        identificacion = etidentificacion.getText().toString();
         if(identificacion.isEmpty()){
             Toast.makeText(this, "Identificacion requerida", Toast.LENGTH_SHORT).show();
             etidentificacion.requestFocus();
         }else{
             Conexion_Consesionario admin = new Conexion_Consesionario(this,"concecionario.bd",null,1);
             SQLiteDatabase db =admin.getReadableDatabase();
             Cursor fila = db.rawQuery("Select * from TblCliente where identificacion='"+identificacion+"'",null);
             if (fila.moveToNext()){
                 sw=1;
                 Toast.makeText(this,"Registro encontrado",Toast.LENGTH_SHORT).show();
                 etnombre.setText(fila.getString(1));
                 etcorreo.setText(fila.getString(2));
                 etcontraseña.setText(fila.getString(3));

             }else{
                 Toast.makeText(this, "Registro no existe", Toast.LENGTH_SHORT).show();
             }
             db.close();
         }
     }
     public void regresar(View view){
         Intent intmain = new Intent(this,MainActivity.class);
         startActivity(intmain);
     }
}