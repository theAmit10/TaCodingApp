package com.example.tacoding.Fragment;

import static java.lang.Thread.sleep;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.tacoding.Adapter.CodingPlatformAdapter;
import com.example.tacoding.Adapter.ProblemAdapter;
import com.example.tacoding.Adapter.ProblemTagsAdapter;
import com.example.tacoding.Api.MySingleton;
import com.example.tacoding.Model.CodingPlatformModel;
import com.example.tacoding.Model.ProblemModel;
import com.example.tacoding.Model.ProblemTagModel;
import com.example.tacoding.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.spec.ECField;
import java.util.ArrayList;

public class ProblemFragment extends Fragment {

    // for coding platform
    // FOR PROBLEM TAGS
    RecyclerView problemTagRV;
    ArrayList<ProblemTagModel> list;


    // FOR PROBLEM RV
    RecyclerView problemRV;
    ArrayList<ProblemModel> problemList;
    ProblemAdapter problemAdapter;

    public ProblemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Thread loadProblemThread = new Thread(){
            public void run(){
                try {
                    loadProblem();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        loadProblemThread.start();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_problem, container, false);

        // for coding platform
        problemTagRV = view.findViewById(R.id.problemTagRV);
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


        ProblemTagsAdapter problemTagsAdapter = new ProblemTagsAdapter(list, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        problemTagRV.setLayoutManager(linearLayoutManager);
        problemTagRV.setNestedScrollingEnabled(false);
        problemTagRV.setAdapter(problemTagsAdapter);

        // FOR PROBLEM RECYCLER VIEW
        problemRV = view.findViewById(R.id.problemRV);
        problemList = new ArrayList<>();
//        problemList.add(new ProblemModel(R.drawable.ic_codechef_svgrepo_com, "Long Coding Challenge", "XOR Swap "));
//        problemList.add(new ProblemModel(R.drawable.ic_codeforces_svgrepo_com, "Short Coding Challenge", "Swap "));
//        problemList.add(new ProblemModel(R.drawable.ic_hackerrank_svgrepo_com, "Coding Challenge", "n-queen Problem "));
//        problemList.add(new ProblemModel(R.drawable.ic_hackerearth_svgrepo_com, "Starter Challenge", "kadeane problem"));
//        problemList.add(new ProblemModel(R.drawable.ic_codechef_svgrepo_com, "Long Coding Challenge", "XOR Swap "));
//        problemList.add(new ProblemModel(R.drawable.ic_leetcode_svgrepo_com, "Coding Challenge", "bit manipulation"));
//        problemList.add(new ProblemModel(R.drawable.ic_codechef_svgrepo_com, "Long Coding Challenge", "gready Problems"));

        problemAdapter = new ProblemAdapter(problemList, getContext());
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        problemRV.setLayoutManager(linearLayoutManager1);
        problemRV.setNestedScrollingEnabled(false);
        problemRV.setAdapter(problemAdapter);


        return view;
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

                                if(tagJsonArray != null){
                                    for(int k=0; k<tagJsonArray.length(); k++){
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
                                    }catch (Exception e){
                                        e.printStackTrace();
                                        problemModel.setRating("1100");
                                    }

                                if(problemList.size() <400){
                                    problemList.add(problemModel);}
                                else{

                                }
                                    System.out.println("TAGS ARE : " +problemModel.getTags());
                                    if(problemModel.getTags().size() != 0){
                                        System.out.println("First TAGS  : " +problemModel.getTags().get(0));
                                    }


//                                System.out.println("THis is Amit " + problemModel.getTags());

                            }
                            problemAdapter.updateProblem(problemList);

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

// Access the RequestQueue through your singleton class.
        MySingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
    }


}