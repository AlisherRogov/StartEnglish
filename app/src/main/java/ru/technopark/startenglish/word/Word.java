package ru.technopark.startenglish.word;
import java.util.List;

public class Word {
    private String word;
    private String pronunciation;
    private List<Definition> definitions;

    public Word(String word, String pronunciation, List<Definition> definitions) {
        this.word = word;
        this.pronunciation = pronunciation;
        this.definitions = definitions;
    }

    public Word() {
        this.word = "Empty Word";
        this.pronunciation = "";
        this.definitions = null;
    }

    public String getWord() {
        return word;
    }

    public String getPronunciation() {
        return pronunciation;
    }

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
        public String toString() {
            return "\n" +
                    "Definition='" + definition + '\'' +
                    ",\nExample='" + example + '\'' +
                    ",\nType='" + type + '\'';
        }
    }

    @Override
    public String toString() {
        return "word =' " + word + '\'' +
                ", pronunciation='" + pronunciation + '\'' +
                ", definitions =" + definitions;
    }
}

