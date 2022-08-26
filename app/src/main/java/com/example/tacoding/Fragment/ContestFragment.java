package com.example.tacoding.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tacoding.Adapter.CodingPlatformAdapter;
import com.example.tacoding.Model.CodingPlatformModel;
import com.example.tacoding.R;

import java.util.ArrayList;

public class ContestFragment extends Fragment {

    RecyclerView codingPlatformRv;
    ArrayList<CodingPlatformModel> list;

    public ContestFragment() {
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
        View view =  inflater.inflate(R.layout.fragment_contest, container, false);
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

        codingPlatformRv.setNestedScrollingEnabled(false);

        codingPlatformRv.setLayoutManager(linearLayoutManager);
        codingPlatformRv.setAdapter(codingPlatformAdapter);

        return view;
    }
}