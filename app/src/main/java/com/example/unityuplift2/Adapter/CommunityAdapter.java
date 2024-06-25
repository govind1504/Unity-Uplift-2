package com.example.unityuplift2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unityuplift2.Model.CommunityModel;
import com.example.unityuplift2.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.ViewHolder> {
    private Context mContext;
    private List<CommunityModel> mCommunity;

    public CommunityAdapter(Context mContext, List<CommunityModel> mCommunity) {
        this.mContext = mContext;
        this.mCommunity = mCommunity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.community_item,viewGroup,false);

        return new CommunityAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final CommunityModel comModel = mCommunity.get(position);
        holder.communityName.setText(comModel.getName());
        holder.communityDescription.setText(comModel.getDescription());
        Picasso.get().load(comModel.getImage()).into(holder.communityImage);
    }

    @Override
    public int getItemCount() {
        return mCommunity.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView communityName,communityDescription;
        public CircleImageView communityImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            communityImage = itemView.findViewById(R.id.communityImage);
            communityName = itemView.findViewById(R.id.communityName);
            communityDescription = itemView.findViewById(R.id.communityDescription);

        }
    }
}
