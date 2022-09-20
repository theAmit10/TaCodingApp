package com.example.tacoding.Fragment;

import static java.lang.Thread.sleep;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.tacoding.Adapter.ProblemAdapter;
import com.example.tacoding.Api.MySingleton;
import com.example.tacoding.Model.ProblemModel;
import com.example.tacoding.Model.ProblemTagModel;
import com.example.tacoding.R;
import com.example.tacoding.databinding.FragmentProblemBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProblemFragment extends Fragment {

    FragmentProblemBinding binding;

    SearchView searchView;

    private String selectedFilter = "";


    ArrayList<String> filteredWord = new ArrayList<>();
    String currentSearchText = "";

    String lowRange = "800";
    String maxRange = "3500";

    // for coding platform
    // FOR PROBLEM TAGS
    ArrayList<ProblemTagModel> list;

    WebView problemWebView;


    // FOR PROBLEM RV
    RecyclerView problemRV;
    ArrayList<ProblemModel> problemList;
    ProblemAdapter problemAdapter;

    Button allButton, twosat, binarySearch, bitmasks, bruteForce, chineseReaminderTherem, combinatorics, constructiveAlgorithum, dataStructure, dfsAndSimilar, divideAndConquer,
            dp, dsu, expressionParsing, fft, flows, games, geometry, graphMatchings, graphs,
            greedy, hashing, implementation, interactive, math, metrices, meetInTheMiddle,
            numberTheory, probablities, schedules, shortestPath, sortings, stringSuffixStructure,
            strings, ternarySearch, trees, twoPointers;

    Button beginners, intermediate, advanced;

    private int white, darkGray, red;


    public ProblemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        filteredWord.add("all");

        ExecutorService loadProblemThread = Executors.newSingleThreadExecutor();
        loadProblemThread.execute(new Runnable() {
            @Override
            public void run() {
                try {

                    loadProblem();


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_problem, container, false);

        binding = FragmentProblemBinding.inflate(inflater, container, false);

        // for coding platform

        list = new ArrayList<>();

        list.add(new ProblemTagModel("2-sat"));
        list.add(new ProblemTagModel("binary search"));
        list.add(new ProblemTagModel("bitmasks"));
        list.add(new ProblemTagModel("brute force"));
        list.add(new ProblemTagModel("chinese remainder theorem"));
        list.add(new ProblemTagModel("combinatorics"));
        list.add(new ProblemTagModel("constructive algorithms"));
        list.add(new ProblemTagModel("data structures"));
        list.add(new ProblemTagModel("dfs and similar"));
        list.add(new ProblemTagModel("divide and conquer"));
        list.add(new ProblemTagModel("dp"));
        list.add(new ProblemTagModel("dsu"));
        list.add(new ProblemTagModel("expression parsing"));
        list.add(new ProblemTagModel("fft"));
        list.add(new ProblemTagModel("flows"));
        list.add(new ProblemTagModel("games"));
        list.add(new ProblemTagModel("geometry"));
        list.add(new ProblemTagModel("graph matchings"));
        list.add(new ProblemTagModel("graphs"));
        list.add(new ProblemTagModel("greedy"));
        list.add(new ProblemTagModel("hashing"));
        list.add(new ProblemTagModel("implementation"));
        list.add(new ProblemTagModel("interactive"));
        list.add(new ProblemTagModel("math"));
        list.add(new ProblemTagModel("metrices"));
        list.add(new ProblemTagModel("meet-in-the-middle"));
        list.add(new ProblemTagModel("number theory"));
        list.add(new ProblemTagModel("probablities"));
        list.add(new ProblemTagModel("schedules"));
        list.add(new ProblemTagModel("shortest paths"));
        list.add(new ProblemTagModel("sortings"));
        list.add(new ProblemTagModel("string suffix structures"));
        list.add(new ProblemTagModel("strings"));
        list.add(new ProblemTagModel("ternary search"));
        list.add(new ProblemTagModel("trees"));
        list.add(new ProblemTagModel("two pointers"));


        // FOR PROBLEM RECYCLER VIEW
        problemRV = binding.problemRV;
        problemList = new ArrayList<>();
//        problemList.add(new ProblemModel(R.drawable.ic_codechef_svgrepo_com, "Long Coding Challenge", "XOR Swap "));
//        problemList.add(new ProblemModel(R.drawable.ic_codeforces_svgrepo_com, "Short Coding Challenge", "Swap "));
//        problemList.add(new ProblemModel(R.drawable.ic_hackerrank_svgrepo_com, "Coding Challenge", "n-queen Problem "));
//        problemList.add(new ProblemModel(R.drawable.ic_hackerearth_svgrepo_com, "Starter Challenge", "kadeane problem"));
//        problemList.add(new ProblemModel(R.drawable.ic_codechef_svgrepo_com, "Long Coding Challenge", "XOR Swap "));
//        problemList.add(new ProblemModel(R.drawable.ic_leetcode_svgrepo_com, "Coding Challenge", "bit manipulation"));
//        problemList.add(new ProblemModel(R.drawable.ic_codechef_svgrepo_com, "Long Coding Challenge", "gready Problems"));


//        problemAdapter = new ProblemAdapter(problemList, getContext());
//        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//        problemRV.setLayoutManager(linearLayoutManager1);
//        problemRV.setNestedScrollingEnabled(false);
//        problemRV.setAdapter(problemAdapter);


        problemWebView = binding.problemWebView;

        problemWebView = new WebView(getContext());

        // binding the all button
        buttonBinding();

        beginners = binding.BeginnersBtn;
        intermediate = binding.IntermediatesBtn;
        advanced = binding.AdvancedBtn;

        lookSelected(allButton);


        binding.allFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allFilterTapped(view);
            }
        });


        binding.binarySearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binarySearch(view);
            }
        });

        binding.twoSat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                twoSat(view);
            }
        });


        binding.bitmasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bitmasks(view);
            }
        });

        binding.bruteForce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bruteForce(view);
            }
        });

        binding.chineseRemainderTheorem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chineseRemainderTheorem(view);
            }
        });

        binding.combinatorics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                combinatorics(view);
            }
        });

        binding.constructiveAlgorithms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                constructiveAlgorithms(view);
            }
        });

        binding.dataStructures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataStructures(view);
            }
        });

        binding.dfsAndSimilar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dfsAndSimilar(view);
            }
        });

        binding.divideAndConquer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                divideAndConquer(view);
            }
        });

        binding.dp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dp(view);
            }
        });

        binding.dsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dsu(view);
            }
        });

        binding.expressionParsing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expressionParsing(view);
            }
        });

        binding.fft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fft(view);
            }
        });

        binding.flows.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flows(view);
            }
        });

        binding.games.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                games(view);
            }
        });

        binding.geometry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                geometry(view);
            }
        });

        binding.graphMatchings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                graphMatchings(view);
            }
        });

        binding.graphs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                graphs(view);
            }
        });

        binding.greedy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                greedy(view);
            }
        });

        binding.hashing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hashing(view);
            }
        });

        binding.interactive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interactive(view);
            }
        });

        binding.math.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                math(view);
            }
        });

        binding.metrices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                metrices(view);
            }
        });

        binding.meetInTheMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                meetInTheMiddle(view);
            }
        });

        binding.numberTheory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberTheory(view);
            }
        });

        binding.probablities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                probablities(view);
            }
        });

        binding.schedules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                schedules(view);
            }
        });

        binding.shortestPaths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shortestPaths(view);
            }
        });

        binding.sortings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortings(view);
            }
        });

        binding.stringSuffixStructures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringSuffixStructures(view);
            }
        });

        binding.strings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strings(view);
            }
        });

        binding.ternarySearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ternarySearch(view);
            }
        });

        binding.trees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trees(view);
            }
        });

        binding.implementation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                implementation(view);
            }
        });

        binding.twoPointers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                twoPointers(view);
            }
        });


        binding.BeginnersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                beginners(view);
            }
        });

        binding.IntermediatesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intermediate(view);
            }
        });


        binding.AdvancedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                advanced(view);
            }
        });


        return binding.getRoot();
    }

    private void buttonBinding() {
        allButton = binding.allFilter;
        twosat = binding.twoSat;
        binarySearch = binding.binarySearch;
        bitmasks = binding.bitmasks;
        bruteForce = binding.bruteForce;
        chineseReaminderTherem = binding.chineseRemainderTheorem;
        combinatorics = binding.combinatorics;
        constructiveAlgorithum = binding.constructiveAlgorithms;
        dataStructure = binding.dataStructures;
        dfsAndSimilar = binding.dfsAndSimilar;
        divideAndConquer = binding.divideAndConquer;
        dp = binding.dp;
        dsu = binding.dsu;
        expressionParsing = binding.expressionParsing;
        fft = binding.fft;
        flows = binding.flows;
        games = binding.games;
        geometry = binding.geometry;
        graphMatchings = binding.graphMatchings;
        graphs = binding.graphs;
        greedy = binding.greedy;
        hashing = binding.hashing;
        implementation = binding.implementation;
        interactive = binding.interactive;
        math = binding.math;
        metrices = binding.metrices;
        meetInTheMiddle = binding.meetInTheMiddle;
        numberTheory = binding.numberTheory;
        probablities = binding.probablities;
        schedules = binding.schedules;
        shortestPath = binding.shortestPaths;
        sortings = binding.sortings;
        stringSuffixStructure = binding.stringSuffixStructures;
        strings = binding.strings;
        ternarySearch = binding.ternarySearch;
        trees = binding.trees;
        twoPointers = binding.twoPointers;


        //inting color
        white = ContextCompat.getColor(getContext(), R.color.white);
        red = ContextCompat.getColor(getContext(), R.color.teal_200);
        darkGray = ContextCompat.getColor(getContext(), R.color.black);


    }

    private void lookSelected(Button parsedButton) {
        parsedButton.setTextColor(red);
        parsedButton.setBackgroundColor(darkGray);

    }

    private void lookUnSelected(Button parsedButton) {
        parsedButton.setTextColor(red);
        parsedButton.setBackgroundColor(white);

    }

    private void unSelectedAllFilterButton() {
        lookUnSelected(allButton);
        lookUnSelected(twosat);
        lookUnSelected(binarySearch);
        lookUnSelected(bitmasks);
        lookUnSelected(bruteForce);
        lookUnSelected(chineseReaminderTherem);
        lookUnSelected(combinatorics);
        lookUnSelected(constructiveAlgorithum);
        lookUnSelected(dataStructure);
        lookUnSelected(dfsAndSimilar);
        lookUnSelected(divideAndConquer);
        lookUnSelected(dp);
        lookUnSelected(dsu);
        lookUnSelected(expressionParsing);
        lookUnSelected(fft);
        lookUnSelected(flows);
        lookUnSelected(games);
        lookUnSelected(geometry);
        lookUnSelected(graphMatchings);
        lookUnSelected(graphs);
        lookUnSelected(greedy);
        lookUnSelected(hashing);
        lookUnSelected(implementation);
        lookUnSelected(interactive);
        lookUnSelected(math);
        lookUnSelected(metrices);
        lookUnSelected(meetInTheMiddle);
        lookUnSelected(numberTheory);
        lookUnSelected(probablities);
        lookUnSelected(schedules);
        lookUnSelected(shortestPath);
        lookUnSelected(sortings);
        lookUnSelected(stringSuffixStructure);
        lookUnSelected(strings);
        lookUnSelected(ternarySearch);
        lookUnSelected(trees);
        lookUnSelected(twoPointers);
    }


    public void initSearchWidget() {
        searchView = binding.problemSearchView;
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Search through PROBLEM NAME ");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                currentSearchText = newText;


                System.out.println("ENTERED TEXT : " + newText);
                ArrayList<ProblemModel> filterProblemList = new ArrayList<>();

                for (ProblemModel problemModel : problemList) {
                    if (problemModel.getName().toLowerCase().contains(newText.toLowerCase())) {
                        if (filteredWord.contains("all")) {
                            filterProblemList.add(problemModel);
                        } else {
                            for (String filter : filteredWord) {
                                if (problemModel.getName().toLowerCase().contains(filter)) {
                                    filterProblemList.add(problemModel);
                                }
                            }
                        }

                    }
                }


                ProblemAdapter problemAdapter1 = new ProblemAdapter(filterProblemList, getContext());
                problemRV.setAdapter(problemAdapter1);
                problemAdapter.notifyDataSetChanged();

                return false;
            }
        });


    }

    public void loadProblem() {
        String problemUrl = "https://codeforces.com/api/problemset.problems";
//        String problemUrl = "https://codeforces.com/api/problemset.recentStatus?count=10";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, problemUrl, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONObject problemResultJsonObj = response.getJSONObject("result");
                            JSONArray problemJsonArray = problemResultJsonObj.getJSONArray("problems");

                            for (int i = 0; i < problemJsonArray.length(); i++) {
                                JSONObject problemJsonObject = problemJsonArray.getJSONObject(i);
                                JSONArray tagJsonArray = problemJsonObject.getJSONArray("tags");
                                ArrayList<String> tagArray = new ArrayList<String>();

//                                System.out.println("sucess" +problemJsonObject.getString("rating"));

                                if (tagJsonArray != null) {
                                    for (int k = 0; k < tagJsonArray.length(); k++) {
                                        tagArray.add((String) tagJsonArray.get(k));
                                    }
                                }

                                ProblemModel problemModel = new ProblemModel(
                                        problemJsonObject.getString("contestId"),
                                        problemJsonObject.getString("index"),
                                        problemJsonObject.getString("name"),
                                        tagArray
                                );
                                try {
                                    problemModel.setRating(problemJsonObject.getString("rating"));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    problemModel.setRating("1100");
                                }

                                System.out.println("CONTEST ID : " + problemJsonObject.getString("contestId"));
                                System.out.println("INDEX : " + problemJsonObject.getString("index"));
                                System.out.println("NAME : " + problemJsonObject.getString("name"));

                                if (problemList.size() < 400) {
                                    problemList.add(problemModel);
                                } else {

                                }
                                System.out.println("TAGS ARE : " + problemModel.getTags());
                                if (problemModel.getTags().size() != 0) {
                                    System.out.println("First TAGS  : " + problemModel.getTags().get(0));
                                }


                            }
                            problemAdapter = new ProblemAdapter(problemList, getContext());
                            LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                            problemRV.setLayoutManager(linearLayoutManager1);
                            problemRV.setNestedScrollingEnabled(false);
                            problemRV.setAdapter(problemAdapter);
//                            problemAdapter.updateProblem(problemList);

                            initSearchWidget();

                        } catch (JSONException e) {
                            System.out.println("try catch error");
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        System.out.println("volly error");
                        error.printStackTrace();
                    }
                });

        MySingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
    }

    public void filterListRating(String low, String high) {
        lowRange = low;
        maxRange = high;

        int val1 = Integer.parseInt(low);
        int val2 = Integer.parseInt(high);
        ArrayList<ProblemModel> filterProblemList = new ArrayList<>();

        for (ProblemModel problemModel : problemList) {

            if ((Integer.parseInt(problemModel.getRating()) >= val1) && (Integer.parseInt(problemModel.getRating()) <= val2)) {
                System.out.println("Rating  : " + problemModel.getRating());
                filterProblemList.add(problemModel);
            }
        }


        ProblemAdapter problemAdapter1 = new ProblemAdapter(filterProblemList, getContext());
        problemRV.setAdapter(problemAdapter1);

        problemAdapter.notifyDataSetChanged();

    }

    public void filteredList(String status) {

        if (status != null && !filteredWord.contains(status)) {
            filteredWord.add(status);
        }

        System.out.println("ENTERED TEXT FILTERED  : " + status);

        ArrayList<ProblemModel> filterProblemList = new ArrayList<>();

        for (ProblemModel problemModel : problemList) {

            for (String filter : filteredWord) {
                System.out.println("LIST DATA : " + filter);
                if (problemModel.getTags().contains(filter.toLowerCase())) {
                    System.out.println("CONTAING LIST : " + problemModel.getTags());
                    if (currentSearchText == "") {
                        filterProblemList.add(problemModel);
                    } else {
                        if (problemModel.getName().toLowerCase().contains(currentSearchText.toLowerCase())) {
                            filterProblemList.add(problemModel);
                        }
                    }
                }

            }

        }


        ProblemAdapter problemAdapter1 = new ProblemAdapter(filterProblemList, getContext());
        problemRV.setAdapter(problemAdapter1);

        problemAdapter.notifyDataSetChanged();

    }


    public void allFilterTapped(View view) {

        filteredWord.clear();
        filteredWord.add("all");
        searchView.setQuery("", false);
        searchView.clearFocus();

        unSelectedAllFilterButton();
        lookSelected(allButton);

//        Toast.makeText(getContext(), "ALL SELECTED", Toast.LENGTH_SHORT).show();

        ProblemAdapter problemAdapter2 = new ProblemAdapter(problemList, getContext());
        problemRV.setAdapter(problemAdapter2);
//        problemAdapter.notifyDataSetChanged();

    }


    public void advanced(View view) {
        selectedFilter = "ADVANCED";
        lowRange = "2000";
        maxRange = "4000";
        filterListRating(lowRange, maxRange);
        lookSelected(advanced);
        lookUnSelected(beginners);
        lookUnSelected(intermediate);
        Toast.makeText(getContext(), "" + selectedFilter, Toast.LENGTH_SHORT).show();
    }

    public void intermediate(View view) {
        selectedFilter = "INTERMEDIATE";
        lowRange = "900";
        maxRange = "2000";
        filterListRating(lowRange, maxRange);
        lookSelected(intermediate);
        lookUnSelected(advanced);
        lookUnSelected(beginners);
        Toast.makeText(getContext(), "" + selectedFilter, Toast.LENGTH_SHORT).show();
    }

    public void beginners(View view) {
        selectedFilter = "BIGINNERS";
        lowRange = "800";
        maxRange = "900";
        filterListRating(lowRange, maxRange);
        lookSelected(beginners);
        lookUnSelected(intermediate);
        lookUnSelected(advanced);
        Toast.makeText(getContext(), "" + selectedFilter, Toast.LENGTH_SHORT).show();
    }

    public void twoSat(View view) {
        filteredWord.remove("all");
        lookSelected(twosat);
        lookUnSelected(allButton);
        selectedFilter = "2-sat";
        filteredList(selectedFilter);
        Toast.makeText(getContext(), "" + selectedFilter, Toast.LENGTH_SHORT).show();
    }

    public void bitmasks(View view) {
        filteredWord.remove("all");
        lookSelected(bitmasks);
        lookUnSelected(allButton);
        selectedFilter = "bitmasks";
        filteredList(selectedFilter);
        Toast.makeText(getContext(), "" + selectedFilter, Toast.LENGTH_SHORT).show();
    }

    public void bruteForce(View view) {
        filteredWord.remove("all");
        lookSelected(bruteForce);
        lookUnSelected(allButton);
        selectedFilter = "brute force";
        filteredList(selectedFilter);
        Toast.makeText(getContext(), "" + selectedFilter, Toast.LENGTH_SHORT).show();
    }

    public void chineseRemainderTheorem(View view) {
        filteredWord.remove("all");
        lookSelected(chineseReaminderTherem);
        lookUnSelected(allButton);
        selectedFilter = "chinese remainder theorem";
        filteredList(selectedFilter);
        Toast.makeText(getContext(), "" + selectedFilter, Toast.LENGTH_SHORT).show();
    }

    public void combinatorics(View view) {
        filteredWord.remove("all");
        lookSelected(combinatorics);
        lookUnSelected(allButton);
        selectedFilter = "combinatorics";
        filteredList(selectedFilter);
        Toast.makeText(getContext(), "" + selectedFilter, Toast.LENGTH_SHORT).show();
    }

    public void constructiveAlgorithms(View view) {
        filteredWord.remove("all");
        lookSelected(constructiveAlgorithum);
        lookUnSelected(allButton);
        selectedFilter = "constructive algorithms";
        filteredList(selectedFilter);
        Toast.makeText(getContext(), "" + selectedFilter, Toast.LENGTH_SHORT).show();
    }


    public void dataStructures(View view) {
        filteredWord.remove("all");
        lookSelected(dataStructure);
        lookUnSelected(allButton);
        selectedFilter = "data structures";
        filteredList(selectedFilter);
        Toast.makeText(getContext(), "" + selectedFilter, Toast.LENGTH_SHORT).show();
    }

    public void divideAndConquer(View view) {
        filteredWord.remove("all");
        lookSelected(divideAndConquer);
        lookUnSelected(allButton);
        selectedFilter = "divide and conquer";
        filteredList(selectedFilter);
        Toast.makeText(getContext(), "" + selectedFilter, Toast.LENGTH_SHORT).show();
    }

    public void dp(View view) {
        filteredWord.remove("all");
        lookSelected(dp);
        lookUnSelected(allButton);
        selectedFilter = "dp";
        filteredList(selectedFilter);
        Toast.makeText(getContext(), "" + selectedFilter, Toast.LENGTH_SHORT).show();
    }

    public void dsu(View view) {
        filteredWord.remove("all");
        lookSelected(dsu);
        lookUnSelected(allButton);
        selectedFilter = "dsu";
        filteredList(selectedFilter);
        Toast.makeText(getContext(), "" + selectedFilter, Toast.LENGTH_SHORT).show();
    }

    public void expressionParsing(View view) {
        filteredWord.remove("all");
        lookSelected(expressionParsing);
        lookUnSelected(allButton);
        selectedFilter = "expression parsing";
        filteredList(selectedFilter);
        Toast.makeText(getContext(), "" + selectedFilter, Toast.LENGTH_SHORT).show();
    }

    public void fft(View view) {
        filteredWord.remove("all");
        lookSelected(fft);
        lookUnSelected(allButton);
        selectedFilter = "fft";
        filteredList(selectedFilter);
        Toast.makeText(getContext(), "" + selectedFilter, Toast.LENGTH_SHORT).show();
    }

    public void games(View view) {
        filteredWord.remove("all");
        lookSelected(games);
        lookUnSelected(allButton);
        selectedFilter = "games";
        filteredList(selectedFilter);
        Toast.makeText(getContext(), "" + selectedFilter, Toast.LENGTH_SHORT).show();
    }

    public void geometry(View view) {
        filteredWord.remove("all");
        lookSelected(geometry);
        lookUnSelected(allButton);
        selectedFilter = "geometry";
        filteredList(selectedFilter);
        Toast.makeText(getContext(), "" + selectedFilter, Toast.LENGTH_SHORT).show();
    }

    public void graphMatchings(View view) {
        filteredWord.remove("all");
        lookSelected(graphMatchings);
        lookUnSelected(allButton);
        selectedFilter = "graph matchings";
        filteredList(selectedFilter);
        Toast.makeText(getContext(), "" + selectedFilter, Toast.LENGTH_SHORT).show();
    }

    public void graphs(View view) {
        filteredWord.remove("all");
        lookSelected(graphs);
        lookUnSelected(allButton);
        selectedFilter = "graphs";
        filteredList(selectedFilter);
        Toast.makeText(getContext(), "" + selectedFilter, Toast.LENGTH_SHORT).show();
    }

    public void greedy(View view) {
        filteredWord.remove("all");
        lookSelected(greedy);
        lookUnSelected(allButton);
        selectedFilter = "greedy";
        filteredList(selectedFilter);
        Toast.makeText(getContext(), "" + selectedFilter, Toast.LENGTH_SHORT).show();
    }

    public void hashing(View view) {
        filteredWord.remove("all");
        lookSelected(hashing);
        lookUnSelected(allButton);
        selectedFilter = "hashing";
        filteredList(selectedFilter);
        Toast.makeText(getContext(), "" + selectedFilter, Toast.LENGTH_SHORT).show();
    }

    public void interactive(View view) {
        filteredWord.remove("all");
        lookSelected(interactive);
        lookUnSelected(allButton);
        selectedFilter = "interactive";
        filteredList(selectedFilter);
        Toast.makeText(getContext(), "" + selectedFilter, Toast.LENGTH_SHORT).show();
    }

    public void math(View view) {
        filteredWord.remove("all");
        lookSelected(math);
        lookUnSelected(allButton);
        selectedFilter = "math";
        filteredList(selectedFilter);
        Toast.makeText(getContext(), "" + selectedFilter, Toast.LENGTH_SHORT).show();
    }

    public void metrices(View view) {
        filteredWord.remove("all");
        lookSelected(metrices);
        lookUnSelected(allButton);
        selectedFilter = "metrices";
        filteredList(selectedFilter);
        Toast.makeText(getContext(), "" + selectedFilter, Toast.LENGTH_SHORT).show();

    }

    public void meetInTheMiddle(View view) {
        filteredWord.remove("all");
        lookSelected(meetInTheMiddle);
        lookUnSelected(allButton);
        selectedFilter = "meet-in-the-middle";
        filteredList(selectedFilter);
        Toast.makeText(getContext(), "" + selectedFilter, Toast.LENGTH_SHORT).show();
    }

    public void numberTheory(View view) {
        filteredWord.remove("all");
        lookSelected(numberTheory);
        lookUnSelected(allButton);
        selectedFilter = "number theory";
        filteredList(selectedFilter);
        Toast.makeText(getContext(), "" + selectedFilter, Toast.LENGTH_SHORT).show();
    }

    public void probablities(View view) {
        filteredWord.remove("all");
        lookSelected(probablities);
        lookUnSelected(allButton);
        selectedFilter = "probablities";
        filteredList(selectedFilter);
        Toast.makeText(getContext(), "" + selectedFilter, Toast.LENGTH_SHORT).show();
    }

    public void schedules(View view) {
        filteredWord.remove("all");
        lookSelected(schedules);
        lookUnSelected(allButton);
        selectedFilter = "schedules";
        filteredList(selectedFilter);
        Toast.makeText(getContext(), "" + selectedFilter, Toast.LENGTH_SHORT).show();
    }

    public void shortestPaths(View view) {
        filteredWord.remove("all");
        lookSelected(shortestPath);
        lookUnSelected(allButton);
        selectedFilter = "shortest paths";
        filteredList(selectedFilter);
        Toast.makeText(getContext(), "" + selectedFilter, Toast.LENGTH_SHORT).show();
    }

    public void sortings(View view) {
        filteredWord.remove("all");
        lookSelected(sortings);
        lookUnSelected(allButton);
        selectedFilter = "sortings";
        filteredList(selectedFilter);
        Toast.makeText(getContext(), "" + selectedFilter, Toast.LENGTH_SHORT).show();
    }

    public void stringSuffixStructures(View view) {
        filteredWord.remove("all");
        lookSelected(stringSuffixStructure);
        lookUnSelected(allButton);
        selectedFilter = "string suffix structures";
        filteredList(selectedFilter);
        Toast.makeText(getContext(), "" + selectedFilter, Toast.LENGTH_SHORT).show();
    }

    public void strings(View view) {
        filteredWord.remove("all");
        lookSelected(strings);
        lookUnSelected(allButton);
        selectedFilter = "strings";
        filteredList(selectedFilter);
        Toast.makeText(getContext(), "" + selectedFilter, Toast.LENGTH_SHORT).show();
    }

    public void ternarySearch(View view) {
        filteredWord.remove("all");
        lookSelected(ternarySearch);
        lookUnSelected(allButton);
        selectedFilter = "ternary search";
        filteredList(selectedFilter);
        Toast.makeText(getContext(), "" + selectedFilter, Toast.LENGTH_SHORT).show();
    }

    public void trees(View view) {
        filteredWord.remove("all");
        lookSelected(trees);
        lookUnSelected(allButton);
        selectedFilter = "trees";
        filteredList(selectedFilter);
        Toast.makeText(getContext(), "" + selectedFilter, Toast.LENGTH_SHORT).show();

    }

    public void implementation(View view) {
        filteredWord.remove("all");
        lookSelected(implementation);
        lookUnSelected(allButton);
        selectedFilter = "implementation";
        filteredList(selectedFilter);
        Toast.makeText(getContext(), "" + selectedFilter, Toast.LENGTH_SHORT).show();
    }

    public void twoPointers(View view) {
        filteredWord.remove("all");
        lookSelected(twoPointers);
        lookUnSelected(allButton);
        selectedFilter = "two pointers";
        filteredList(selectedFilter);
        Toast.makeText(getContext(), "" + selectedFilter, Toast.LENGTH_SHORT).show();
    }

    public void binarySearch(View view) {
        filteredWord.remove("all");
        lookSelected(binarySearch);
        lookUnSelected(allButton);
        selectedFilter = "binary search";
        filteredList(selectedFilter);
        Toast.makeText(getContext(), "" + selectedFilter, Toast.LENGTH_SHORT).show();
    }

    public void dfsAndSimilar(View view) {
        filteredWord.remove("all");
        lookSelected(dfsAndSimilar);
        lookUnSelected(allButton);
        selectedFilter = "dfs and similar";
        filteredList(selectedFilter);
        Toast.makeText(getContext(), "" + selectedFilter, Toast.LENGTH_SHORT).show();
    }

    public void flows(View view) {
        filteredWord.remove("all");
        lookSelected(flows);
        lookUnSelected(allButton);
        selectedFilter = "flows";
        filteredList(selectedFilter);
        Toast.makeText(getContext(), "" + selectedFilter, Toast.LENGTH_SHORT).show();
    }
}