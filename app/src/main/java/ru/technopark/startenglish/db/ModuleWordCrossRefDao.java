package ru.technopark.startenglish.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

@Dao
public interface ModuleWordCrossRefDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ModuleWordCrossRef moduleWordCrossRef);

    @Transaction
    @Query("SELECT COUNT(*) FROM module_word_cross_ref WHERE moduleId LIKE :search")
    long getModuleSize(long search);

}
