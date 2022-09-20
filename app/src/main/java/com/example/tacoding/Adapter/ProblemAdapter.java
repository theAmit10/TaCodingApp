package com.example.tacoding.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tacoding.Model.ProblemModel;
import com.example.tacoding.Model.ProblemTagModel;
import com.example.tacoding.R;

import java.util.ArrayList;
import java.util.Locale;

public class ProblemAdapter extends RecyclerView.Adapter<ProblemAdapter.viewHolder>{
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


        holder.rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String urlOrg = "https://codeforces.com/problemset/problem/"+problemModel.getContestId()+"/"+problemModel.getIndex();

                String abc = "https://codeforces.com/contestRegistration/1736";
                holder.problemWebView = new WebView(v.getContext());
                holder.problemWebView.setWebChromeClient(new WebChromeClient());

                holder.problemWebView.getSettings().setJavaScriptEnabled(true);
                holder.problemWebView.getSettings().setDomStorageEnabled(true);
                holder.problemWebView.getSettings().setAppCacheEnabled(false);
                holder.problemWebView.loadUrl(abc);
//                holder.problemWebView.loadUrl(urlOrg);



                System.out.println("LINK : "+urlOrg);
                System.out.println("LINK abc : "+abc);

            }
        });







    }

    public void updateProblem(ArrayList<ProblemModel> updatedProblem){

//        list.clear();
        list.addAll(updatedProblem);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class viewHolder extends RecyclerView.ViewHolder{

        TextView contestId, name, rating, tags;
        View problemContainer;
        WebView problemWebView;



        public viewHolder(@NonNull View itemView) {
            super(itemView);

            contestId = itemView.findViewById(R.id.problemContestTitle);
            name = itemView.findViewById(R.id.problemContestName);
            rating = itemView.findViewById(R.id.setRating);
            tags  = itemView.findViewById(R.id.setTags);
            problemContainer = itemView.findViewById(R.id.problemContainer);
            problemWebView  = (WebView)itemView.findViewById(R.id.problemWebView);
        }

    }
}
