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
    public long getModuleId() {
        return moduleId;
    }

    public void setModuleId(long moduleId) {
        this.moduleId = moduleId;
    }

    @PrimaryKey(autoGenerate = true)
    private long moduleId;
    @NonNull
    private String moduleName;
    @Ignore
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

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }
}


