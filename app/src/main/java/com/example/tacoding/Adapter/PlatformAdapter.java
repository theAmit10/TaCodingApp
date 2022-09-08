package com.example.tacoding.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tacoding.R;
import com.example.tacoding.tadatabase.Platform;
import java.util.ArrayList;

public class PlatformAdapter extends RecyclerView.Adapter<PlatformAdapter.PlatformViewHolder> {

    ArrayList<Platform> allPlatform = new ArrayList<>();
    Context context;

    IPlatformRVAdapter listener;

    public PlatformAdapter(Context context, IPlatformRVAdapter listener) {
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PlatformViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        PlatformViewHolder viewHolder = new PlatformViewHolder(LayoutInflater.from(context).inflate(R.layout.coding_platform_story_design,parent,false));

        viewHolder.codingPlatformImage.setOnClickListener(allPlatforms -> {
            listener.onITemClick(allPlatform.get(viewHolder.getAdapterPosition()));
            Toast.makeText(context, "SELECTED", Toast.LENGTH_SHORT).show();
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlatformViewHolder holder, int position) {

        Platform currentPlatform = allPlatform.get(position);
        holder.codingPlatformImage.setImageResource(currentPlatform.getPlatformImage());
        holder.codingPlatformName.setText(currentPlatform.getPlatformName());

    }

    public void updateList(ArrayList<Platform> newList){
        allPlatform.clear();
        allPlatform.addAll(newList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return allPlatform.size();
    }

    public class PlatformViewHolder extends RecyclerView.ViewHolder{

        ImageView codingPlatformImage;
        TextView codingPlatformName;

        public PlatformViewHolder(@NonNull View itemView) {
            super(itemView);

            codingPlatformName = itemView.findViewById(R.id.codingPlatformName);
            codingPlatformImage = itemView.findViewById(R.id.codingPlatformImage);
        }
    }
}

