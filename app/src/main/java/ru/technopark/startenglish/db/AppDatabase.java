package ru.technopark.startenglish.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ru.technopark.startenglish.module.Module;
import ru.technopark.startenglish.word.Definition;
import ru.technopark.startenglish.word.Word;

@Database(entities = {Module.class, ModuleWordCrossRef.class, Word.class, Definition.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract WordDao wordDao();

    public abstract ModuleDao moduleDao();

    public abstract ModuleWordCrossRefDao moduleWordCrossRefDao();

    public abstract DefinitionDao definitionDao();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "dictionary_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}