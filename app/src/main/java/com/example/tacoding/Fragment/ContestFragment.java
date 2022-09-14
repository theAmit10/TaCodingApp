package com.example.tacoding.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.tacoding.Adapter.ContestAdapter;

import com.example.tacoding.Adapter.IPlatformRVAdapter;
import com.example.tacoding.Adapter.PlatformAdapter;
import com.example.tacoding.Adapter.TopCoderAdapter;
import com.example.tacoding.Api.MySingleton;
import com.example.tacoding.Model.CodingPlatformModel;
import com.example.tacoding.Model.ContestModel;
import com.example.tacoding.Model.TopCoderModel;
import com.example.tacoding.R;
import com.example.tacoding.databinding.FragmentContestBinding;
import com.example.tacoding.tadatabase.Platform;
import com.example.tacoding.tadatabase.PlatformName;
import com.example.tacoding.tadatabase.PlatformViewModel;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class ContestFragment extends Fragment implements IPlatformRVAdapter {

    FragmentContestBinding binding;
    public static PlatformViewModel platformViewModel;



    // for coding platform
    RecyclerView codingPlatformRv;
    ArrayList<CodingPlatformModel> list;
    PlatformAdapter platformAdapter;

    //for top coder
    RecyclerView topCoderRv;
    ArrayList<TopCoderModel> topcoderList;

    //For Contest
    RecyclerView contestRv;
    ArrayList<ContestModel> contestList;
    ContestAdapter contestAdapter;
    public static Map<String, Integer> map = new HashMap<String, Integer>();
    public static Map<String, Integer> sMap = new HashMap<String, Integer>();
    ArrayList<ContestModel> filteredContestList = new ArrayList<>();
    private PlatformName PlatformNameTA;


    public ContestFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toast.makeText(getContext(), "RELOADING", Toast.LENGTH_SHORT).show();

        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    loadContest();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        platformViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(PlatformViewModel.class);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentContestBinding.inflate(inflater, container, false);

        // for coding platform

        // adding non active drawable
        codingPlatformRv = binding.problemTagRV;
        list = new ArrayList<>();
        map.put("CodeChef", R.drawable.ic_codechef_svgrepo_com);
        map.put("HackerEarth", R.drawable.ic_hackerearth_svgrepo_com);
        map.put("HackerRank", R.drawable.ic_hackerrank_svgrepo_com);
        map.put("CodeForces", R.drawable.ic_codeforces_svgrepo_com);
        map.put("LeetCode", R.drawable.ic_leetcode_svgrepo_com);
        map.put("AtCoder", R.drawable.ic_code);
        map.put("CodeForces::Gym", R.drawable.ic_codeforces_svgrepo_com);
        map.put("TopCoder", R.drawable.ic_topcoder_svgrepo_com);
        map.put("CS Academy", R.drawable.ic_csacademy);
        map.put("Kick Start", R.drawable.ic_u_kickstarter);
        map.put("Toph", R.drawable.ic_code);


        // adding active drawable
        sMap.put("CodeChef", R.drawable.s_codechef);
        sMap.put("HackerEarth", R.drawable.s_hackerearth);
        sMap.put("HackerRank", R.drawable.s_hackerrank);
        sMap.put("CodeForces", R.drawable.s_codeforces);
        sMap.put("LeetCode", R.drawable.s_leetcode);
        sMap.put("AtCoder", R.drawable.ta_atcoder);
        sMap.put("CodeForces::Gym", R.drawable.s_codeforces);
        sMap.put("TopCoder", R.drawable.s_topcoder);
        sMap.put("CS Academy", R.drawable.s_csacademy);
        sMap.put("Kick Start", R.drawable.s_kickstarter);
        sMap.put("Toph", R.drawable.ta_toph);


        platformAdapter = new PlatformAdapter(getContext().getApplicationContext(), this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        codingPlatformRv.setLayoutManager(linearLayoutManager);
        codingPlatformRv.setAdapter(platformAdapter);
        codingPlatformRv.setNestedScrollingEnabled(false);

//        #############################################################
        // below code is commedted because the following data has been already added to the local database.
        //       #############################################################

        // adding conding platform to database

//        for (Map.Entry<String, Integer> entry : sMap.entrySet()) {
//            String k = entry.getKey();
//            Integer v = entry.getValue();
//
//            System.out.println("PLATFORM INSERTING");
//            System.out.println("key: " + k + ", value: " + v);
//            Platform platform = new Platform(k,v);
//
//            platformViewModel.insert(platform);
//            System.out.println("PLATFORM INSERTED");
//
//        }

        // checking data in the database and updating adapter
        platformViewModel.getmAllPlatform().observe(
                getViewLifecycleOwner(), platforms -> {
                    System.out.println("CHECKING DATA IN DATABASE");

                    System.out.println(" BEFORE PLATFORM ARRAYLIST : ");
                    for (int i = 0; i < platforms.size(); i++) {
                        System.out.println("DATA NAME : " + platforms.get(i).getPlatformName());
                        System.out.println("DATA IMAGE : " + platforms.get(i).getPlatformImage());
                    }

                    platformAdapter.updateList((ArrayList<Platform>) platforms);

                    System.out.println("CHECKED DATA IN DATABASE");

                }

        );

        platformViewModel.getmAllPlatformName().observeForever(platformNames ->
        {
            System.out.println("#####################SELECTED PLATFORM DATA #####################");
            for (int i = 0; i < platformNames.size(); i++) {
                System.out.println("VAL : " + platformNames.get(i).getNplatformName());
            }
            System.out.println("#####################SELECTED PLATFORM DATA #####################");
        });


        //          For Top Coder
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

        contestRv = binding.contestRv;
        contestList = new ArrayList<>();

        contestAdapter = new ContestAdapter(contestList, getContext());
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        contestRv.setLayoutManager(linearLayoutManager2);
        contestRv.setNestedScrollingEnabled(false);
        contestRv.setAdapter(contestAdapter);

        return binding.getRoot();
    }


    public void loadContest() {

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

                                System.out.println("contest site : " + jsonObject.getString("site"));

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
                                contestModelList.setPlatformImage(sMap.get(jsonObject.getString("site")));
                                contestList.add(contestModelList);


                            }

                            // here filtering the contest via selected platform
                            ExecutorService executorService = Executors.newSingleThreadExecutor();
                            executorService.execute(new Runnable() {
                                @Override
                                public void run() {

                                    System.out.println("NOW FILTERED LIST ONLY ");


                                    Handler handler = new Handler(Looper.getMainLooper());
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {

                                            platformViewModel.getmAllPlatformName().observe(getViewLifecycleOwner(), platformNames -> {

                                                if (platformNames.size() > 0) {
                                                    System.out.println("IF BLOCK SELECTED LENGTH : " + platformNames.size());
                                                    System.out.println("IF BLOCK CONTEST LENGTH : " + contestList.size());
                                                    for (int x = 0; x < platformNames.size(); x++) {
                                                        for (int y = 0; y < contestList.size(); y++) {
                                                            if (contestList.get(y).getContestTitle().equals(platformNames.get(x).getNplatformName())) {
                                                                filteredContestList.add(contestList.get(y));
                                                            }
                                                        }
                                                    }
                                                    System.out.println("NODATA GET");
                                                    contestAdapter.updateContest(filteredContestList);
                                                } else {
                                                    System.out.println("ELSE BLOCK  RUNNING ");
                                                    System.out.println("ELSE BLOCK BEFORE -> LENGTH : " + contestList.size());
                                                    contestAdapter.updateContest(contestList);
                                                    System.out.println("ELSE BLOCK AFTER -> LENGTH : " + contestList.size());
                                                }
                                            });

                                        }
                                    });


                                }
                            });


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

        MySingleton.getInstance(getContext()).addToRequestQueue(jsonArrayRequest);


    }

    @Override
    public void onITemClick(Platform platform) {

        PlatformName platformName = new PlatformName(platform.getPlatformName());
        platformViewModel.insertName(platformName);


        AtomicBoolean check = new AtomicBoolean(false);

        platformViewModel.getmAllPlatformName().observe(getViewLifecycleOwner(),platformNames -> {
            String a = platform.getPlatformName().toLowerCase(Locale.ROOT);

            for (int j = 0; j < platformNames.size(); j++) {
                if (platformNames.get(j).getNplatformName().toLowerCase(Locale.ROOT).equalsIgnoreCase(a)) {
                    check.set(true); //means it exist in db
                    break;
                }
            }
            System.out.println(" SELECTED  : " + a + " EXISTS : " + check);

        });

        System.out.println("###########################################################");
        System.out.println("SELECTED PLATFORM WORKING  : " + platform.getPlatformName());
        System.out.println("###########################################################");



        if (check.get()) {
            System.out.println("GOOOD : " + check);
            platformViewModel.deleteName(platformName);

        } else {
            System.out.println("BAD : " + check);
        }

//        contestAdapter.updateContest(filteredContestList);

        getParentFragmentManager().beginTransaction().detach(ContestFragment.this).commit();
        getParentFragmentManager().beginTransaction().attach(ContestFragment.this).commit();

    }

}