package com.example.labgrupal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Loginfire extends AppCompatActivity {
private EditText correo, contraseña;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginfire);
        mAuth = FirebaseAuth.getInstance();

        correo = findViewById(R.id.correo1);
        contraseña = findViewById(R.id.contraseña1);
    }

    public void log (View view){

        Intent ñ = new Intent(getApplicationContext(),registro.class);
        startActivity(ñ);
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
       // updateUI(currentUser);
    }
    public void iniciarsee(View view){

        mAuth.signInWithEmailAndPassword(correo.getText().toString().trim(),contraseña.getText().toString().trim() )
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(), "Usuario correcto.",
                                    Toast.LENGTH_SHORT).show();
                            Intent l = new Intent(getApplicationContext(),InicioSistema.class);
                            startActivity(l);
                           // updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(getApplicationContext(), "No esta Registrado.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });

    }
}