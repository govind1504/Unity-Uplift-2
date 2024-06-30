package com.example.unityuplift2.ui;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.unityuplift2.Adapter.SearchAdapter;
import com.example.unityuplift2.Model.SearchUser;
import com.example.unityuplift2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Search extends Fragment {

    public Search() {
        // Required empty public constructor
    }

    private RecyclerView recyclerView;
    private SearchAdapter searchAdapter;
    private List<SearchUser> mUser;


    EditText searchBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        searchBar =view.findViewById(R.id.searchBar);
        recyclerView =view.findViewById(R.id.userDetails);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        mUser= new ArrayList<>();
        searchAdapter = new SearchAdapter(getContext(),mUser);
        recyclerView.setAdapter(searchAdapter);
        recyclerView.setVisibility(View.GONE);
        readUser();
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                recyclerView.setVisibility(View.GONE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                recyclerView.setVisibility(View.VISIBLE);
                searchUsers(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                recyclerView.setVisibility(View.VISIBLE);
                searchUsers(s.toString());
            }
        });


        return view;
    }
    public void searchUsers(String s){
        Query query = FirebaseDatabase.getInstance().getReference("Users").orderByChild("name").startAt(s).endAt(s+"\uf0ff");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mUser.clear();
                for (DataSnapshot snapshot1: snapshot.getChildren()){
                    SearchUser user = snapshot1.getValue(SearchUser.class);
                    mUser.add(user);
                }
                searchAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void readUser(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (searchBar.getText().toString().equals("")){
                    mUser.clear();
                    for (DataSnapshot snapshot1:snapshot.getChildren()){
                        SearchUser user = snapshot1.getValue(SearchUser.class);
                        mUser.add(user);
                    }
                    searchAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}