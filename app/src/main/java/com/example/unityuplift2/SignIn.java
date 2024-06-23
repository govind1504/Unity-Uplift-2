package com.example.unityuplift2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignIn extends AppCompatActivity {
    EditText Email, Pass;
    Button Login;
    TextView SignUp;
    Intent intent1, intent2;

    FirebaseAuth fAuth;
    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Email = findViewById(R.id.Email);
        Pass = findViewById(R.id.Password);
        Login = findViewById(R.id.SignInbtn);
        SignUp = findViewById(R.id.registerActivityintent);

        intent1 = new Intent(this, Home.class);
        intent2 = new Intent(this, RegisterActivity.class);

        fAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();



        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fAuth.signInWithEmailAndPassword(Email.getText().toString(),Pass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            SharedPreferences sp = getSharedPreferences("login",MODE_PRIVATE);
                            SharedPreferences.Editor editor = sp.edit();

                            editor.putBoolean("flag",true);
                            editor.apply();

                            startActivity(intent1);
                        }else {
                            Toast.makeText( SignIn.this,"There is a error in your credential" , Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent2);
            }
        });
    }
}