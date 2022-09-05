package com.example.tacoding.taDatabase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {SelectedContestList.class}, version = 1, exportSchema = false)
public abstract class SelectedContestListDatabase extends RoomDatabase {

    public abstract SelectedContestListDao selectedContestListDao();

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
//            databaseWriteExecutor.execute(() -> {
//                // Populate the database in the background.
//                // If you want to start with more words, just add them.
//                SelectedContestListDao dao = INSTANCE.selectedContestListDao();
////                dao.deleteAll();
//
//                SelectedContestList word = new SelectedContestList("Hello");
//                dao.insert(word);
//                word = new SelectedContestList("World");
//                dao.insert(word);
//            });
        }
    };

    private static volatile SelectedContestListDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static SelectedContestListDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SelectedContestListDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    SelectedContestListDatabase.class, "selected_contest_list_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
