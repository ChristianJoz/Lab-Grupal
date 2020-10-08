package com.example.labgrupal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class recycler_productos extends AppCompatActivity {

    private RecyclerView recycler;
    private AdaptadorProductos adaptadorProductos;
    BaseDeDato datos = new BaseDeDato(recycler_productos.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_productos);


        recycler = findViewById(R.id.rview);

        recycler.setHasFixedSize(true);

        recycler.setLayoutManager(new LinearLayoutManager(this));

        adaptadorProductos = new AdaptadorProductos(recycler_productos.this, datos.mostrarProductos());
        recycler.setAdapter(adaptadorProductos);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuotro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.Inisistema) {
            Intent i = new Intent(recycler_productos.this , InicioSistema.class);
            startActivity(i);
            return true;
        }else if (id==R.id.Nuevo){
            Intent i = new Intent(recycler_productos.this , FormularioProducto.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}