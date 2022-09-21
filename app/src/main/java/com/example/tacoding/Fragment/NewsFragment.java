package com.example.tacoding.Fragment;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.tacoding.Adapter.NewsAdapter;
import com.example.tacoding.Model.NewsModel;
import com.example.tacoding.R;
import com.example.tacoding.databinding.FragmentNewsBinding;


import java.util.ArrayList;

public class NewsFragment extends Fragment {

    FragmentNewsBinding binding;
    NewsAdapter newsAdapter;

    RecyclerView newsRV;
    ArrayList<NewsModel> list;

    ArrayList<String> filteredword = new ArrayList<>();
    String currentString = "";
    SearchView searchView;

    private Button allButton, firstButton, secondButton, thirdButton;

    private  int white, darkGray, red;







    public NewsFragment() {
        // Required empty public constructor
    }






    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        filteredword.add("all");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNewsBinding.inflate(inflater, container, false);


        // initing button
        allButton = binding.allFilter;
        firstButton = binding.first;
        secondButton = binding.second;
        thirdButton = binding.third;

        //inting color
        white = ContextCompat.getColor(getContext(), R.color.white);
        red = ContextCompat.getColor(getContext(), R.color.teal_200);
        darkGray = ContextCompat.getColor(getContext(), R.color.black);


        lookSelected(allButton);


//              View view =   inflater.inflate(R.layout.fragment_news, container, false);

        newsRV = binding.newsRV;

        list = new ArrayList<>();

        list.add(new NewsModel("this is the first data from the news fragment "));
        list.add(new NewsModel(" second data go goa gone movie is not bad "));
        list.add(new NewsModel("this is the third movie id phir hera pheri id one of the greatest movie of all time "));
        list.add(new NewsModel("this fourth movie is dhoom machala "));
        list.add(new NewsModel("this is the fifth five jab chot kabhi mera lag jati ha"));
        list.add(new NewsModel("this is the sixth kabhi kushi kabhi gum "));
        list.add(new NewsModel("this is the seven data sath samunder par ma tera  "));
        list.add(new NewsModel("this is the eight ooh ohh jana jana dudha tujha dewana "));


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
                first(view);
                System.out.println("currentString first : " +currentString);

            }
        });

        binding.second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "SECOND SELECTED", Toast.LENGTH_SHORT).show();
                second(view);

                System.out.println("currentString second : " +currentString);
            }
        });

        binding.third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "THIRD SELECTED", Toast.LENGTH_SHORT).show();
                third(view);

                System.out.println("currentString third : " +currentString);

            }
        });

        binding.allFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "All SELECTED", Toast.LENGTH_SHORT).show();
                allFilterTapped(view);

            }
        });




        return binding.getRoot();
    }


    private void lookSelected(Button parsedButton){
        parsedButton.setTextColor(red);
        parsedButton.setBackgroundColor(darkGray);

    }

    private void lookUnSelected(Button parsedButton){
        parsedButton.setTextColor(red);
        parsedButton.setBackgroundColor(white);

    }

    private void unSelectedAllFilterButton(){
        lookUnSelected(allButton);
        lookUnSelected(firstButton);
        lookUnSelected(secondButton);
        lookUnSelected(thirdButton);
    }

    public void initSearchWidget() {
        searchView = binding.problemSearchView;
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("COOL SEARCH HERE ");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

//                newsAdapter.getFilter().filter(query);
                return false;

            }

            @Override
            public boolean onQueryTextChange(String newText) {

                currentString = newText;

                System.out.println("ENTERED TEXT : " + newText);

                ArrayList<NewsModel> newsModels = new ArrayList<>();


                for (NewsModel newsModel : list) {
                    if (newsModel.getNewsDescription().toLowerCase().contains(newText.toLowerCase())) {
                        if(filteredword.contains("all")){
                            newsModels.add(newsModel);
                        }else {
                            for(String filter : filteredword){
                                if(newsModel.getNewsDescription().toLowerCase().contains(filter)){
                                    newsModels.add(newsModel);
                                }
                            }
                        }
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

        if(status != null && !filteredword.contains(status))
            filteredword.add(status);

        System.out.println("ENTERED TEXT FILTERED  : " + status);

        ArrayList<NewsModel> newsModels = new ArrayList<>();


        for (NewsModel newsModel : list) {
            for(String filter : filteredword){
                System.out.println("LIST DATA : "+filter);
                if (newsModel.getNewsDescription().toLowerCase().contains(filter.toLowerCase())) {
                    if(currentString == ""){
                        System.out.println("currentString  TA : " +newsModel.getNewsDescription());
                        newsModels.add(newsModel);
                    }else {
                        if (newsModel.getNewsDescription().toLowerCase().contains(currentString.toLowerCase())){
                            newsModels.add(newsModel);
                        }
                    }
                }

            }
        }


        NewsAdapter newsAdapter1 = new NewsAdapter(newsModels, getContext());
        newsRV.setAdapter(newsAdapter1);
        newsAdapter.notifyDataSetChanged();
    }

    public void third(View view) {
        filteredword.remove("all");
        filterList("third");
        lookSelected(thirdButton);
        lookUnSelected(allButton);
        Toast.makeText(getContext(), "THIRD SELECTED", Toast.LENGTH_SHORT).show();


    }

    public void second(View view) {
        filteredword.remove("all");
        filterList("second");
        lookSelected(secondButton);
        lookUnSelected(allButton);
        Toast.makeText(getContext(), "SECOND SELECTED", Toast.LENGTH_SHORT).show();


    }

    public void first(View view) {

        filteredword.remove("all");
        filterList("first");
        lookSelected(firstButton);
        lookUnSelected(allButton);
        Toast.makeText(getContext(), "FIRST SELECTED", Toast.LENGTH_SHORT).show();


    }

    public void allFilterTapped(View view) {
        filteredword.clear();
        filteredword.add("all");
        searchView.setQuery("",false);
        searchView.clearFocus();

        unSelectedAllFilterButton();
        lookSelected(allButton);


        Toast.makeText(getContext(), "ALL SELECTED", Toast.LENGTH_SHORT).show();

        NewsAdapter newsAdapter1 = new NewsAdapter(list, getContext());
        newsRV.setAdapter(newsAdapter1);

    }

}