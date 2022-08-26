package com.example.tacoding.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tacoding.Model.CodingPlatformModel;
import com.example.tacoding.R;

import java.util.ArrayList;

public class CodingPlatformAdapter extends RecyclerView.Adapter<CodingPlatformAdapter.viewHolder> {

    ArrayList<CodingPlatformModel> list;
    Context context;

    public CodingPlatformAdapter(ArrayList<CodingPlatformModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.coding_platform_story_design,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        CodingPlatformModel codingPlatformModel = list.get(position);
        holder.codingPlatformImage.setImageResource(codingPlatformModel.getCodingPlatformImage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView codingPlatformImage;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            codingPlatformImage = itemView.findViewById(R.id.codingPlatformImage);
        }
    }
}
