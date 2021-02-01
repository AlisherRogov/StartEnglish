package ru.technopark.startenglish.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import ru.technopark.startenglish.module.Module;

@Dao
public interface ModuleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Module module);

    @Transaction
    @Query("SELECT * FROM module_of_words WHERE moduleName LIKE :search")
    LiveData<ModuleWithWords> getModuleWithWords(String search);

    @Transaction
    @Query("SELECT moduleId FROM module_of_words WHERE moduleName LIKE :search")
    long getModuleId(String search);

    @Transaction
    @Query("SELECT * FROM module_of_words")
    LiveData<List<Module>> getAllModulesWithWords();

    @Query("SELECT * FROM module_of_words WHERE moduleName == :search")
    boolean isModuleExist(String search);
}
