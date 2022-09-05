package com.example.tacoding.taDatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class SelectedContestListViewModel extends AndroidViewModel {

    private SelectedContestListRepository selectedContestListRepository;
    private final LiveData<List<SelectedContestList>> allSelectedContestList;

    public SelectedContestListViewModel(Application application) {
        super(application);
        selectedContestListRepository = new SelectedContestListRepository(application);
        allSelectedContestList = selectedContestListRepository.getSelectedContestList();
    }


    public LiveData<List<SelectedContestList>> getSelectedContestList() {
        return allSelectedContestList;
    }

    public void  insert(SelectedContestList selectedContestList){
        selectedContestListRepository.insert(selectedContestList);
    }

    public void delete(SelectedContestList selectedContestList){
        selectedContestListRepository.delete(selectedContestList);
    }
}
