package ru.technopark.startenglish.wordsUI;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class WordDataSource {
    // private final static WordDataSource ourInstance = new WordDataSource();
    private final int INITIAL_CAPACITY = 100;
    private final List<WordModel> list;

    private WordDataSource(String moduleName) {
        list = new ArrayList<>(INITIAL_CAPACITY);
        list.add(new WordModel("toy"));
        list.add(new WordModel(moduleName + "toy"));
        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            list.add(new WordModel("word " + (i + 1)));
        }
    }

    static WordDataSource getInstance(String moduleName) {
        return new WordDataSource(moduleName);
    }

    public List<WordModel> getList() {
        return list;
    }

    public static class WordModel {
        @NonNull
        private String name;

        public WordModel(@NonNull String name) {
            this.name = name;
        }

        @NonNull
        public String getName() {
            return name;
        }

        public void setName(@NonNull String name) {
            this.name = name;
        }
    }
}
