package com.example.concesionario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etUsuario,etContraseña;
    Button btnIngresar,btnCancelar,btnRegistrarse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        etUsuario=findViewById(R.id.etUsuario);
        etContraseña=findViewById(R.id.etContraseña);
        btnIngresar=findViewById(R.id.btnIngresar);
        btnRegistrarse=findViewById(R.id.btnRegistrar);
        btnCancelar=findViewById(R.id.btnCancelar);
    }

    public void Registrarse(View view){
        Intent intRegistrarse=new Intent(this,Cliente.class);
        startActivity(intRegistrarse);
    }
    public void cancelar(View view){
        limpiarCampos();
    }
    public void limpiarCampos(){
        etContraseña.setText("");
        etUsuario.setText("");
        etUsuario.requestFocus();
    }
    public void Ingresar(View view){
        String usuario;
        String clave;
        clave = etContraseña.getText().toString();
        usuario = etUsuario.getText().toString();
        if (usuario.isEmpty()||clave.isEmpty()){
            Toast.makeText(this, "Necesita un usuario y contraseña", Toast.LENGTH_SHORT).show();
            etUsuario.requestFocus();
        }else{
            Conexion_Consesionario admin = new Conexion_Consesionario(this,"concecionario.bd",null,1);
            SQLiteDatabase db = admin.getReadableDatabase();
            Cursor fila = db.rawQuery("Select usuario from TblCliente where usuario='"+usuario+"'and clave = '"+clave+"'"  ,null);
            if (fila.moveToNext()){
                Toast.makeText(this, "Usuario coincide", Toast.LENGTH_SHORT).show();
                Intent intVehiculo= new Intent(this,Vehiculo.class);
                startActivity(intVehiculo);

            }else{
                Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
                etUsuario.requestFocus();
            }
            db.close();
        }


    }
}