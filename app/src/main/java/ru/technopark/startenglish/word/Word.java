package ru.technopark.startenglish.word;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "word_table")
public class Word {
    @PrimaryKey(autoGenerate = true)
    private long wordId;
    @NonNull
    private String word;
    @Nullable
    private String pronunciation;
    @Ignore
    @Nullable
    private List<Definition> definitions;

    public Word(@NonNull String word, @Nullable String pronunciation, @Nullable List<Definition> definitions) {
        this.word = word;
        this.pronunciation = pronunciation;
        this.definitions = definitions;
    }

    public Word() {
        this.word = "Empty Word";
        this.pronunciation = "";
        this.definitions = null;
    }

    @NonNull
    public String getWord() {
        return word;
    }

    public void setWord(@NonNull String word) {
        this.word = word;
    }

    @Nullable
    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(@Nullable String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public long getWordId() {
        return wordId;
    }

    public void setWordId(long wordId) {
        this.wordId = wordId;
    }

    @Nullable
    public List<Definition> getDefinitions() {
        return definitions;
    }
    public void setDefinitions(@Nullable List<Definition> definitions) {
        this.definitions = definitions;
    }

    @Override
    @NonNull
    public String toString() {
        return "word =' " + word + '\'' +
                ", pronunciation='" + pronunciation + '\'' +
                ", definitions =" + definitions;
    }

}

