package com.example.tacoding.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tacoding.Model.ProblemModel;
import com.example.tacoding.R;

import java.util.ArrayList;

public class ProblemAdapter extends RecyclerView.Adapter<ProblemAdapter.viewHolder> {
    ArrayList<ProblemModel> list;
    Context context;

    public ProblemAdapter(ArrayList<ProblemModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.story_design_problem_fragment,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        ProblemModel problemModel = list.get(position);

        holder.problemPlatformImage.setImageResource(problemModel.getProblemPlatformImage());
        holder.problemContestName.setText(problemModel.getProblemContestName());
        holder.problemTitle.setText(problemModel.getProblemTitle());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView problemPlatformImage;
        TextView problemContestName, problemTitle;



        public viewHolder(@NonNull View itemView) {
            super(itemView);

            problemPlatformImage = itemView.findViewById(R.id.problemPlatformImage);
            problemContestName = itemView.findViewById(R.id.problemContestName);
            problemTitle = itemView.findViewById(R.id.problemContestTitle);
        }
    }
}
