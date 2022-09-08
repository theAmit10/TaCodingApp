package com.example.tacoding.tadatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PlatformViewModel extends AndroidViewModel {

    private PlatformRepository mRepository;

    private final LiveData<List<Platform>> mAllPlatform;

    public PlatformViewModel(Application application) {
        super(application);
        mRepository = new PlatformRepository(application);
        mAllPlatform = mRepository.getmAllPlatform();
    }

    public LiveData<List<Platform>> getmAllPlatform() {return mAllPlatform;}

    public void insert(Platform platform ){mRepository.insert(platform);}

    public void delete(Platform platform) {mRepository.delete(platform);}

}
