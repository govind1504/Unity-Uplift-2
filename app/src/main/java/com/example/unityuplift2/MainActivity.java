package com.example.unityuplift2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button signInBtn1,signUpBtn1;
    Intent intent1, intent2;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signInBtn1 = findViewById(R.id.SignInbtn1);
        signUpBtn1 = findViewById(R.id.SignUpbtn1);

        intent1 = new Intent(this,RegisterActivity.class);
        intent2 = new Intent(this,SignIn.class);

        signUpBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent1);
            }
        });

        signInBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent2);
            }
        });
    }
}