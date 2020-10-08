package com.example.labgrupal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class recycler_productos extends AppCompatActivity {

    private RecyclerView recycler;
    private AdaptadorCategorias adaptadorProductos;
    BaseDeDato datos = new BaseDeDato(recycler_productos.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_productos);


        recycler = findViewById(R.id.rview);

        recycler.setHasFixedSize(true);

        recycler.setLayoutManager(new LinearLayoutManager(this));

        adaptadorProductos = new AdaptadorCategorias(recycler_productos.this,
                datos.mostrarCategorias());
        recycler.setAdapter(adaptadorProductos);
    }
}