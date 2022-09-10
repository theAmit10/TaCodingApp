package com.example.tacoding.tadatabase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "platform_name_table")
public class PlatformName {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "platform_name")
    private String nplatformName;

    public PlatformName(@NonNull String nplatformName) {
        this.nplatformName = nplatformName;
    }

    public String getNplatformName() {
        return this.nplatformName;
    }
}
