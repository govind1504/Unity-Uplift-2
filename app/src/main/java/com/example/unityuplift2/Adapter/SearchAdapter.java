package com.example.unityuplift2.Adapter;

import android.content.Context;
import android.text.NoCopySpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.unityuplift2.Model.SearchUser;
import com.example.unityuplift2.R;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{

    private Context mContext;
    private List<SearchUser> mUser;
    private FirebaseUser firebaseUser;

    public SearchAdapter(Context mContext, List<SearchUser> mUser) {
        this.mContext = mContext;
        this.mUser = mUser;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.user_item,viewGroup,false);
        return new SearchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();

        final SearchUser user = mUser.get(position);

        holder.searchUserName.setText(user.getName());
        holder.searchUserBio.setText(user.getBio());
//        Glide.with(mContext).load(user.getImageUrl()).into(holder.circleImageView);
        Picasso.get().load(user.getimage()).into(holder.circleImageView);

    }

    @Override
    public int getItemCount() {
        return mUser.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView searchUserName,searchUserBio;
        public CircleImageView circleImageView;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            searchUserName = itemView.findViewById(R.id.searchUserName);
            searchUserBio = itemView.findViewById(R.id.searchUserBio);
            circleImageView = itemView.findViewById(R.id.searchImageProfile);


        }
    }
}
