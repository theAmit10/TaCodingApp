package com.example.tacoding.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.tacoding.Api.MySingleton;
import com.example.tacoding.Model.ProfileModel;
import com.example.tacoding.R;
import com.example.tacoding.databinding.FragmentProfileBinding;
import com.example.tacoding.tadatabase.Platform;
import com.example.tacoding.tadatabase.PlatformName;
import com.example.tacoding.tadatabase.PlatformViewModel;
import com.github.marlonlom.utilities.timeago.TimeAgo;
import com.github.thunder413.datetimeutils.DateTimeUnits;
import com.github.thunder413.datetimeutils.DateTimeUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class ProfileFragment extends Fragment {

    ArrayList<ProfileModel> arrayList = new ArrayList<>();
    FragmentProfileBinding binding;
    public PlatformViewModel platformViewModel;
    String photo ="",fullName="",rank="",rating="",maxRating="", handle="" , firstName="", lastName="",lastOnlineTimeSeconds= "", name = "";


    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        platformViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(PlatformViewModel.class);


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding  = FragmentProfileBinding.inflate(inflater, container, false);

        binding.profileSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Entered text : "+binding.profileUserName.getText());
                String abc = "";
                PlatformName platformName = new PlatformName(String.valueOf(binding.profileUserName.getText()));
                if(!platformName.getNplatformName().equals("")){
                    platformViewModel.insertName(platformName);
                }
                loadProfile(String.valueOf(binding.profileUserName.getText()));
                binding.profileUserName.setText(abc);
            }
        });



//        String names =  "DmitriyH";
//        Editable intialNames =  binding.profileUserName.getText();



        platformViewModel.getmAllPlatformName().observeForever(platformNames ->
        {
            System.out.println("#####################SELECTED PLATFORM DATA #####################");
            for (int i = 0; i < platformNames.size(); i++) {
                System.out.println("VAL : " + platformNames.get(i).getNplatformName());
            }
            System.out.println("#####################SELECTED PLATFORM DATA #####################");
            System.out.println("SIZE : "+platformNames.size());

            if(platformNames.size()!=0){
                loadProfile(platformNames.get(platformNames.size()-1 ).getNplatformName());
                System.out.println("LAST ELEMENT : "+platformNames.get(platformNames.size() - 1).getNplatformName());
            }
        });

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

                    photo = jsonObject.getString("titlePhoto");
                    handle = jsonObject.getString("handle");
                    lastOnlineTimeSeconds = jsonObject.getString("lastOnlineTimeSeconds");

                    Date date = DateTimeUtils.formatDate(Long.parseLong(lastOnlineTimeSeconds), DateTimeUnits.SECONDS);

                    String dateString = date+"";





                    if(jsonObject.has("firstName")){
                        firstName = jsonObject.getString("firstName");
                    }else {
                        firstName = "No";
                    }

                    if(jsonObject.has("lastName")){
                        lastName = jsonObject.getString("lastName");
                    }else {
                        lastName = "Name";
                    }

                    if(jsonObject.has("rating")){
                        rating = jsonObject.getString("rating");
                    }else {
                        rating = "unrated";
                    }

                    if(jsonObject.has("maxRating")){
                        maxRating= jsonObject.getString("maxRating");
                    }else {
                        maxRating = "unrated";
                    }

                    if(jsonObject.has("rank")){
                        rank= jsonObject.getString("rank");
                    }else {
                        rank = "No Rank";
                    }



                    name = firstName + " " + lastName;


                    binding.setName.setText(name);
                    binding.setRating.setText(rating);
                    binding.setRank.setText(rank);
                    binding.setMaxRating.setText(maxRating);
                    binding.setHandler.setText(handle);
                    binding.setLastOnline.setText(dateString);

                    Picasso.get().load(jsonObject.getString("titlePhoto"))
                            .placeholder(R.drawable.ic_profile)
                            .into(binding.profileUserImage);

                    System.out.println("fullNAME: " + fullName);

                } catch (Exception e) {
                    System.out.println("TRY CATCH ERROR");
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                System.out.println("Error happened ");
                if(getContext() != null){
                    if(binding.profileUserName.getText().equals("")){
                        Toast.makeText(getContext(), "ENTER USER NAME  "+binding.profileUserName.getText(), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getContext(), "NO USER FOUND : "+binding.profileUserName.getText(), Toast.LENGTH_SHORT).show();
                    }
                }
                error.printStackTrace();
            }
        });

        MySingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);


    }


}