package com.example.labgrupal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class BaseDeDato extends SQLiteOpenHelper {
    public BaseDeDato(@Nullable Context context) {
        super(context, "Inventario.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table tb_categoria(id_categoria integer not null primary key, " +
                "nom_categoria varchar (50), estado_categoria tinyint(1))");

        db.execSQL("create table tb_producto(id_producto int (9) not null primary key," +
                " nom_producto varchar (50)," +
                " des_producto varchar (50), stock decimal (6,2), precio decimal (6,2)," +
                " unidad_medida varchar (20), " +
                "estado_producto tinyint (1), id_categoria_categoria not null," +
                " FORENING KEY (id_categoria_categoria) references tb_categoria " +
                "(id_categoria), fecha_entrada timestamp)");

        db.execSQL("create table tb_usuario(id_usuario int(11) not null primary key, nom_usuario varchar (60)," +
                "apellido varchar (60), correo varchar(45), usuario varchar(30), contraseña varchar(50)," +
                " tipo tinyint (1), estado tinyint(1)," +
                " pregunta varchar(60), respuesta varchar (45), fecha_registro timestamp)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists tb_categoria");
        db.execSQL("drop table if exists tb_producto");
        db.execSQL("drop table if exists tb_usuario");
        onCreate(db);
    }
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }


    //getting the current time for joining date
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat();
}

