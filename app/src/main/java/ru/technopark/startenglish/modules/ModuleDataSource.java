package ru.technopark.startenglish.modules;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class ModuleDataSource {
    private final static ModuleDataSource ourInstance = new ModuleDataSource();
    private final int INITIAL_CAPACITY = 20;
    private final List<ModuleModel> list;

    private ModuleDataSource() {
        list = new ArrayList<>(INITIAL_CAPACITY);
        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            list.add(new ModuleModel("module " + (i + 1), i + 1));
        }
    }

    static ModuleDataSource getInstance() {
        return ourInstance;
    }

    public List<ModuleModel> getList() {
        return list;
    }

    public static class ModuleModel {
        @NonNull
        private String name;
        private int wordCount;

        public ModuleModel(@NonNull String name, int wordCount) {
            this.name = name;
            this.wordCount = wordCount;
        }

        public String getName() {
            return name;
        }

        public int getWordCount() {
            return wordCount;
        }

        public void setName(@NonNull String name) {
            this.name = name;
        }

        public void setWordCount(int wordCount) {
            this.wordCount = wordCount;
        }
    }
}
