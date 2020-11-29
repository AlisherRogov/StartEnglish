package ru.technopark.startenglish.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import ru.technopark.startenglish.word.Word;

@Dao
public interface WordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Word word);

    @Transaction
    @Query("SELECT * FROM word_table WHERE word LIKE :search")
    LiveData<WordWithDefinition> getWord(String search);


    @Transaction
    @Query("SELECT * FROM word_table")
    LiveData<List<WordWithDefinition>> getAllWords();

    @Query("SELECT * FROM word_table WHERE word == :search")
    boolean isWordExist(String search);
}