package com.example.unityuplift2.ui;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.unityuplift2.Model.ImgModel;
import com.example.unityuplift2.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UploadPost extends Fragment {
    Button UploadBtn,CancelBtn;
    ImageView UploadImgView;
    private Uri ImageUri;
    private StorageReference storageRef = FirebaseStorage.getInstance().getReference();;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("Community").child("Posts");
    private DatabaseReference post = FirebaseDatabase.getInstance().getReference("All Posts");
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapter;
    FrameLayout uploadFrame;
    EditText uploadDescription;
    ImgModel model;

    String item,Description;

    String[] items = {"Music","Programming","Gaming","Cricket"};
    int random,min,max;


    public UploadPost() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_upload_post, container, false);

        UploadBtn = view.findViewById(R.id.uploadBtn);
        UploadImgView = view.findViewById(R.id.UploadImgView);
        CancelBtn = view.findViewById(R.id.cancelBtn);
        uploadFrame = view.findViewById(R.id.uploadFrame);
        uploadDescription = view.findViewById(R.id.uploadDescription);
        autoCompleteTextView = view.findViewById(R.id.selectCommunity);

        adapter = new ArrayAdapter<>(getContext(),R.layout.list_item,items);
        autoCompleteTextView.setAdapter(adapter);

        min = 10;
        max = 100000000;

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                random = (int) ((Math.random() * (max - min+1))+min);
                item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getContext(), item, Toast.LENGTH_SHORT).show();
            }
        });

        UploadImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Gallery = new Intent();
                Gallery.setAction(Intent.ACTION_GET_CONTENT);
                Gallery.setType("Image/*");
                startActivityForResult(Gallery,2);
            }
        });
        UploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ImageUri!=null){
                    UploadOnFirebase(ImageUri);
                }else {
                    Toast.makeText(getContext(), "Upload Image", Toast.LENGTH_SHORT).show();
                }
            }
        });
        CancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFrame.setVisibility(View.GONE);
                UploadBtn.setVisibility(View.GONE);
                UploadImgView.setVisibility(View.GONE);
                CancelBtn.setVisibility(View.GONE);
                Fragment fragment = new Home();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.uploadFrame,fragment).commit();
            }
        });

        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2 && resultCode == Activity.RESULT_OK && data != null){

            ImageUri = data.getData();
            UploadImgView.setImageURI(ImageUri);

        }

    }
    private void UploadOnFirebase(Uri uri){

        StorageReference fileRef = storageRef.child(System.currentTimeMillis() + "." + getFileExtension(ImageUri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Description = uploadDescription.getText().toString();
                        if(item.equals("Music")){
                            model = new ImgModel(uri.toString(),item,Description,"https://firebasestorage.googleapis.com/v0/b/unity-up-lift-2.appspot.com/o/music.jpg?alt=media&token=3f1132ac-07f1-40b6-8e83-419419b1e0d4");
                        } else if (item.equals("Programming")) {
                            model = new ImgModel(uri.toString(),item,Description,"https://firebasestorage.googleapis.com/v0/b/unity-up-lift-2.appspot.com/o/prog.jpeg?alt=media&token=97c7ea0b-0e8b-418c-96c9-89f9a7f2fb02");
                        } else if (item.equals("Cricket")) {
                            model = new ImgModel(uri.toString(),item,Description,"https://firebasestorage.googleapis.com/v0/b/unity-up-lift-2.appspot.com/o/cricket.jpg?alt=media&token=0b5fa2a1-e27a-46de-adb9-73878281f214");
                        }else if(item.equals("Gaming")){
                            model = new ImgModel(uri.toString(),item,Description,"https://firebasestorage.googleapis.com/v0/b/unity-up-lift-2.appspot.com/o/gaming.jpg?alt=media&token=4a64c1a1-4c6b-484b-b964-8b6f18b5b6cf");
                        }
                        String modelId = root.push().getKey();
                        root.child(item).child(Integer.toString(random)).setValue(model);
                        post.child(Integer.toString(random)).setValue(model);
                        Toast.makeText(getContext(), "Uploaded Successfully", Toast.LENGTH_SHORT).show();
                        UploadImgView.setImageResource(R.drawable.add_photo);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Uploading Failed !!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private String getFileExtension(Uri mUri) {
        ContentResolver cr = getActivity().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));
    }

}