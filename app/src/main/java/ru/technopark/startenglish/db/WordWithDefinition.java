package ru.technopark.startenglish.db;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import ru.technopark.startenglish.word.Definition;
import ru.technopark.startenglish.word.Word;

public class WordWithDefinition {
    @Embedded
    private Word word;
    @Relation(
            parentColumn = "wordId",
            entityColumn = "definitionsWordId"
    )
    private List<Definition> definitions;

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public List<Definition> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(List<Definition> definitions) {
        this.definitions = definitions;
    }
}
