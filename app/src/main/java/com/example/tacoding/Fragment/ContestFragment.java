package com.example.tacoding.Fragment;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
import com.github.thunder413.datetimeutils.DateTimeStyle;
import com.github.thunder413.datetimeutils.DateTimeUtils;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.github.ybq.android.spinkit.style.FadingCircle;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ContestFragment extends Fragment implements IPlatformRVAdapter {

    FragmentContestBinding binding;
    public static PlatformViewModel platformViewModel;

    // Filtering Contest
    ArrayList<String> filterWord = new ArrayList<>();
    SearchView searchView;
    String currentString = "";
    private ImageView allButton, codeChef, codeForces, codeForcesGym,
            hackerEarth, hackerRank, leetCode, atCoder, topCoder, csAcademy,
            kickStart, toph;

    public static Map<String, Integer> filterMap = new HashMap<String, Integer>();
    public static Map<String, Integer> selMap = new HashMap<String, Integer>();



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
    ProgressBar progressBar;
    SwipeRefreshLayout swipeToRefresh;


    public ContestFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        filterWord.add("all");

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
//        codingPlatformRv = binding.problemTagRV;
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

        // for filtermap

        filterMap.put("allFilter", R.drawable.ic_profile);
        filterMap.put("codeChef", R.drawable.ic_codechef_svgrepo_com);
        filterMap.put("hackerEarth", R.drawable.ic_hackerearth_svgrepo_com);
        filterMap.put("hackerRank", R.drawable.ic_hackerrank_svgrepo_com);
        filterMap.put("codeForces", R.drawable.ic_codeforces_svgrepo_com);
        filterMap.put("leetCode", R.drawable.ic_leetcode_svgrepo_com);
        filterMap.put("atCoder", R.drawable.ic_code);
        filterMap.put("codeForcesGym", R.drawable.ic_codeforces_svgrepo_com);
        filterMap.put("topCoder", R.drawable.ic_topcoder_svgrepo_com);
        filterMap.put("csAcademy", R.drawable.ic_csacademy);
        filterMap.put("kickStart", R.drawable.ic_u_kickstarter);
        filterMap.put("toph", R.drawable.ic_code);

        filterMap.put("allFilter", R.drawable.ic_contest);
        selMap.put("codeChef", R.drawable.s_codechef);
        selMap.put("hackerEarth", R.drawable.s_hackerearth);
        selMap.put("hackerRank", R.drawable.s_hackerrank);
        selMap.put("codeForces", R.drawable.s_codeforces);
        selMap.put("leetCode", R.drawable.s_leetcode);
        selMap.put("atCoder", R.drawable.ta_atcoder);
        selMap.put("codeForcesGym", R.drawable.s_codeforces);
        selMap.put("topCoder", R.drawable.s_topcoder);
        selMap.put("csAcademy", R.drawable.s_csacademy);
        selMap.put("kickStart", R.drawable.s_kickstarter);
        selMap.put("toph", R.drawable.ta_toph);


        // initing imageView
        allButton = binding.allFilter;
        codeChef = binding.codeChef;
        codeForces = binding.codeForces;
        codeForcesGym = binding.codeForcesGym;
        hackerEarth = binding.hackerEarth;
        hackerRank = binding.hackerRank;
        leetCode = binding.leetCode;
        atCoder = binding.atCoder;
        topCoder = binding.topCoder;
        csAcademy = binding.csAcademy;
        kickStart = binding.kickStart;
        toph = binding.Toph;




//        SwipeRefreshLayout swipeRefreshLayout = binding.swipeToRefresh;
//        swipeRefreshLayout.setOnRefreshListener(() -> {
//
//            getFragmentManager().beginTransaction().detach(ContestFragment.this).attach(ContestFragment.this).commit();
//
//            Log.d(TAG, "onCreateView: RELOADING CONTEST FRAGMENT");
//            System.out.println("RELOADED Fragment");
//
//
//
//            swipeRefreshLayout.setRefreshing(false);
//        });








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
//        platformViewModel.getmAllPlatform().observe(
//                getViewLifecycleOwner(), platforms -> {
//                    System.out.println("CHECKING DATA IN DATABASE");
//
//                    System.out.println(" BEFORE PLATFORM ARRAYLIST : ");
//                    for (int i = 0; i < platforms.size(); i++) {
//                        System.out.println("DATA NAME : " + platforms.get(i).getPlatformName());
//                        System.out.println("DATA IMAGE : " + platforms.get(i).getPlatformImage());
//                    }
//
//                    platformAdapter.updateList((ArrayList<Platform>) platforms);
//
//                    System.out.println("CHECKED DATA IN DATABASE");
//
//                }
//
//        );

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

//        TopCoderAdapter topCoderAdapter = new TopCoderAdapter(topcoderList, getContext());
//        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
//        topCoderRv.setLayoutManager(linearLayoutManager1);
//        topCoderRv.setNestedScrollingEnabled(false);
//        topCoderRv.setAdapter(topCoderAdapter);


        // For Contest List

        contestRv = binding.contestRv;
        contestList = new ArrayList<>();

        contestAdapter = new ContestAdapter(contestList, getContext());
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        contestRv.setLayoutManager(linearLayoutManager2);
        contestRv.setNestedScrollingEnabled(false);
        contestRv.setAdapter(contestAdapter);

        initSearchWidget();


        binding.allFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allFilterTapped(view);
            }
        });

        binding.codeChef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codeChef(view);
            }
        });

        binding.codeForces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codeForces(view);
            }
        });

        binding.codeForcesGym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codeForcesGym(view);
            }
        });

        binding.leetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leetCode(view);

            }
        });

        binding.atCoder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atCoder(view);
            }
        });

        binding.csAcademy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                csAcademy(view);
            }
        });

        binding.hackerEarth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hackerEarth(view);
            }
        });

        binding.hackerRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hackerRank(view);
            }
        });

        binding.Toph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toph(view);
            }
        });

        binding.kickStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kickStart(view);
            }
        });

        binding.topCoder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topCoder(view);
            }
        });

        progressBar = binding.spinKit;
        Sprite doubleBounce = new DoubleBounce();
        Sprite fadingCircle = new FadingCircle();
        progressBar.setIndeterminateDrawable(fadingCircle);


        swipeToRefresh = binding.swipeToRefresh;
        swipeToRefresh.setOnRefreshListener(() -> {

            Toast.makeText(getContext(), "KYA BAAT", Toast.LENGTH_SHORT).show();
            getParentFragmentManager().beginTransaction().detach(ContestFragment.this).attach(ContestFragment.this).commit();
            System.out.println("REFRESHED");

            swipeToRefresh.setRefreshing(false);
        });


        return binding.getRoot();
    }


    public void loadContest() {
        String contestUrl = "https://www.kontests.net/api/v1/all";
        System.out.println("goa");
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

                                progressBar.setVisibility(View.VISIBLE);
                                // setting contest image
                                contestModelList.setPlatformImage(sMap.get(jsonObject.getString("site")));



                                System.out.println("Start Time : "+contestModelList.getStartDate());
                                Date date = DateTimeUtils.formatDate(contestModelList.getStartDate());
                                Date endDate = DateTimeUtils.formatDate(contestModelList.getEndDate());
                                System.out.println("Start Edit Time : "+date);
                                System.out.println("Start format Time : "+DateTimeUtils.formatWithStyle(date,DateTimeStyle.FULL));


                                contestModelList.setStartDate(""+date);
                                contestModelList.setEndDate(""+endDate);
//
//                                DateTimeUtils.formatWithStyle("2017-06-13", DateTimeStyle.FULL); // Tuesday, June 13, 2017
//                                DateTimeUtils.formatWithStyle("2017-06-13", DateTimeStyle.LONG); // June 13, 2017
//                                DateTimeUtils.formatWithStyle("2017-06-13", DateTimeStyle.MEDIUM); // Jun 13, 2017
//                                DateTimeUtils.formatWithStyle("2017-06-13", DateTimeStyle.SHORT); // 06/13/17

                                System.out.println("End Time : "+contestModelList.getEndDate());
                                contestList.add(contestModelList);
                            }


                            ContestAdapter con = new ContestAdapter(contestList,getContext());
                            progressBar.setVisibility(View.GONE);
                            contestRv.setAdapter(con);

