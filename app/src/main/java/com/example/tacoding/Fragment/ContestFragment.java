package com.example.tacoding.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.tacoding.Adapter.CodingPlatformAdapter;
import com.example.tacoding.Adapter.ContestAdapter;
import com.example.tacoding.Adapter.TopCoderAdapter;
import com.example.tacoding.Api.MySingleton;
import com.example.tacoding.Model.CodingPlatformModel;
import com.example.tacoding.Model.ContestModel;
import com.example.tacoding.Model.TopCoderModel;
import com.example.tacoding.R;
import com.example.tacoding.databinding.FragmentContestBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ContestFragment extends Fragment {

    FragmentContestBinding binding;


    // for coding platform
    RecyclerView codingPlatformRv;
    ArrayList<CodingPlatformModel> list;
    CodingPlatformAdapter codingPlatformAdapter;

    ArrayList<String> selectedPlatformSet = new ArrayList<>();


    //for top coder
    RecyclerView topCoderRv;
    ArrayList<TopCoderModel> topcoderList;

    //For Contest
    RecyclerView contestRv;
    ArrayList<ContestModel> contestList;
    ContestAdapter contestAdapter;
    public static Map<String, Integer> map = new HashMap<String, Integer>();


    public ContestFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Thread thread = new Thread() {
            public void run() {
                try {
                    loadContest();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
//        loadContest();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_contest, container, false);
        binding = FragmentContestBinding.inflate(inflater,container,false);

        // for coding platform
//        codingPlatformRv = view.findViewById(R.id.problemTagRV);
        codingPlatformRv = binding.problemTagRV;
        list = new ArrayList<>();
        map.put("CodeChef", R.drawable.ic_codechef_svgrepo_com);
        map.put("HackerEarth", R.drawable.ic_hackerearth_svgrepo_com);
        map.put("HackerRank", R.drawable.ic_hackerrank_svgrepo_com);
        map.put("CodeForces", R.drawable.ic_codeforces_svgrepo_com);
        map.put("LeetCode", R.drawable.ic_leetcode_svgrepo_com);
        map.put("AtCoder", R.drawable.ic_codechef_svgrepo_com);
        map.put("CodeForces::Gym", R.drawable.ic_codeforces_svgrepo_com);
        map.put("TopCoder", R.drawable.ic_topcoder_svgrepo_com);
        map.put("CS Academy", R.drawable.ic_codeforces_svgrepo_com);
        map.put("Kick Start", R.drawable.ic_kick_start);
        map.put("Toph", R.drawable.ic_codeforces_svgrepo_com);



        // adding conding contest image
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String k = entry.getKey();
            Integer v = entry.getValue();
            System.out.println("key: " + k + ", value: " + v);
            list.add(new CodingPlatformModel(v,k));
        }

        // setting platform clicked

//        list.add(new CodingPlatformModel(R.drawable.ic_codechef_svgrepo_com));
//        list.add(new CodingPlatformModel(R.drawable.ic_hackerearth_svgrepo_com));
//        list.add(new CodingPlatformModel(R.drawable.ic_codeforces_svgrepo_com));
//        list.add(new CodingPlatformModel(R.drawable.ic_hackerrank_svgrepo_com));
//        list.add(new CodingPlatformModel(R.drawable.ic_leetcode_svgrepo_com));
//        list.add(new CodingPlatformModel(R.drawable.ic_codeforces_svgrepo_com));
//        list.add(new CodingPlatformModel(R.drawable.ic_leetcode_svgrepo_com));
//        list.add(new CodingPlatformModel(R.drawable.ic_codeforces_svgrepo_com));
//        list.add(new CodingPlatformModel(R.drawable.ic_people));





        codingPlatformAdapter = new CodingPlatformAdapter(list, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        codingPlatformRv.setLayoutManager(linearLayoutManager);
        codingPlatformRv.setNestedScrollingEnabled(false);
        codingPlatformRv.setAdapter(codingPlatformAdapter);





//        System.out.println("CHECKING  :  ");
//        selectedContestList = codingPlatformAdapter.getArrayListSELECTED();
//
//
//        for(int z=0; z<selectedContestList.size();z++){
//            System.out.println("MINE : " +selectedContestList.get(z));
//        }
//        System.out.println("CHECKING  : DONE  ");







//        codingPlatformRv.findViewById(R.id.codingPlatformImage).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Toast.makeText(getContext(), "AMIT GO ON", Toast.LENGTH_SHORT).show();
//
//
//
//
//
//
//
////                if (!selectedPlatformSet.contains(codingPlatformRv.findViewById(R.id.codingPlatformName))) {
////                    selectedPlatformSet.add(holder.codingPlatformName.getText().toString());
////                    holder.codingPlatformImage.setImageResource(R.drawable.pogo_ic_checked_tick_svgrepo_com);
////                    Toast.makeText(context, "Selected", Toast.LENGTH_SHORT).show();
////                } else {
////                    selectedPlatformSet.remove(holder.codingPlatformName.getText().toString());
////                    selectedPlatformSet.remove(holder.codingPlatformName.getText().toString());
////                    holder.codingPlatformImage.setImageResource(codingPlatformModel.getCodingPlatformImage());
////                    Toast.makeText(context, "Disselected", Toast.LENGTH_SHORT).show();
////                }
////                if (selectedPlatformSet.size() > 0) {
////                    for (int i = 0; i < selectedPlatformSet.size(); i++) {
////                        System.out.println("YOYOYOY : " + selectedPlatformSet);
////
////                    }
////                }
//            }
//        });
//




        // For Top Coder
//        topCoderRv = view.findViewById(R.id.topCoderRV);
        topCoderRv = binding.topCoderRV;
        topcoderList = new ArrayList<>();
        topcoderList.add(new TopCoderModel(R.drawable.p7, "Wasu", "Grand Master", "CodeForces"));
        topcoderList.add(new TopCoderModel(R.drawable.p1, "James", "Master", "CodeChef"));
        topcoderList.add(new TopCoderModel(R.drawable.p2, "Goku", "Candidate", "Hackerrank"));
        topcoderList.add(new TopCoderModel(R.drawable.p3, "Gyan", "Grand Master", "Codechef"));
        topcoderList.add(new TopCoderModel(R.drawable.p4, "Sinchan", "intermediate", "CodeForces"));
        topcoderList.add(new TopCoderModel(R.drawable.p5, "Nobita", "Master", "CodeForces"));
        topcoderList.add(new TopCoderModel(R.drawable.p6, "Raju", "Candidate Master", "CodeForces"));
        topcoderList.add(new TopCoderModel(R.drawable.p2, "Rakesh", "Grand Master", "CodeForces"));

        TopCoderAdapter topCoderAdapter = new TopCoderAdapter(topcoderList, getContext());
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        topCoderRv.setLayoutManager(linearLayoutManager1);
        topCoderRv.setNestedScrollingEnabled(false);
        topCoderRv.setAdapter(topCoderAdapter);


        // For Contest List
//        contestRv = view.findViewById(R.id.contestRv);
        contestRv = binding.contestRv;
        contestList = new ArrayList<>();
//        contestList.add(new ContestModel(R.drawable.ic_codechef_svgrepo_com,R.drawable.ic_baseline_add_alarm_24,"CodeChef","Long Challenge","9:00 PM","11: 00 PM"));
//        contestList.add(new ContestModel(R.drawable.ic_codeforces_svgrepo_com,R.drawable.ic_baseline_add_alarm_24,"CodeForces","Coding Challenge","9:00 PM","11: 00 PM"));
//        contestList.add(new ContestModel(R.drawable.ic_hackerearth_svgrepo_com,R.drawable.ic_baseline_add_alarm_24,"HackerEarth","Starter Pack Challenge","9:00 PM","11: 00 PM"));
//        contestList.add(new ContestModel(R.drawable.ic_hackerrank_svgrepo_com,R.drawable.ic_baseline_add_alarm_24,"HackerRank"," Challenge","9:00 PM","11: 00 PM"));
//        contestList.add(new ContestModel(R.drawable.ic_leetcode_svgrepo_com,R.drawable.ic_baseline_add_alarm_24,"LeetCode","Long Challenge","9:00 PM","11: 00 PM"));
//        contestList.add(new ContestModel(R.drawable.ic_codechef_svgrepo_com,R.drawable.ic_baseline_add_alarm_24,"CodeChef","Long Challenge","9:00 PM","11: 00 PM"));
//        contestList.add(new ContestModel(R.drawable.ic_codeforces_svgrepo_com,R.drawable.ic_baseline_add_alarm_24,"CodeForces","Long Challenge","9:00 PM","11: 00 PM"));

        // for adding contest image
//        map.put("CodeChef", R.drawable.ic_codechef_svgrepo_com);
//        map.put("HackerEarth", R.drawable.ic_hackerearth_svgrepo_com);
//        map.put("HackerRank", R.drawable.ic_hackerrank_svgrepo_com);
//        map.put("CodeForces", R.drawable.ic_codeforces_svgrepo_com);
//        map.put("LeetCode", R.drawable.ic_leetcode_svgrepo_com);
//        map.put("AtCoder", R.drawable.ic_codechef_svgrepo_com);
//        map.put("CodeForces::Gym", R.drawable.ic_codeforces_svgrepo_com);
//        map.put("TopCoder", R.drawable.ic_topcoder_svgrepo_com);
//        map.put("CS Academy", R.drawable.ic_codeforces_svgrepo_com);
//        map.put("Kick Start", R.drawable.ic_kick_start);
//        map.put("Toph", R.drawable.ic_codeforces_svgrepo_com);


        contestAdapter = new ContestAdapter(contestList, getContext());
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        contestRv.setLayoutManager(linearLayoutManager2);
        contestRv.setNestedScrollingEnabled(false);
        contestRv.setAdapter(contestAdapter);

        return binding.getRoot();
    }


    public void loadContest() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getContext());
//        String url = "https://codeforces.com/api/contest.list";
//        String url1 = "https://competitive-coding-api.herokuapp.com/api/codechef/the_amit";


//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
//                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            JSONArray contestJsonArray = response.getJSONArray("result");
//                            System.out.println("success");
//
//                            for (int i = 0; i < contestJsonArray.length(); i++) {
//                                JSONObject contestJsonObject = contestJsonArray.getJSONObject(i);
//
//                                ContestModel contestModel = new ContestModel(
//
//                                        contestJsonObject.getString("type"),
//                                        contestJsonObject.getString("name"),
//                                        contestJsonObject.getString("startTimeSeconds"),
//                                        contestJsonObject.getString("durationSeconds")
//                                );
//
//                                // Converting Second to Date and time
//                                DateTimeUtils.setTimeZone("UTC");
//                                Date sdate = DateTimeUtils.formatDate(Long.parseLong(contestModel.getStartDate()), DateTimeUnits.SECONDS);
//                                String strDate = String.valueOf(sdate);
//                                String syear = strDate.substring(strDate.length() - 4);
//                                System.out.println("Year : " + syear);
//                                int year = Integer.parseInt(syear);
//
//                                // setting end Date
//                                Date edate = DateTimeUtils.formatDate(Long.parseLong(contestModel.getEndDate()), DateTimeUnits.SECONDS);
//                                String eDate = String.valueOf(edate);
//
//                                contestModel.setStartDate(strDate);
//                                contestModel.setEndDate(eDate);
//                                if (year >= 2021) {
//                                    contestList.add(contestModel);
//                                }
//
//                                System.out.println("Start TIME : " + contestModel.getStartDate());
//                                System.out.println("Contest Titile : " + contestModel.getContestTitle());
//                                System.out.println("Contest DESC : " + contestModel.getContestDescription());
//
//
//                            }
//
//
//                            contestAdapter.updateContest(contestList);
//                            System.out.println("success Amit");
//                        } catch (JSONException e) {
//                            System.out.println("Error Amit");
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // TODO: Handle error
//                        System.out.println("Error Found " + error.getLocalizedMessage());
//
//                    }
//                });

        String contestUrl = "https://www.kontests.net/api/v1/all";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, contestUrl, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                jsonObject.getString("name");
                                jsonObject.getString("url");
                                jsonObject.getString("site");
//                                System.out.println("contest name : " + jsonObject.getString("name"));
//                                System.out.println("contest url : " + jsonObject.getString("url"));
//                                System.out.println("contest site : " + jsonObject.getString("site"));

                                ContestModel contestModelList = new ContestModel(
                                        jsonObject.getString("site"),
                                        jsonObject.getString("name"),
                                        jsonObject.getString("start_time"),
                                        jsonObject.getString("end_time"),
                                        jsonObject.getString("url"),
                                        jsonObject.getString("in_24_hours"),
                                        jsonObject.getString("status")
                                );

                                // setting contest image
                                contestModelList.setPlatformImage(map.get(jsonObject.getString("site")));
                                contestList.add(contestModelList);

//                                selectedContestList = new ArrayList<>();
//

//                                selectedContestList = codingPlatformAdapter.getArrayListSELECTED();
////                                codingPlatformAdapter.updateSelected(selectedContestList);
//
//                                for(int z=0; z<selectedContestList.size();z++){
//                                    System.out.println("MINE : " +selectedContestList.get(z));
//                                }

                            }



//                            codingPlatformAdapter.updateSelected(selectedContestList);

                            contestAdapter.updateContest(contestList);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });


//        MySingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
        MySingleton.getInstance(getContext()).addToRequestQueue(jsonArrayRequest);


    }

}