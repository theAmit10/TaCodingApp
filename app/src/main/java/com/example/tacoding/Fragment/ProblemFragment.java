package com.example.tacoding.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tacoding.Adapter.CodingPlatformAdapter;
import com.example.tacoding.Adapter.ProblemAdapter;
import com.example.tacoding.Model.CodingPlatformModel;
import com.example.tacoding.Model.ProblemModel;
import com.example.tacoding.R;

import java.util.ArrayList;

public class ProblemFragment extends Fragment {

    // for coding platform
    RecyclerView codingPlatformRv;
    ArrayList<CodingPlatformModel> list;

    // FOR PROBLEM RV
    RecyclerView problemRV;
    ArrayList<ProblemModel> problemList;

    public ProblemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_problem, container, false);

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

        // FOR PROBLEM RECYCLER VIEW
        problemRV = view.findViewById(R.id.problemRV);
        problemList = new ArrayList<>();
        problemList.add(new ProblemModel(R.drawable.ic_codechef_svgrepo_com,"Long Coding Challenge","XOR Swap "));
        problemList.add(new ProblemModel(R.drawable.ic_codeforces_svgrepo_com,"Short Coding Challenge","Swap "));
        problemList.add(new ProblemModel(R.drawable.ic_hackerrank_svgrepo_com,"Coding Challenge","n-queen Problem "));
        problemList.add(new ProblemModel(R.drawable.ic_hackerearth_svgrepo_com,"Starter Challenge","kadeane problem"));
        problemList.add(new ProblemModel(R.drawable.ic_codechef_svgrepo_com,"Long Coding Challenge","XOR Swap "));
        problemList.add(new ProblemModel(R.drawable.ic_leetcode_svgrepo_com,"Coding Challenge","bit manipulation"));
        problemList.add(new ProblemModel(R.drawable.ic_codechef_svgrepo_com,"Long Coding Challenge","gready Problems"));

        ProblemAdapter problemAdapter = new ProblemAdapter(problemList, getContext());
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        problemRV.setLayoutManager(linearLayoutManager1);
        problemRV.setNestedScrollingEnabled(false);
        problemRV.setAdapter(problemAdapter);



        return view;
    }
}