package ru.technopark.startenglish.word;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Definition {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private long definitionsWordId;
    private String definition;
    private String example;
    private String type;
    private String image_url;
    private String emoji;

    public Definition(String definition, String example, String type,
                      String image_url, String emoji) {
        this.definition = definition;
        this.example = example;
        this.type = type;
        this.image_url = image_url;
        this.emoji = emoji;
    }

    public String getDefinition() {
        return definition;
    }

    public String getExample() {
        return example;
    }

    public String getType() {
        return type;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

    public long getWordId() {
        return definitionsWordId;
    }

    public void setWordId(int wordId) {
        this.definitionsWordId = wordId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDefinitionsWordId() {
        return definitionsWordId;
    }

    public void setDefinitionsWordId(long definitionsWordId) {
        this.definitionsWordId = definitionsWordId;
    }

    @Override
    @NonNull
    public String toString() {
        return "\n" +
                "Definition='" + definition + '\'' +
                ",\nExample='" + example + '\'' +
                ",\nType='" + type + '\'';
    }
}
