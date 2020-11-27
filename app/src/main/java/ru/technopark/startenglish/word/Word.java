package ru.technopark.startenglish.word;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class Word {
    @NonNull
    private String word;
    @Nullable
    private String pronunciation;
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

    @Nullable
    public String getPronunciation() {
        return pronunciation;
    }

    @Nullable
    public List<Definition> getDefinitions() {
        return definitions;
    }

    static class Definition {
        private String definition;
        private String example;
        private String type;
        private String image_url;
        private String emoji;

        public Definition(String definition, String example, String type, String image_url, String emoji) {
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

        @Override
        @NonNull
        public String toString() {
            return "\n" +
                    "Definition='" + definition + '\'' +
                    ",\nExample='" + example + '\'' +
                    ",\nType='" + type + '\'';
        }
    }

    @Override
    @NonNull
    public String toString() {
        return "word =' " + word + '\'' +
                ", pronunciation='" + pronunciation + '\'' +
                ", definitions =" + definitions;
    }
}

