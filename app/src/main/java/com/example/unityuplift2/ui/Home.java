package com.example.unityuplift2.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.unityuplift2.Adapter.ImageAdapter;
import com.example.unityuplift2.Model.imageModel;
import com.example.unityuplift2.R;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment {

    ImageView createPost;
    LinearLayout appNamePanel;

    private ImageAdapter imageAdapter;
    private List<imageModel> imageLinks;

    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        imageLinks = new ArrayList<>();
        imageAdapter = new ImageAdapter(getContext(),imageLinks);
        recyclerView.setAdapter(imageAdapter);

        // Load images from Firebase Realtime Database
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        DatabaseReference getImage = databaseReference.child("All Posts");

        getImage.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                imageLinks.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    imageModel model = snapshot.getValue(imageModel.class);
                    imageLinks.add(model);
                }
                imageAdapter.notifyDataSetChanged();
            }

            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "Error Loading Images", Toast.LENGTH_SHORT).show();
            }
        });


        createPost = view.findViewById(R.id.createPost);
        appNamePanel = view.findViewById(R.id.appNamePanel);

        createPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appNamePanel.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                Fragment fragment = new UploadPost();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.homeFrame,fragment).commit();
            }
        });

        return view;
    }

}