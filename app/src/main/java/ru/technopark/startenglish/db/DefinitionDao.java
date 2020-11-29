package ru.technopark.startenglish.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import ru.technopark.startenglish.word.Definition;

@Dao
public interface DefinitionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Definition definition);
}