package com.example.tacoding.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tacoding.Model.ProfileUserDetailModel;
import com.example.tacoding.R;

import java.util.ArrayList;

public class ProfileUserDetailAdapter extends RecyclerView.Adapter<ProfileUserDetailAdapter.viewHolder>{

    ArrayList<ProfileUserDetailModel> list;
    Context context;

    public ProfileUserDetailAdapter(ArrayList<ProfileUserDetailModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.story_design_profile_progress,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        ProfileUserDetailModel profileUserDetailModel = list.get(position);

        holder.profileUserName.setText(profileUserDetailModel.getProfileUserName());
        holder.profileUserImage.setImageResource(profileUserDetailModel.getProfileUserImage());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView profileUserImage;
        TextView profileUserName;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            profileUserImage = itemView.findViewById(R.id.profileUserImage);
            profileUserName = itemView.findViewById(R.id.profileUserName);
        }
    }
}
