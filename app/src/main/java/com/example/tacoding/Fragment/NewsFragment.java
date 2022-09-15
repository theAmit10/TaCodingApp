package com.example.tacoding.Fragment;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tacoding.Adapter.NewsAdapter;
import com.example.tacoding.Model.NewsModel;
import com.example.tacoding.databinding.FragmentNewsBinding;

import java.util.ArrayList;

public class NewsFragment extends Fragment {

    FragmentNewsBinding binding;
    NewsAdapter newsAdapter;

    RecyclerView newsRV;
    ArrayList<NewsModel> list;

    String filteredword = "all";


    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentNewsBinding.inflate(inflater, container, false);


//              View view =   inflater.inflate(R.layout.fragment_news, container, false);

        newsRV = binding.newsRV;

        list = new ArrayList<>();

        list.add(new NewsModel("this is the first data from the news fragment "));
        list.add(new NewsModel("this is the second data from the news fragment "));
        list.add(new NewsModel("this is the third data from the news fragment "));
        list.add(new NewsModel("this is the fourth data from the news fragment "));
        list.add(new NewsModel("this is the fifth data from the news fragment "));
        list.add(new NewsModel("this is the sixth data from the news fragment "));
        list.add(new NewsModel("this is the seven data from the news fragment "));
        list.add(new NewsModel("this is the eight data from the news fragment "));


        newsAdapter = new NewsAdapter(list, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        newsRV.setLayoutManager(linearLayoutManager);
        newsRV.setAdapter(newsAdapter);
        newsAdapter.notifyDataSetChanged();

        initSearchWidget();

        binding.first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "FIRST SELECTED", Toast.LENGTH_SHORT).show();
                filterList("first");

            }
        });




        return binding.getRoot();
    }

    public void initSearchWidget() {
        SearchView searchView = binding.problemSearchView;
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("COOL SEARCH HERE ");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                newsAdapter.getFilter().filter(query);
                return false;

            }

            @Override
            public boolean onQueryTextChange(String newText) {

                System.out.println("ENTERED TEXT : " + newText);

//                newsAdapter.getFilter().filter(newText);

                ArrayList<NewsModel> newsModels = new ArrayList<>();


                for (NewsModel newsModel : list) {
                    if (newsModel.getNewsDescription().toLowerCase().contains(newText.toLowerCase())) {
                        newsModels.add(newsModel);
                    }
                }


                NewsAdapter newsAdapter1 = new NewsAdapter(newsModels, getContext());
                newsRV.setAdapter(newsAdapter1);
                newsAdapter.notifyDataSetChanged();

                return false;
            }
        });
    }

    public void filterList(String status) {


        System.out.println("ENTERED TEXT FILTERED  : " + status);

        ArrayList<NewsModel> newsModels = new ArrayList<>();


        for (NewsModel newsModel : list) {
            if (newsModel.getNewsDescription().toLowerCase().contains(status.toLowerCase())) {
                newsModels.add(newsModel);
            }
        }


        NewsAdapter newsAdapter1 = new NewsAdapter(newsModels, getContext());
        newsRV.setAdapter(newsAdapter1);
//        newsAdapter.notifyDataSetChanged();
    }

    public void third(View view) {
        Toast.makeText(getContext(), "THIRD SELECTED", Toast.LENGTH_SHORT).show();
        filterList("third");
    }

    public void second(View view) {
        Toast.makeText(getContext(), "SECOND SELECTED", Toast.LENGTH_SHORT).show();
        filterList("second");
    }

    public void first(View view) {
        Toast.makeText(getContext(), "FIRST SELECTED", Toast.LENGTH_SHORT).show();
//        filterList("first");
    }

    public void allFilterTapped(View view) {
        Toast.makeText(getContext(), "ALL SELECTED", Toast.LENGTH_SHORT).show();
        filteredword = "all";
        NewsAdapter newsAdapter1 = new NewsAdapter(list, getContext());
        newsRV.setAdapter(newsAdapter1);

    }

}