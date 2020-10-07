package com.example.labgrupal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BaseDeDato extends SQLiteOpenHelper {
    public BaseDeDato(@Nullable Context context) {
        super(context, "Inventario.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table tb_categoria(id_categoria integer not null primary key, " +
                "nom_categoria varchar (50), estado_categoria tinyint(1))");

        db.execSQL("create table tb_producto(id_producto integer not null primary key autoincrement , nom_producto varchar (50)," +
                " des_producto text (50), catidad decimal (6,2), precio float," +
                " unidad_medida text (20), fecha_entrada timestamp,"+
                " id_categoria int, estado_producto int (1)," +
                " foreign key (id_categoria) references tb_categoria (id_categoria))");

        db.execSQL("create table tb_usuario(" +
                "id_usuario integer not null primary key autoincrement," +
                "nombre text (60)," +
                "apellido text (60), " +
                "correo text(45), " +
                "usuario text(30), " +
                "contraseña text(50), " +
                "tipo text (1), " +
                "estado int(1)," +
                " pregunta text(60), " +
                "respuesta text (45), " +
                "fecha_registro timestamp)");

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



    public List<Modelo_Usuario> mostrarUsuarios(){
        SQLiteDatabase bd = this.getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM tb_usuario order by id_usuario desc", null);
        List<Modelo_Usuario> usuarios = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                usuarios.add(new Modelo_Usuario(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getInt(7), cursor.getString(8), cursor.getString(9)));
            }while (cursor.moveToNext());
        }
        return usuarios;
    }

    public SQLiteDatabase bd() {
        SQLiteDatabase bd = this.getWritableDatabase();
        return bd;
    }

    public boolean InserTradicional(Modelo_Usuario datos) {
        boolean estado = true;
        int resultado;
        SQLiteDatabase bd = this.getWritableDatabase();
        try {
            int id_usuario = datos.getId_usuario();
            String nombre = datos.getNombre_usuario();
            String apellido = datos.getApellido_usuario();
            String correo = datos.getCorreo_usuario();
            String usario = datos.getUsuario_usu();
            String clave = datos.getClave_usuario();
            String tipo = datos.getTipo_usuario();
            int estado_usu = datos.getEstado_usuario();
            String pregunta = datos.getPregunta_usuario();
            String respuesta = datos.getRespuesta_usuario();

            Cursor fila = bd().rawQuery("select nombre from tb_usuario where nombre = '"+nombre+"'", null);

            if (fila.moveToFirst() == true) {
                estado = false;
            }else{
                String SQL = ("INSERT INTO tb_usuario \n " +
                        "(nombre, apellido, correo, usuario, contraseña, tipo, estado, pregunta, respuesta)\n " +
                        "VALUES \n " +
                        "('"+nombre+"','"+apellido+"','"+correo+"','"+usario+"','"+clave+"','"+tipo+"','"+String.valueOf(estado_usu)+"','"+pregunta+"','"+respuesta+"');");
                bd().execSQL(SQL);
                bd().close();
                estado = true;
            }

        } catch (Exception e) {
            estado = false;
            Log.e("error.", e.toString());
        }
        return estado;
    }

}

