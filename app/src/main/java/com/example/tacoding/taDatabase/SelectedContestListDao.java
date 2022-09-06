//package com.example.tacoding.taDatabase;
//
//import androidx.lifecycle.LiveData;
//import androidx.room.Dao;
//import androidx.room.Delete;
//import androidx.room.Insert;
//import androidx.room.OnConflictStrategy;
//import androidx.room.Query;
//
//import java.util.List;
//
//@Dao
//public interface SelectedContestListDao {
//
//    @Query("SELECT * FROM selected_contest_list ORDER BY contestList ASC")
//    LiveData<List<SelectedContestList>> getAllList();
//
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    void insert(SelectedContestList selectedContestList);
//
//    @Delete()
//    void delete(SelectedContestList selectedContestList);
//
////    @Query("DELETE FROM selected_contest_list")
////    void deleteAll();
//
//
//
//
//
//}
