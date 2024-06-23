package com.example.unityuplift2.ui;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.unityuplift2.R;


public class Community extends Fragment {



    public Community() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_community, container, false);
        Button Btn1 = view.findViewById(R.id.community1_button);
        Button Btn2 = view.findViewById(R.id.community2_button);
        Button Btn3 = view.findViewById(R.id.community3_button);
        Btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Btn1.setTag("Joined");
                Btn1.setText("Joined");
                Btn1.setBackgroundColor(Color.RED);
                Btn1.setEnabled(false);
            }
        });
        Btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Btn2.setTag("Joined");
                Btn2.setText("Joined");
                Btn2.setBackgroundColor(Color.RED);
                Btn2.setEnabled(false);
            }
        });
        Btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Btn3.setTag("Joined");
                Btn3.setText("Joined");
                Btn3.setBackgroundColor(Color.RED);
                Btn3.setEnabled(false);
            }
        });

        return view;
    }
}