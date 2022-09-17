package com.example.tacoding.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tacoding.Adapter.ProfileAdapter;
import com.example.tacoding.Model.CodingPlatformModel;
import com.example.tacoding.Model.ProfileModel;
import com.example.tacoding.Model.AddUserModel;
import com.example.tacoding.R;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    // for coding platform
    RecyclerView codingPlatformRv;
    ArrayList<CodingPlatformModel> list;

    // for profile platform
    RecyclerView profileProgressRV;
    ArrayList<ProfileModel> profileList;

    // for profileUserDetailsModel
    ArrayList<AddUserModel> addUserModelArrayList;



    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_profile, container, false);

        // for coding platform
        codingPlatformRv = view.findViewById(R.id.problemTagRV);
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

//        CodingPlatformAdapter codingPlatformAdapter = new CodingPlatformAdapter(list,getContext());
//        ProfileAdapter codingPlatformAdapter = new ProfileAdapter(list, getContext());
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
//        codingPlatformRv.setLayoutManager(linearLayoutManager);
//        codingPlatformRv.setNestedScrollingEnabled(false);
//        codingPlatformRv.setAdapter(codingPlatformAdapter);


        //for profile
        profileProgressRV = view.findViewById(R.id.profileProgressRV);
        profileList = new ArrayList<>();
        profileList.add(new ProfileModel(R.drawable.ic_codechef_svgrepo_com,"Codechef","200","1850"));
        profileList.add(new ProfileModel(R.drawable.ic_codeforces_svgrepo_com,"Codeforces","260","1800"));
        profileList.add(new ProfileModel(R.drawable.ic_leetcode_svgrepo_com,"leetcode","10","1900"));
        profileList.add(new ProfileModel(R.drawable.ic_hackerearth_svgrepo_com,"hackerearth","20","2000"));
        profileList.add(new ProfileModel(R.drawable.ic_hackerrank_svgrepo_com,"hackerrank","300","1800"));
        profileList.add(new ProfileModel(R.drawable.ic_leetcode_svgrepo_com,"Leetcode","20","1850"));
        profileList.add(new ProfileModel(R.drawable.ic_codechef_svgrepo_com,"Codechef","100","180"));
        profileList.add(new ProfileModel(R.drawable.ic_codeforces_svgrepo_com,"Codeforces","200","1800"));
        profileList.add(new ProfileModel(R.drawable.ic_codechef_svgrepo_com,"Codechef","2","1850"));

        ProfileAdapter profileAdapter = new ProfileAdapter(profileList,getContext());
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        profileProgressRV.setLayoutManager(linearLayoutManager1);
        profileProgressRV.setNestedScrollingEnabled(false);
        profileProgressRV.setAdapter(profileAdapter);




        return view;
    }
}