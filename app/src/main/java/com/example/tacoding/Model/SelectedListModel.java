package com.example.tacoding.Model;

import java.util.ArrayList;

public class SelectedListModel {
    ArrayList<String> selectedContestList;

    public SelectedListModel(ArrayList<String> selectedContestList) {
        this.selectedContestList = selectedContestList;
    }

    public ArrayList<String> getSelectedContestList() {
        return selectedContestList;
    }

    public void setSelectedContestList(ArrayList<String> selectedContestList) {
        this.selectedContestList = selectedContestList;
    }
}
