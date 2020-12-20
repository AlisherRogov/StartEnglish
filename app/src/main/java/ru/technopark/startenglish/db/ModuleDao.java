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

//    @Transaction
//    @Query("SELECT * FROM module_word_cross_ref WHERE moduleId LIKE :search")
//    LiveData<ModuleWithWords> getWord(long search);

    @Transaction
    @Query("SELECT * FROM module_of_words WHERE moduleName LIKE :search")
    LiveData<ModuleWithWords> getModuleWithWords(String search);

    @Transaction
    @Query("SELECT * FROM module_of_words WHERE moduleName LIKE :search")
    LiveData<Module> getModule(String search);

    @Transaction
    @Query("SELECT * FROM module_of_words")
    public LiveData<List<ModuleWithWords>> getModulesWithWords();

    @Query("SELECT * FROM module_of_words WHERE moduleName == :search")
    boolean isModuleExist(String search);
}
