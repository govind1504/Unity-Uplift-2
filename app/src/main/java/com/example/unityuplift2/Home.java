package com.example.unityuplift2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.unityuplift2.ui.Community;
import com.example.unityuplift2.ui.Profile;
import com.example.unityuplift2.ui.Search;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {
    private static final int ROOT_FRAGMENT_TAG = 0;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottomNavigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.navHome){
                    loadFrag(new com.example.unityuplift2.ui.Home(),false);
                }else if (id == R.id.navSearch){
                    loadFrag(new Search(),false);
                } else if (id == R.id.navCommunity) {
                    loadFrag(new Community(),false);
                } else {
                    loadFrag(new Profile(),false);
                }
                return true;
            }

        });

        bottomNavigationView.setSelectedItemId(R.id.navHome);

    }
    public void loadFrag(Fragment fragment, boolean flag){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (flag) {
            fragmentTransaction.add(R.id.frame, fragment);
            fragmentManager.popBackStack(ROOT_FRAGMENT_TAG,FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        else {
            fragmentTransaction.replace(R.id.frame,fragment);
        }
        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();

    }
}