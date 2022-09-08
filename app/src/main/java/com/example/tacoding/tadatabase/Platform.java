package com.example.tacoding.tadatabase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "platform_table")
public class Platform {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "platform_name")
    private String mPlatformName;

    @ColumnInfo(name = "platform_image")
    private int mPlatformImage;

    public Platform(@NonNull String platformName, @NonNull int platformImage){
        this.mPlatformName = platformName;
        this.mPlatformImage = platformImage;
    }

    public String getPlatformName(){return this.mPlatformName;}

    public int getPlatformImage(){return this.mPlatformImage;}

    public void setmPlatformName(@NonNull String mPlatformName) {
        this.mPlatformName = mPlatformName;
    }

    public void setmPlatformImage(int mPlatformImage) {
        this.mPlatformImage = mPlatformImage;
    }
}
