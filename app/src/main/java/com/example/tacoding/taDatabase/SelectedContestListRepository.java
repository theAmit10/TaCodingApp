package com.example.tacoding.taDatabase;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class SelectedContestListRepository {

    private SelectedContestListDao selectedContestListDao;
    private LiveData<List<SelectedContestList>> selectedContestList;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples

    SelectedContestListRepository(Application application) {
        SelectedContestListDatabase db = SelectedContestListDatabase.getDatabase(application);
        selectedContestListDao = db.selectedContestListDao();
        selectedContestList = selectedContestListDao.getAllList();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.

    LiveData<List<SelectedContestList>> getSelectedContestList() {
        return selectedContestList;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.


    void insert(SelectedContestList selectedContestList){
        SelectedContestListDatabase.databaseWriteExecutor.execute(() -> {
            selectedContestListDao.insert(selectedContestList);
        });
    }

    void delete(SelectedContestList selectedContestList){
        SelectedContestListDatabase.databaseWriteExecutor.execute(()->{
            selectedContestListDao.delete(selectedContestList);
        });
    }
}


