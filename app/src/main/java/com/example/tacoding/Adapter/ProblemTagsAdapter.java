package com.example.tacoding.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tacoding.Model.ProblemTagModel;
import com.example.tacoding.R;

import java.util.ArrayList;

public class ProblemTagsAdapter extends RecyclerView.Adapter<ProblemTagsAdapter.viewHolder> {
    ArrayList<ProblemTagModel> list;
    Context context;

    public ProblemTagsAdapter(ArrayList<ProblemTagModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.story_design_problem_tags_model,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        ProblemTagModel problemTagModel = list.get(position);
        holder.ProblemTagName.setText(problemTagModel.getProblemTagName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView ProblemTagName;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            ProblemTagName = itemView.findViewById(R.id.ProblemTagName);
        }
    }
}
