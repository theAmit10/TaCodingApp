package com.example.tacoding.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tacoding.Model.AddUserModel;
import com.example.tacoding.R;

import java.util.ArrayList;

public class AddUserAdapter extends RecyclerView.Adapter<AddUserAdapter.viewHolder>{

    ArrayList<AddUserModel> list;
    Context context;

    public AddUserAdapter(ArrayList<AddUserModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.story_design_add_user_sample,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        AddUserModel addUserModel = list.get(position);

        holder.profileUserName.setText(addUserModel.getProfileUserName());
        holder.addprofileUserImage.setImageResource(addUserModel.getAddprofileUserImage());
        holder.userPlatform.setText(addUserModel.getUserPlatform());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView addprofileUserImage;
        TextView profileUserName, userPlatform;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            addprofileUserImage = itemView.findViewById(R.id.addprofileUserImage);
            profileUserName = itemView.findViewById(R.id.profileUserName);
            userPlatform = itemView.findViewById(R.id.userPlatform);
        }
    }
}
