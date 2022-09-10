package com.example.tacoding.tadatabase;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PlatformRepository {

    private PlatformDao mPlatformDao;
    private LiveData<List<Platform>> mAllPlatform;
    private LiveData<List<PlatformName>> mAllPlatformName;

    PlatformRepository(Application application){
        PlatformDatabase db = PlatformDatabase.getDatabase(application);
        mPlatformDao = db.platformDao();
        mAllPlatform = mPlatformDao.getAllPlatformData();
        mAllPlatformName = mPlatformDao.getAllPlatformName();
    }

    LiveData<List<Platform>> getmAllPlatform() {return  mAllPlatform;}

    void insert(Platform platform){
        PlatformDatabase.databaseWriteExecutor.execute(() -> {
            mPlatformDao.insert(platform);
        });
    }

    void update(Platform platform){
        PlatformDatabase.databaseWriteExecutor.execute(() -> {
            mPlatformDao.update(platform);
        });
    }

    void delete(Platform platform){
        PlatformDatabase.databaseWriteExecutor.execute(() -> {
            mPlatformDao.delete(platform);
        });
    }


    // FOR PLATFORM NAME TABLE

    void insertName(PlatformName platformName){
        PlatformDatabase.databaseWriteExecutor.execute(() -> {
            mPlatformDao.insertName(platformName);
        });
    }

    void deleteName(PlatformName platformName){
        PlatformDatabase.databaseWriteExecutor.execute(() -> {
            mPlatformDao.deleteName(platformName);
        });
    }

    LiveData<List<PlatformName>> getmAllPlatformName() {return mAllPlatformName;}
}
