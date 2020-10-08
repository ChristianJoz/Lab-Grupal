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

import static android.content.ContentValues.TAG;

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

    public boolean InsertProducto(Modelo_Productos datos) {
        boolean estado = true;
        int resultado;
        SQLiteDatabase bd = this.getWritableDatabase();
        try {

            String nombre = datos.getNombre_producto();
            String descripcion = datos.getDescripcion_producto();
            int cantidad = datos.getCantidad_producto();
            double precio = datos.getPrecio();
            String unidadMedida = datos.getUnidad_medida();
            int idCategoria = datos.getId_categoria();
            int estadoProducto = datos.getEstado();

            Cursor fila = bd().rawQuery("select nom_producto from tb_producto where nom_producto = '"+nombre+"'", null);

            if ( fila.moveToFirst() ) {
                estado = false;
            }else{
                String SQL = ("INSERT INTO tb_producto (nom_producto, des_producto, catidad, precio, unidad_medida, id_categoria, estado_producto) " +
                        "VALUES ('"+nombre+"','"+descripcion+"',"+cantidad+","+precio+",'"+unidadMedida+"',"+idCategoria+","+estadoProducto + ");");
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
    public int getIdCategoria(String nombreCategoria) {

        Cursor fila = bd().rawQuery("select id_categoria from tb_categoria where nom_categoria = '"+nombreCategoria+"';", null);

        int id = -1;
        if (fila.moveToFirst()){
            Log.i(TAG, "id_categoria: " + fila.getInt(fila.getColumnIndex("id_categoria")));
            id = fila.getInt(fila.getColumnIndex("id_categoria"));
        }

        return id;
    }

    public List<String> mostrarcategoriaspinner(){
        SQLiteDatabase bd = this.getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM tb_categoria ", null);
        List<String> categorias = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                categorias.add(cursor.getString(1));
                categorias.indexOf(cursor.getInt(0));
            }while (cursor.moveToNext());
        }
        return categorias;
    }

    public boolean InsertarCategorias(Modelo_Categorias datos) {
        boolean estado = true;
        int resultado;
        SQLiteDatabase bd = this.getWritableDatabase();
        try {
            int id_categoria = datos.getId_categoria();
            String nom_categoria = datos.getNombre_categoria();
            int estado_categoria = datos.getEstado_categoria();

            Cursor fila = bd().rawQuery("select nom_categoria from tb_categoria where nom_categoria = '"+nom_categoria+"'", null);

            if (fila.moveToFirst() == true) {
                estado = false;
            }else{
                String SQL = ("INSERT INTO tb_categoria \n " +
                        "(nom_categoria, estado_categoria)\n " +
                        "VALUES \n " +
                        "('"+nom_categoria+"','"+String.valueOf(estado_categoria)+"');");
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
    public List<Modelo_Categorias> mostrarCategorias(){
        SQLiteDatabase bd = this.getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM tb_categoria order by id_categoria desc", null);
        List<Modelo_Categorias> categoria = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                categoria.add(new Modelo_Categorias(cursor.getInt(0), cursor.getString(1), cursor.getInt(2)));
            }while (cursor.moveToNext());
        }
        return categoria;
    }

    public List<Modelo_Productos> mostrarProductos(){
        SQLiteDatabase bd = this.getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM tb_producto order by id_producto desc", null);
        List<Modelo_Productos> productos = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                    productos.add(new Modelo_Productos(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(4), cursor.getString(5),cursor.getInt(6)));
            }while (cursor.moveToNext());
        }
        return productos;
    }

}

