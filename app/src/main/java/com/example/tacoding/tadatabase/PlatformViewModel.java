package com.example.tacoding.tadatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PlatformViewModel extends AndroidViewModel {

    private PlatformRepository mRepository;

    private final LiveData<List<Platform>> mAllPlatform;
    private final LiveData<List<PlatformName>> mAllPlatformName;

    public PlatformViewModel(Application application) {
        super(application);
        mRepository = new PlatformRepository(application);
        mAllPlatform = mRepository.getmAllPlatform();
        mAllPlatformName = mRepository.getmAllPlatformName();
    }

    public LiveData<List<Platform>> getmAllPlatform() {return mAllPlatform;}

    public void insert(Platform platform ){mRepository.insert(platform);}

    public void delete(Platform platform) {mRepository.delete(platform);}


    // FOR PLATFORM NAME

    public  LiveData<List<PlatformName>> getmAllPlatformName() {return mAllPlatformName;}
    public void insertName(PlatformName platformName){mRepository.insertName(platformName);}
    public void deleteName(PlatformName platformName){mRepository.deleteName(platformName);}

}
