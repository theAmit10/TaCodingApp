package com.example.tacoding.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.tacoding.Adapter.ContestAdapter;
import com.example.tacoding.Adapter.ProfileAdapter;
import com.example.tacoding.Api.MySingleton;
import com.example.tacoding.Model.CodingPlatformModel;
import com.example.tacoding.Model.ContestModel;
import com.example.tacoding.Model.ProfileModel;
import com.example.tacoding.Model.AddUserModel;
import com.example.tacoding.R;
import com.example.tacoding.databinding.FragmentContestBinding;
import com.example.tacoding.databinding.FragmentProfileBinding;
import com.github.thunder413.datetimeutils.DateTimeStyle;
import com.github.thunder413.datetimeutils.DateTimeUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class ProfileFragment extends Fragment {

    ArrayList<ProfileModel> arrayList = new ArrayList<>();
    FragmentProfileBinding binding;
    String photo ="",fullName="amit",rank="",rating="",maxRating="",lastOnline="", handle="";


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

        binding  = FragmentProfileBinding.inflate(inflater, container, false);

        binding.profileSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("ENtered text : "+binding.profileUserName.getText());
                String abc = "";
                loadProfile(String.valueOf(binding.profileUserName.getText()));
                binding.profileUserName.setText(abc);
            }
        });

        String names =  "DmitriyH";
        loadProfile(names);
        return binding.getRoot();
    }

    private void loadProfile(String names) {
        String profileUrl = "https://codeforces.com/api/user.info?handles="+names;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, profileUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArrayResult = response.getJSONArray("result");
                    JSONObject jsonObject = jsonArrayResult.getJSONObject(0);
                    jsonObject.getString("titlePhoto");
                    jsonObject.getString("firstName");
                    jsonObject.getString("lastName");
                    jsonObject.getString("rating");
                    jsonObject.getString("rank");
                    jsonObject.getString("maxRating");
                    jsonObject.getString("handle");
                    jsonObject.getString("lastOnlineTimeSeconds");


                    String name = jsonObject.getString("firstName") + " " + jsonObject.getString("lastName");


                    binding.setName.setText(name);
                    binding.setRating.setText(jsonObject.getString("rating"));
                    binding.setRank.setText(jsonObject.getString("rank"));
                    binding.setMaxRating.setText(jsonObject.getString("maxRating"));
                    binding.setHandler.setText(jsonObject.getString("handle"));
                    binding.setLastOnline.setText(jsonObject.getString("lastOnlineTimeSeconds"));





                    ProfileModel profileModel = new ProfileModel(
                            jsonObject.getString("titlePhoto"),
                            fullName,
                            jsonObject.getString("rating"),
                            jsonObject.getString("rank"),
                            jsonObject.getString("maxRating"),
                            jsonObject.getString("handle"),
                            jsonObject.getString("lastOnlineTimeSeconds")
                    );


                    System.out.println("firstname: " + jsonObject.getString("firstName"));
                    System.out.println("firstname: " + jsonObject.getString("lastName"));
                    System.out.println("firstname: " + jsonObject.getString("handle"));
                    System.out.println("firstname: " + jsonObject.getString("titlePhoto"));
                    System.out.println("firstname: " + jsonObject.getString("rating"));
                    System.out.println("fullNAME: " + fullName);

                    arrayList.add(profileModel);


                } catch (Exception e) {
                    System.out.println("TRY CATCH ERROR");
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                System.out.println("Error happened ");
                Toast.makeText(getContext(), "NO USER FOUND : "+binding.profileUserName.getText(), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });



        MySingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);


    }


}