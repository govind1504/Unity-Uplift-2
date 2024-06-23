package com.example.unityuplift2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences sp = getSharedPreferences("login",MODE_PRIVATE);
                Boolean check = sp.getBoolean("flag",false);

                if(check){
                    Intent intent = new Intent(SplashActivity.this, Home.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(SplashActivity.this , MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },3000);

    }
}