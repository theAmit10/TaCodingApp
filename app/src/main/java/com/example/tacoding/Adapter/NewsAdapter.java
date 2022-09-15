package com.example.tacoding.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tacoding.Model.NewsModel;
import com.example.tacoding.R;

import java.util.ArrayList;
import java.util.Locale;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.viewHolder> implements Filterable {
    ArrayList<NewsModel> list;
    Context context;
    ArrayList<NewsModel> filteredList;

    public NewsAdapter(ArrayList<NewsModel> list, Context context) {
        this.list = list;
        this.context = context;
        this.filteredList =  new ArrayList<>(list);
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.story_desgn_news,parent, false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        NewsModel newsModel = list.get(position);

        holder.newsdata.setText(newsModel.getNewsDescription());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }


    private final Filter filter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            ArrayList<NewsModel> filteredArraylist =  new ArrayList<>();

            if(charSequence == null || charSequence.length() == 0){
                filteredArraylist.addAll(list);
            }else{
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for(NewsModel newsModel : list){
                    if(newsModel.getNewsDescription().toLowerCase().contains(filterPattern)){
                        filteredArraylist.add(newsModel);

                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values  = filteredArraylist;
            results.count  = filteredArraylist.size();
            return results;

        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            filteredList.clear();
            filteredList.addAll((ArrayList)filterResults.values);
            notifyDataSetChanged();

        }
    };

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView newsdata;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            newsdata = itemView.findViewById(R.id.newsdata);
        }
    }
}
