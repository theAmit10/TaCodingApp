package com.example.tacoding.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tacoding.Adapter.AddUserAdapter;
import com.example.tacoding.Model.AddUserModel;
import com.example.tacoding.R;

import java.util.ArrayList;


public class UserFragment extends Fragment {

    RecyclerView addFriendRV;
    ArrayList<AddUserModel> list;

    public UserFragment() {
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
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        addFriendRV = view.findViewById(R.id.addFriendRV);
        list = new ArrayList<>();
        list.add(new AddUserModel(R.drawable.p3,"Wasu","CodeForces"));
        list.add(new AddUserModel(R.drawable.p2,"Aasu","Codes"));
        list.add(new AddUserModel(R.drawable.p1,"Zasu","Codechef"));
        list.add(new AddUserModel(R.drawable.p4,"Dasu","leetcode"));
        list.add(new AddUserModel(R.drawable.p6,"Kasu","CodeForces"));

        AddUserAdapter addUserAdapter = new AddUserAdapter(list, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        addFriendRV.setLayoutManager(linearLayoutManager);
        addFriendRV.setNestedScrollingEnabled(false);
        addFriendRV.setAdapter(addUserAdapter);

        return view;
    }
}