package com.example.tacoding.taDatabase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "selected_contest_list")
public class SelectedContestList {
    @NonNull
    public String getmSelectedConstest() {
        return mSelectedConstest;
    }

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "contestList")
    private String mSelectedConstest;

    public SelectedContestList(@NonNull String mSelectedConstest) {
        this.mSelectedConstest = mSelectedConstest;
    }

    public String getSelectedContestList(){
        return this.mSelectedConstest;
    }
}



