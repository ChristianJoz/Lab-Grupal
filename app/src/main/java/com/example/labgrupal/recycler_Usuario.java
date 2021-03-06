package com.example.labgrupal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
            Intent i = new Intent(recycler_Usuario.this , InicioSistema.class);
            startActivity(i);
            return true;
        } else if (id == R.id.Nuevo) {
            Intent i = new Intent(recycler_Usuario.this , FormularioUsuario.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}