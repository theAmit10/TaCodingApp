package com.example.tacoding.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tacoding.Model.TopCoderModel;
import com.example.tacoding.R;

import java.util.ArrayList;

public class TopCoderAdapter extends RecyclerView.Adapter<TopCoderAdapter.viewHolder>{

    ArrayList<TopCoderModel> list;
    Context context;

    public TopCoderAdapter(ArrayList<TopCoderModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.story_design_top_coder,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        TopCoderModel topCoderModel = list.get(position);

        holder.topCoderImage.setImageResource(topCoderModel.getTopCoderImage());
        holder.username.setText(topCoderModel.getUsername());
        holder.title.setText(topCoderModel.getTitle());
        holder.codingPlatform.setText(topCoderModel.getCodingPlatform());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView topCoderImage;
        TextView username, title, codingPlatform;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            topCoderImage = itemView.findViewById(R.id.topCoderImage);
            username = itemView.findViewById(R.id.userName);
            title = itemView.findViewById(R.id.title);
            codingPlatform = itemView.findViewById(R.id.codingPlatform);
        }
    }
}
