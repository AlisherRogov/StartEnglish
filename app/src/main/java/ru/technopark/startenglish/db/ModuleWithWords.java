package ru.technopark.startenglish.db;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

import ru.technopark.startenglish.module.Module;
import ru.technopark.startenglish.word.Word;

public class ModuleWithWords {
    @Embedded
    private Module module;
    @Relation(
            parentColumn = "moduleId",
            entityColumn = "wordId",
            associateBy = @Junction(ModuleWordCrossRef.class)
    )
    private List<Word> words;

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }
}
