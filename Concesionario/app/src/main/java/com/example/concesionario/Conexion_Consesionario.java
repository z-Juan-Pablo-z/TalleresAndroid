package com.example.concesionario;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Conexion_Consesionario extends SQLiteOpenHelper {

    public Conexion_Consesionario(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create table TblCliente(Identificacion text primary key,nombre text not null,usuario text not null," +
                "clave text not null, valor text,activo text not null default 'si')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table cliente");{
            onCreate(sqLiteDatabase);
        }

    }
}
