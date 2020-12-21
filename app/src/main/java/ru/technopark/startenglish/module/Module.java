package ru.technopark.startenglish.module;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

import ru.technopark.startenglish.word.Word;

@Entity(tableName = "module_of_words")
public class Module {
    @PrimaryKey(autoGenerate = true)
    private long moduleId;
    @NonNull
    private String moduleName;
    @Ignore
    @Nullable
    private List<Word> words;

    public Module(@NonNull String moduleName, @Nullable List<Word> words) {
        this.moduleName = moduleName;
        this.words = words;
    }

    public Module() {
        this.moduleName = "Empty module";
        this.words = null;
    }

    @NonNull
    public String getModuleName() {
        return moduleName;
    }
    public void setModuleName(@NonNull String moduleName) {
        this.moduleName = moduleName;
    }

    @Nullable
    public List<Word> getWords() {
        return words;
    }

    public void setWords(@Nullable List<Word> words) {
        this.words = words;
    }

    public long getModuleId() {
        return moduleId;
    }

    public void setModuleId(long moduleId) {
        this.moduleId = moduleId;
    }
}


