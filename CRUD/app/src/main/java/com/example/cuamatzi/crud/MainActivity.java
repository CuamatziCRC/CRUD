package com.example.cuamatzi.crud;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Declaracion de los componentes
    EditText editTextId, editTextNombre, editTextTelefono;

    Button buttonAlta, buttonConsultar, buttonEditar, buttonEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Relacion de los componentes

        editTextId = findViewById(R.id.etID);
        editTextNombre = findViewById(R.id.etNombre);
        editTextTelefono = findViewById(R.id.etTelefono);

        buttonAlta = findViewById(R.id.bAlta);
        buttonConsultar = findViewById(R.id.bConsulta);
        buttonEditar = findViewById(R.id.bmodificacion);
        buttonEliminar = findViewById(R.id.beliminar);


        //Creamos las relaciones con los botones
        buttonAlta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alta();
            }
        });
        buttonConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consulta();
            }
        });
        buttonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editar();
            }
        });
        buttonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminar();
            }
        });
    }

    public void consulta() {

        SQLiteHelper admin = new SQLiteHelper(this, "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        //SE lee el campo ID
        String idUsuario = editTextId.getText().toString();

        //consultamos
        Cursor fila = db.rawQuery("SELECT nombre, telefono FROM usuarios WHERE idUsuario=" + idUsuario, null);

        //Si se encuentra se mostrara en caso contrario mostrar 2 alertas tipo toast
        if (fila.moveToFirst()) {
            editTextNombre.setText(fila.getString(0));
            editTextTelefono.setText(fila.getString(1));
        } else {
            Toast.makeText(this, "NO existe ningun usuario con ese ID", Toast.LENGTH_LONG).show();
            Toast.makeText(this, "Verifique el ID", Toast.LENGTH_LONG).show();
        }
    }

    //metodo de alta

    public void alta(){


        SQLiteHelper admin = new SQLiteHelper(this, "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();


        //Lectura de los campos
        String idUsuario = editTextId.getText().toString();
        String nombre = editTextNombre.getText().toString();
        String telefono = editTextTelefono.getText().toString();

        // variables de registro
        ContentValues registro = new ContentValues();
        //subimos
        registro.put("idUsuario",idUsuario);
        registro.put("nombre",nombre);
        registro.put("telefono",telefono);

        //se insertan los datos en la BD
        db.insert("usuarios",null,registro);

        //Cerramos la conexion para evitar conflictos
        db.close();

        //limppiamos los campos
        editTextId.setText("");
        editTextNombre.setText("");
        editTextTelefono.setText("");

        //Mensaje de confirmacion
        Toast.makeText(this,"El usuario se a registrado Exitosamente",Toast.LENGTH_LONG).show();

    }

    //metodo eliminar

    public void eliminar(){

        SQLiteHelper admin = new SQLiteHelper(this, "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        //Se lee id para eliminar
        String idUsuario = editTextId.getText().toString();

        //Aqui se borrara el registro del usuario como de sus campos
        int cant = db.delete("usuarios","idUsuario="+idUsuario,null);


        //Cerramos la conexion para evitar conflictos
        db.close();

        //limppiamos los campos
        editTextId.setText("");
        editTextNombre.setText("");
        editTextTelefono.setText("");


        if (cant == 1 ){
            Toast.makeText(this,"Usuario eliminado",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Usuario inexistente",Toast.LENGTH_LONG).show();
        }

    }

    //editar
    public void  editar(){
        SQLiteHelper admin = new SQLiteHelper(this, "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        //se leen los campos
        String idUsuario = editTextId.getText().toString();
        String nombre = editTextNombre.getText().toString();
        String telefono = editTextTelefono.getText().toString();

        //Se crean las variables para el registro
        ContentValues registro = new ContentValues();
        registro.put("nombre",nombre);
        registro.put("telefono",telefono);


        //Se hace update para el registro del idUsuario
        int cant = db.update("usuarios",registro,"idUsuario="+idUsuario,null);

        //Cerramos la conexion para evitar conflictos
        db.close();

        //limppiamos los campos
        editTextId.setText("");
        editTextNombre.setText("");
        editTextTelefono.setText("");

        //Si se encuntra o no se muestra un mensaje

        if (cant == 1 ){
            Toast.makeText(this,"Usuario modificasdo exitosamente",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Usuario inexistente",Toast.LENGTH_LONG).show();
        }


    }

}