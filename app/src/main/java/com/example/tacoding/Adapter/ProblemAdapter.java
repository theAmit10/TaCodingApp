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
import com.example.tacoding.Model.ProblemTagModel;
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

        holder.contestId.setText(problemModel.getContestId());
        holder.name.setText(problemModel.getName());
        holder.rating.setText(problemModel.getRating());
//        holder.tags.setText(problemModel.getTags());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<problemModel.getTags().size(); i++){
            sb.append(problemModel.getTags().get(i)).toString();
            sb.append(" | ");
        }
        holder.tags.setText(sb.toString());



    }

    public void updateProblem(ArrayList<ProblemModel> updatedProblem){
        list.addAll(updatedProblem);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        TextView contestId, name, rating, tags;



        public viewHolder(@NonNull View itemView) {
            super(itemView);

            contestId = itemView.findViewById(R.id.problemContestTitle);
            name = itemView.findViewById(R.id.problemContestName);
            rating = itemView.findViewById(R.id.setRating);
            tags  = itemView.findViewById(R.id.setTags);
        }
    }
}
