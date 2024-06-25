package com.example.unityuplift2.ui;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unityuplift2.Model.Users;
import com.example.unityuplift2.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfile extends Fragment {

    ImageView backBtn;
    Button saveChanges;
    TextView edit;
    EditText editName;
    CircleImageView editImage;
    String newName,uid;


    FirebaseStorage storage;
    FirebaseAuth fAuth;
    FirebaseDatabase db;
    DatabaseReference myRef;

    ActivityResultLauncher<String> launcher;

    public EditProfile() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        backBtn = view.findViewById(R.id.backBtn);
        saveChanges = view.findViewById(R.id.saveChanges);
        edit = view.findViewById(R.id.edit);
        editName = view.findViewById(R.id.editName);
        editImage = view.findViewById(R.id.editImage);

        storage = FirebaseStorage.getInstance();
        fAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        uid = fAuth.getCurrentUser().getUid().toString();
        myRef = db.getReference().child("Users").child(uid);


        launcher = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri uri) {
                editImage.setImageURI(uri);

                final StorageReference sRef = storage.getReference().child(uid).child("image");
                sRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        sRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                db.getReference().child("Users").child(uid).child("image").setValue(uri.toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(getContext(), "Image Uploaded Successfully", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backBtn.setVisibility(View.GONE);
                saveChanges.setVisibility(View.GONE);
                edit.setVisibility(View.GONE);
                editName.setVisibility(View.GONE);
                editImage.setVisibility(View.GONE);

                Fragment fragment = new Profile();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.editProf,fragment).commit();
            }
        });


        editImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher.launch("image/*");
            }
        });

        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newName = editName.getText().toString();

                if (newName.isEmpty()){
                    Toast.makeText(getContext(), "Enter Name to be Changed", Toast.LENGTH_SHORT).show();
                }else {
                    Users user = new Users(newName);
                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            snapshot.getRef().child("name").setValue(newName);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    backBtn.setVisibility(View.GONE);
                    saveChanges.setVisibility(View.GONE);
                    edit.setVisibility(View.GONE);
                    editName.setVisibility(View.GONE);
                    editImage.setVisibility(View.GONE);
                    Fragment fragment = new Profile();
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.editProf,fragment).commit();
                }
            }
        });




        setUpOnBackpressed();
        return view;
    }
    private void setUpOnBackpressed(){
        requireActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (isEnabled()){
                    backBtn.setVisibility(View.GONE);
                    saveChanges.setVisibility(View.GONE);
                    edit.setVisibility(View.GONE);
                    editName.setVisibility(View.GONE);
                    editImage.setVisibility(View.GONE);
                    Fragment fragment = new Profile();
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.editProf,fragment).commit();
                }
            }
        });
    }
}