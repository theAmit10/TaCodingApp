package com.example.tacoding.Adapter;




import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tacoding.Model.ProfileModel;
import com.example.tacoding.R;

import java.util.ArrayList;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.viewHolder> {

    ArrayList<ProfileModel> list;
    Context context;

    public ProfileAdapter(ArrayList<ProfileModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.story_design_profile_progress, parent, false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        ProfileModel profileModel = list.get(position);

//        holder.profilePlatformImage.setImageResource(profileModel.getProfilePlatformImage());
//        holder.profileCodingPlatformName.setText(profileModel.getProfileCodingPlatformName());
//        holder.profileRating.setText(profileModel.getProfileRating());
//        holder.profileProblemSolve.setText(profileModel.getProfileProblemSolve());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView  profilePlatformImage,profileUserImage;
        TextView profileCodingPlatformName,profileProblemSolve,profileRating,profileUserName;

        public viewHolder(@NonNull View itemView) {
            super(itemView);


            profilePlatformImage = itemView.findViewById(R.id.profilePlatformImage);
            profileCodingPlatformName = itemView.findViewById(R.id.profileCodingPlatformName);
            profileProblemSolve = itemView.findViewById(R.id.profileProblemSolve);
            profileRating = itemView.findViewById(R.id.profileRating);

        }
    }
}
