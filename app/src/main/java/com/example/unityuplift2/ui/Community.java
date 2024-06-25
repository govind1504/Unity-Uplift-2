package com.example.unityuplift2.ui;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.unityuplift2.Adapter.CommunityAdapter;
import com.example.unityuplift2.Model.CommunityModel;
import com.example.unityuplift2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Community extends Fragment {

    private RecyclerView recyclerView;
    private CommunityAdapter communityAdapter;
    private List<CommunityModel> mCommunity;

    public Community() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_community, container, false);

        recyclerView = view.findViewById(R.id.Communities);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mCommunity = new ArrayList<>();
        communityAdapter = new CommunityAdapter(getContext(),mCommunity);
        recyclerView.setAdapter(communityAdapter);

        allCommunities();
        return view;
    }
    private void allCommunities(){
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Community").child("Posts");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    CommunityModel model = dataSnapshot.getValue(CommunityModel.class);
                    mCommunity.add(model);
                }
                communityAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}