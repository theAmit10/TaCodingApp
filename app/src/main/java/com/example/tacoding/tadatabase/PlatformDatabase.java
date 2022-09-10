package com.example.tacoding.tadatabase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {Platform.class,PlatformName.class}, version = 2, exportSchema = false)
abstract class PlatformDatabase extends RoomDatabase {

    abstract PlatformDao platformDao();


    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile PlatformDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static PlatformDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PlatformDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    PlatformDatabase.class, "platform_database")
                            .addCallback(sRoomDatabaseCallback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Override the onCreate method to populate the database.
     * For this sample, we clear the database every time it is created.
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                PlatformDao dao = INSTANCE.platformDao();
//                dao.deleteAll();
//
//                Book book = new Book("Hello");
//                dao.insert(book);
//                book = new Book("World");
//                dao.insert(book);
            });
        }
    };
}
