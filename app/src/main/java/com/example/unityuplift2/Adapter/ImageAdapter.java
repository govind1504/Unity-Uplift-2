package com.example.unityuplift2.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.unityuplift2.Model.imageModel;
import com.example.unityuplift2.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private final Context context;
    private final List<imageModel> imageLinks;

    public ImageAdapter(Context context, List<imageModel> imageLinks) {
        this.context = context;
        this.imageLinks = imageLinks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.homeCommunityName.setText(imageLinks.get(position).getCommunity());
        holder.postDesc.setText(imageLinks.get(position).getPostDescription());
        Glide.with(context).load(imageLinks.get(position).getImageUrl()).into(holder.imageView);
        Glide.with(context).load(imageLinks.get(position).getPostTitleImage()).into(holder.PostTitleImage);
    }

    @Override
    public int getItemCount() {
        return imageLinks.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView homeCommunityName,postDesc;
        ImageView imageView;
        CircleImageView PostTitleImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.mainImg);
            homeCommunityName = itemView.findViewById(R.id.homeCommunityName);
            postDesc = itemView.findViewById(R.id.postDesc);
            PostTitleImage = itemView.findViewById(R.id.PostTitleImage);
        }
    }
}