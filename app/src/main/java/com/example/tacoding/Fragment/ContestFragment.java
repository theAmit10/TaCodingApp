package com.example.tacoding.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tacoding.Adapter.CodingPlatformAdapter;
import com.example.tacoding.Adapter.ContestAdapter;
import com.example.tacoding.Adapter.TopCoderAdapter;
import com.example.tacoding.Api.MySingleton;
import com.example.tacoding.Model.CodingPlatformModel;
import com.example.tacoding.Model.ContestModel;
import com.example.tacoding.Model.TopCoderModel;
import com.example.tacoding.R;
import com.github.thunder413.datetimeutils.DateTimeUnits;
import com.github.thunder413.datetimeutils.DateTimeUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class ContestFragment extends Fragment {

    // for coding platform
    RecyclerView codingPlatformRv;
    ArrayList<CodingPlatformModel> list;

    //for top coder
    RecyclerView topCoderRv;
    ArrayList<TopCoderModel> topcoderList;

    //For Contest
    RecyclerView contestRv;
    ArrayList<ContestModel> contestList;
    ContestAdapter contestAdapter;

    public ContestFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadContest();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_contest, container, false);

        // for coding platform
        codingPlatformRv = view.findViewById(R.id.codingPlatformRV);
        list = new ArrayList<>();
        list.add(new CodingPlatformModel(R.drawable.ic_codechef_svgrepo_com));
        list.add(new CodingPlatformModel(R.drawable.ic_hackerearth_svgrepo_com));
        list.add(new CodingPlatformModel(R.drawable.ic_codeforces_svgrepo_com));
        list.add(new CodingPlatformModel(R.drawable.ic_hackerrank_svgrepo_com));
        list.add(new CodingPlatformModel(R.drawable.ic_leetcode_svgrepo_com));
        list.add(new CodingPlatformModel(R.drawable.ic_codeforces_svgrepo_com));
        list.add(new CodingPlatformModel(R.drawable.ic_leetcode_svgrepo_com));
        list.add(new CodingPlatformModel(R.drawable.ic_codeforces_svgrepo_com));
        list.add(new CodingPlatformModel(R.drawable.ic_people));

        CodingPlatformAdapter codingPlatformAdapter = new CodingPlatformAdapter(list,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        codingPlatformRv.setLayoutManager(linearLayoutManager);
        codingPlatformRv.setNestedScrollingEnabled(false);
        codingPlatformRv.setAdapter(codingPlatformAdapter);

        // For Top Coder
        topCoderRv = view.findViewById(R.id.topCoderRV);
        topcoderList = new ArrayList<>();
        topcoderList.add(new TopCoderModel(R.drawable.p7,"Wasu","Grand Master","CodeForces"));
        topcoderList.add(new TopCoderModel(R.drawable.p1,"James","Master","CodeChef"));
        topcoderList.add(new TopCoderModel(R.drawable.p2,"Goku","Candidate","Hackerrank"));
        topcoderList.add(new TopCoderModel(R.drawable.p3,"Gyan","Grand Master","Codechef"));
        topcoderList.add(new TopCoderModel(R.drawable.p4,"Sinchan","intermediate","CodeForces"));
        topcoderList.add(new TopCoderModel(R.drawable.p5,"Nobita","Master","CodeForces"));
        topcoderList.add(new TopCoderModel(R.drawable.p6,"Raju","Candidate Master","CodeForces"));
        topcoderList.add(new TopCoderModel(R.drawable.p2,"Rakesh","Grand Master","CodeForces"));

        TopCoderAdapter topCoderAdapter = new TopCoderAdapter(topcoderList, getContext());
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        topCoderRv.setLayoutManager(linearLayoutManager1);
        topCoderRv.setNestedScrollingEnabled(false);
        topCoderRv.setAdapter(topCoderAdapter);


        // For Contest List
        contestRv = view.findViewById(R.id.contestRv);
        contestList =new ArrayList<>();
//        contestList.add(new ContestModel(R.drawable.ic_codechef_svgrepo_com,R.drawable.ic_baseline_add_alarm_24,"CodeChef","Long Challenge","9:00 PM","11: 00 PM"));
//        contestList.add(new ContestModel(R.drawable.ic_codeforces_svgrepo_com,R.drawable.ic_baseline_add_alarm_24,"CodeForces","Coding Challenge","9:00 PM","11: 00 PM"));
//        contestList.add(new ContestModel(R.drawable.ic_hackerearth_svgrepo_com,R.drawable.ic_baseline_add_alarm_24,"HackerEarth","Starter Pack Challenge","9:00 PM","11: 00 PM"));
//        contestList.add(new ContestModel(R.drawable.ic_hackerrank_svgrepo_com,R.drawable.ic_baseline_add_alarm_24,"HackerRank"," Challenge","9:00 PM","11: 00 PM"));
//        contestList.add(new ContestModel(R.drawable.ic_leetcode_svgrepo_com,R.drawable.ic_baseline_add_alarm_24,"LeetCode","Long Challenge","9:00 PM","11: 00 PM"));
//        contestList.add(new ContestModel(R.drawable.ic_codechef_svgrepo_com,R.drawable.ic_baseline_add_alarm_24,"CodeChef","Long Challenge","9:00 PM","11: 00 PM"));
//        contestList.add(new ContestModel(R.drawable.ic_codeforces_svgrepo_com,R.drawable.ic_baseline_add_alarm_24,"CodeForces","Long Challenge","9:00 PM","11: 00 PM"));


        contestAdapter = new ContestAdapter(contestList, getContext());
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        contestRv.setLayoutManager(linearLayoutManager2);
        contestRv.setNestedScrollingEnabled(false);
        contestRv.setAdapter(contestAdapter);

        return view;
    }



    public void  loadContest(){
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = "https://codeforces.com/api/contest.list";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray contestJsonArray = response.getJSONArray("result");
                            System.out.println("success");

                                for (int i = 0; i < contestJsonArray.length(); i++) {
                                    JSONObject contestJsonObject = contestJsonArray.getJSONObject(i);

                                    ContestModel contestModel = new ContestModel(
                                            contestJsonObject.getString("type"),
                                            contestJsonObject.getString("name"),
                                            contestJsonObject.getString("startTimeSeconds"),
                                            contestJsonObject.getString("durationSeconds")
                                    );

                                    // Converting Second to Date and time
                                    DateTimeUtils.setTimeZone("UTC");
                                    Date sdate = DateTimeUtils.formatDate(Long.parseLong(contestModel.getStartDate()), DateTimeUnits.SECONDS);
                                    String strDate = String.valueOf(sdate);
                                    String syear =  strDate.substring(strDate.length()-4);
                                    System.out.println("Year : " +syear);
                                    int year = Integer.parseInt(syear);

                                    // setting end Date
                                    Date edate = DateTimeUtils.formatDate(Long.parseLong(contestModel.getEndDate()), DateTimeUnits.SECONDS);
                                    String eDate = String.valueOf(edate);

                                    contestModel.setStartDate(strDate);
                                    contestModel.setEndDate(eDate);
                                    if(year >= 2021) {
                                        contestList.add(contestModel);
                                    }

                                    System.out.println("Start TIME : " +contestModel.getStartDate() );
                                    System.out.println("Contest Titile : " + contestModel.getContestTitle());
                                    System.out.println("Contest DESC : " + contestModel.getContestDescription());


                                }


                        contestAdapter.updateContest(contestList);
                            System.out.println("success Amit");
                        } catch (JSONException e) {
                            System.out.println("Error Amit");
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        System.out.println("Error Found " +error.getLocalizedMessage());

                    }
                });


        MySingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);


    }

}