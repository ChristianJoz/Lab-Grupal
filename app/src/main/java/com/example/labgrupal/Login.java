package com.example.labgrupal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etusu = findViewById(R.id.usu);
        final EditText etcontra = findViewById(R.id.contra);
        Button btn_ingresar = findViewById(R.id.Ingresar);

        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usu =etusu .getText().toString();
                String contra = etcontra.getText().toString();
                if (usu.equals("admin")&&contra.equals("123")){
                    Intent I = new Intent(Login.this, InicioSistema.class);
                    startActivity(I);
                }
            }
        });

    }
}