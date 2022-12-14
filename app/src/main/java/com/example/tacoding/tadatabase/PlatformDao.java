package com.example.tacoding.tadatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PlatformDao {

    //FOR PLATFORM TABLE

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Platform platform);

    @Update
    void update(Platform platform);

    @Query("DELETE FROM platform_table")
    void deleteAll();

    @Query("DELETE FROM platform_name_table")
    void deleteAllNames();

    @Delete
    void delete(Platform platform);


    @Query("SELECT * FROM platform_table ORDER BY platform_name ASC")
    LiveData<List<Platform>> getAllPlatformData();


    // FOR PLATFORMNAME  TABLE

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertName(PlatformName platformName);


    @Delete
    void deleteName(PlatformName platformName);

    @Query("SELECT * FROM platform_name_table ORDER BY platform_name ASC")
    LiveData<List<PlatformName>> getAllPlatformName();





}