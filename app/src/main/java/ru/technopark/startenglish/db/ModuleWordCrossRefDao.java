package ru.technopark.startenglish.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

@Dao
public interface ModuleWordCrossRefDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ModuleWordCrossRef moduleWordCrossRef);
}
