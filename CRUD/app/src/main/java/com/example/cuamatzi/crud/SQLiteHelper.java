package com.example.cuamatzi.crud;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {


    public SQLiteHelper(Context context,String nombre,SQLiteDatabase.CursorFactory factory,int version){
        super(context,nombre,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //creacion de la tabla de la base de datos : usuraios(idUsuario,nombre,telefono);
        db.execSQL("CREATE TABLE usuraios(idUsuario interger primary key,nombre text,telefono text);");//tipo texto por los editText

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        db.execSQL("DROP TABLE IF EXISTS usuarios");//QUITA LA TABLA
        db.execSQL("CREATE TABLE usuraios(idUsuario interger primary key,nombre text,telefono text);");

    }
}
