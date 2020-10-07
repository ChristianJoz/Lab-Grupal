package com.example.labgrupal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class recycler_Usuario extends AppCompatActivity {

    private RecyclerView recycler;
    private AdaptadorUsuarios adaptadorUsuarios;
    private Button btn_nuevo;
    BaseDeDato datos = new BaseDeDato(recycler_Usuario.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_usuario);
        recycler = (RecyclerView)findViewById(R.id.rview);

        recycler.setHasFixedSize(true);

        recycler.setLayoutManager(new LinearLayoutManager(this));

        adaptadorUsuarios = new AdaptadorUsuarios(recycler_Usuario.this,
                datos.mostrarUsuarios());
        recycler.setAdapter(adaptadorUsuarios);

        btn_nuevo = findViewById(R.id.btn_nuevo);

    }

    public void nu (View view){
        Intent nuevo = new Intent(recycler_Usuario.this, FormularioUsuario.class);
        startActivity(nuevo);
    }
}