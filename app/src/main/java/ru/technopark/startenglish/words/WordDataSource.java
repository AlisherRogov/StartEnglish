package ru.technopark.startenglish.words;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class WordDataSource {

    private final static WordDataSource ourInstance = new WordDataSource();
    private final int INITIAL_CAPACITY = 100;
    private final List<WordModel> list;

    private WordDataSource() {
        list = new ArrayList<>(INITIAL_CAPACITY);
        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            list.add(new WordModel("word " + (i + 1)));
        }
    }

    static WordDataSource getInstance() {
        return ourInstance;
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
