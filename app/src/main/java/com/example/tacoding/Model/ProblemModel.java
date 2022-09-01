package com.example.tacoding.Model;

import org.json.JSONArray;

import java.util.ArrayList;

public class ProblemModel {


    String contestId, index, name;
    String rating ;
    ArrayList<String> tags;


    public ProblemModel(String contestId, String index, String name,String rating, ArrayList<String> tags) {
        this.contestId = contestId;
        this.index = index;
        this.name = name;
        this.rating = rating;
        this.tags = tags;
    }

    public ProblemModel(String contestId, String index, String name, ArrayList<String> tags) {
        this.contestId = contestId;
        this.index = index;
        this.name = name;
        this.tags = tags;



    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public String getContestId() {
        return contestId;
    }

    public void setContestId(String contestId) {
        this.contestId = contestId;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}

