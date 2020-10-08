package com.example.labgrupal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class recycler_Categoria extends AppCompatActivity {

    private RecyclerView recycler;
   private AdaptadorCategorias adaptadorCategorias;
    BaseDeDato datos = new BaseDeDato(recycler_Categoria.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta__categoria);

        recycler = findViewById(R.id.rview);

        recycler.setHasFixedSize(true);

        recycler.setLayoutManager(new LinearLayoutManager(this));

        adaptadorCategorias = new AdaptadorCategorias(recycler_Categoria.this,
                datos.mostrarCategorias());
        recycler.setAdapter(adaptadorCategorias);
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
        if (id == R.id.item_siguiente) {
            Intent i = new Intent(recycler_Categoria.this , formularioCategoria.class);
            startActivity(i);
            return true;
        }else if (id == R.id.item_Inicio) {
            Intent i = new Intent(recycler_Categoria.this , InicioSistema.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}