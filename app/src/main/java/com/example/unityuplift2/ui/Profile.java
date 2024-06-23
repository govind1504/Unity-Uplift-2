package com.example.unityuplift2.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unityuplift2.EditProfile;
import com.example.unityuplift2.MainActivity;
import com.example.unityuplift2.Model.Users;
import com.example.unityuplift2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends Fragment {
    public Profile() {
        // Required empty public constructor
    }
    String uid,uName,uEmail;
    Button editProfile;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseAuth fAuth;
    TextView userEmail,userName;
    Users user;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        database = FirebaseDatabase.getInstance();
        fAuth = FirebaseAuth.getInstance();
        uid = fAuth.getCurrentUser().getUid().toString();
        myRef = database.getReference().child("Users").child(uid);

        editProfile = view.findViewById(R.id.editProfile);
        userEmail = view.findViewById(R.id.userEmail);
        userName = view.findViewById(R.id.userName);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user = snapshot.getValue(Users.class);
                uName = user.getName();
                uEmail = user.getEmail();
                userName.setText(uName);
                userEmail.setText(uEmail);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getDetails(), Toast.LENGTH_SHORT).show();
            }
        });


        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Hello", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), EditProfile.class);
                startActivity(intent);
            }
        });

        return view;
    }

}