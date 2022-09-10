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

import com.example.tacoding.Model.ContestModel;
import com.example.tacoding.R;
import com.github.thunder413.datetimeutils.DateTimeUnits;
import com.github.thunder413.datetimeutils.DateTimeUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class ContestAdapter extends RecyclerView.Adapter<ContestAdapter.viewHolder> {

    ArrayList<ContestModel> list;
    Context context;


    public ContestAdapter(ArrayList<ContestModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.story_design_contest,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        ContestModel contestModel = list.get(position);

        holder.platformImage.setImageResource(contestModel.getPlatformImage());
        holder.contestTitle.setText(contestModel.getContestTitle());
        holder.contestDescription.setText(contestModel.getContestDescription());
        holder.startDate.setText(contestModel.getStartDate());
        holder.endDate.setText(contestModel.getEndDate());

        holder.itemView.findViewById(R.id.contestView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Item Clicked", Toast.LENGTH_SHORT).show();
                
            }
        });

    }

    public void updateContest(ArrayList<ContestModel> updatedConstest){
        list.clear();
        list.addAll(updatedConstest);
        notifyDataSetChanged();

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        ImageView platformImage;
        TextView contestTitle, contestDescription, startDate, endDate;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            platformImage = itemView.findViewById(R.id.platformImage);
            contestTitle = itemView.findViewById(R.id.contestTitle);
            contestDescription = itemView.findViewById(R.id.contestDescription);
            startDate = itemView.findViewById(R.id.setStartTime);
            endDate = itemView.findViewById(R.id.setEndTime);
        }
    }
}