//                            contestAdapter.updateContest(contestList);
                            System.out.println("BLOCK -> LENGTH : " + contestList.size());


                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressBar.setVisibility(View.VISIBLE);
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        progressBar.setVisibility(View.VISIBLE);

                    }
                });

        MySingleton.getInstance(getContext()).addToRequestQueue(jsonArrayRequest);


    }

    @Override
    public void onITemClick(Platform platform) {




    }

    private void lookSelected(ImageView parsedImage, String val) {
        for (Map.Entry<String, Integer> entry : filterMap.entrySet()) {
            String k = entry.getKey();
            Integer v = entry.getValue();
            System.out.println("key: " + k + ", value: " + v);
            if (k.toLowerCase().equalsIgnoreCase(val.toLowerCase())) {
                parsedImage.setImageResource(v);
            }
        }
    }

    private void lookUnSelected(ImageView parsedImage, String val) {
        for (Map.Entry<String, Integer> entry : selMap.entrySet()) {
            String k = entry.getKey();
            Integer v = entry.getValue();
            System.out.println("key: " + k + ", value: " + v);
            if (k.toLowerCase().equalsIgnoreCase(val.toLowerCase())) {
                parsedImage.setImageResource(v);
            }
        }
    }


    private void unSelectedAllFilterButton() {
        lookUnSelected(allButton, "allFilter");
        lookUnSelected(codeChef, "codeChef");
        lookUnSelected(codeForces, "codeForces");
        lookUnSelected(hackerEarth, "hackerEarth");
        lookUnSelected(hackerRank, "hackerRank");
        lookUnSelected(leetCode, "leetCode");
        lookUnSelected(atCoder, "atCoder");
        lookUnSelected(codeForcesGym, "codeForcesGym");
        lookUnSelected(topCoder, "topCoder");
        lookUnSelected(csAcademy, "csAcademy");
        lookUnSelected(kickStart, "kickStart");
        lookUnSelected(toph, "toph");
    }

    public void initSearchWidget() {
        searchView = binding.contestSearchView;
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint(" Platform Name ");
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;

            }

            @Override
            public boolean onQueryTextChange(String newText) {

                currentString = newText;

                System.out.println("ENTERED TEXT : " + newText);

                ArrayList<ContestModel> contestModels = new ArrayList<>();


                for (ContestModel contestModel : contestList) {
                    if (contestModel.getContestTitle().toLowerCase().contains(newText.toLowerCase())) {
                        if (filterWord.contains("all")) {
                            contestModels.add(contestModel);
                        } else {
                            for (String filter : filterWord) {
                                if (contestModel.getContestTitle().toLowerCase().contains(filter)) {
                                    contestModels.add(contestModel);
                                }
                            }
                        }
                    }
                }

                ContestAdapter contestAdapter3 = new ContestAdapter(contestModels, getContext());
                contestRv.setAdapter(contestAdapter3);
                contestAdapter3.notifyDataSetChanged();

                return false;
            }
        });
    }


    public void filterList(String status) {

        if (status != null && !filterWord.contains(status))
            filterWord.add(status);

        System.out.println("ENTERED TEXT FILTERED  : " + status);

        ArrayList<ContestModel> contestModels = new ArrayList<>();


        for (ContestModel contestModel : contestList) {
            for (String filter : filterWord) {
                System.out.println("LIST DATA : " + filter);
                if (contestModel.getContestTitle().toLowerCase().contains(filter.toLowerCase())) {
                    if (currentString == "") {
                        System.out.println("currentString  TA : " + contestModel.getContestTitle());
                        contestModels.add(contestModel);
                    } else {
                        if (contestModel.getContestTitle().toLowerCase().contains(currentString.toLowerCase())) {
                            contestModels.add(contestModel);
                        }
                    }
                }

            }
        }


        ContestAdapter contestAdapter1 = new ContestAdapter(contestModels, getContext());
        contestRv.setAdapter(contestAdapter1);
        contestAdapter1.notifyDataSetChanged();
    }


    public void allFilterTapped(View view) {
        filterWord.clear();
        filterWord.add("all");
        searchView.setQuery("", false);
        searchView.clearFocus();

        unSelectedAllFilterButton();
        lookSelected(allButton, "allFilter");

        ContestAdapter contestAdapter2 = new ContestAdapter(contestList, getContext());
        contestRv.setAdapter(contestAdapter2);
    }

    public void codeChef(View view) {
        filterWord.remove("all");
        filterList("codeChef");
        lookSelected(codeChef, "codeChef");
        lookUnSelected(allButton, "allFilter");
        Toast.makeText(getContext(), "CODECHEF", Toast.LENGTH_SHORT).show();

    }

    public void hackerEarth(View view) {
        filterWord.remove("all");
        filterList("hackerEarth");
        lookSelected(hackerEarth, "hackerEarth");
        lookUnSelected(allButton, "allFilter");
        Toast.makeText(getContext(), "HACKEREARTH", Toast.LENGTH_SHORT).show();
    }

    public void hackerRank(View view) {
        filterWord.remove("all");
        filterList("hackerRank");
        lookSelected(hackerRank, "hackerRank");
        lookUnSelected(allButton, "allFilter");
        Toast.makeText(getContext(), "HACKERRANK", Toast.LENGTH_SHORT).show();
    }

    public void codeForces(View view) {
        filterWord.remove("all");
        filterList("codeForces");
        lookSelected(codeForces, "codeForces");
        lookUnSelected(allButton, "allFilter");
        Toast.makeText(getContext(), "CODEFORCES", Toast.LENGTH_SHORT).show();
    }

    public void leetCode(View view) {
        filterWord.remove("all");
        filterList("leetCode");
        lookSelected(leetCode, "leetCode");
        lookUnSelected(allButton, "allFilter");
        Toast.makeText(getContext(), "LEETCODE", Toast.LENGTH_SHORT).show();
    }

    public void atCoder(View view) {
        filterWord.remove("all");
        filterList("atCoder");
        lookSelected(atCoder, "atCoder");
        lookUnSelected(allButton, "allFilter");
        Toast.makeText(getContext(), "ATCODER", Toast.LENGTH_SHORT).show();
    }

    public void codeForcesGym(View view) {
        filterWord.remove("all");
        filterList("CodeForces::Gym");
        lookSelected(codeForcesGym, "codeForcesGym");
        lookUnSelected(allButton, "allFilter");
        Toast.makeText(getContext(), "CODEFORCESGYM", Toast.LENGTH_SHORT).show();
    }

    public void topCoder(View view) {
        filterWord.remove("all");
        filterList("topCoder");
        lookSelected(topCoder, "topCoder");
        lookUnSelected(allButton, "allFilter");
        Toast.makeText(getContext(), "TOPCODER", Toast.LENGTH_SHORT).show();
    }

    public void csAcademy(View view) {
        filterWord.remove("all");
        filterList("cs Academy");
        lookSelected(csAcademy, "csAcademy");
        lookUnSelected(allButton, "allFilter");
        Toast.makeText(getContext(), "CSACADEMY", Toast.LENGTH_SHORT).show();
    }

    public void kickStart(View view) {
        filterWord.remove("all");
        filterList("kick start");
        lookSelected(kickStart, "kickStart");
        lookUnSelected(allButton, "allFilter");
        Toast.makeText(getContext(), "KICKSTART", Toast.LENGTH_SHORT).show();
    }

    public void Toph(View view) {
        filterWord.remove("all");
        filterList("toph");
        lookSelected(toph, "toph");
        lookUnSelected(allButton, "allFilter");
        Toast.makeText(getContext(), "TOPH", Toast.LENGTH_SHORT).show();
    }
}