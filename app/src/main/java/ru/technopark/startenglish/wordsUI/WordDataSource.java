package ru.technopark.startenglish.wordsUI;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

import ru.technopark.startenglish.module.Module;
import ru.technopark.startenglish.word.Word;
import ru.technopark.startenglish.word.WordViewModel;

public class WordDataSource {
    // private final static WordDataSource ourInstance = new WordDataSource();
//    private final int INITIAL_CAPACITY = 100;
//    private final List<WordModel> list;
    private final List<Word> list;

    private WordDataSource(List<Word> words) {
        list = words;

/*        list = new ArrayList<>(INITIAL_CAPACITY);
        list.add(new WordModel("toy"));
        list.add(new WordModel("abandon"));
        list.add(new WordModel("ability"));
        list.add(new WordModel("again"));
        list.add(new WordModel("bench"));
        list.add(new WordModel("chairman"));
        list.add(new WordModel("coalition"));
        list.add(new WordModel("condition"));
        list.add(new WordModel("defendant"));
        list.add(new WordModel("develop"));
        list.add(new WordModel("engine"));
        list.add(new WordModel("finish"));
        list.add(new WordModel("gallery"));
        list.add(new WordModel("hole"));
        list.add(new WordModel("inspire"));
        list.add(new WordModel("lawyer"));
        list.add(new WordModel("management"));
        list.add(new WordModel("news"));
        list.add(new WordModel("ongoing"));
        list.add(new WordModel("participate"));
        list.add(new WordModel("political"));
        list.add(new WordModel("recovery"));
        list.add(new WordModel("relatively"));
        list.add(new WordModel("roughly"));
        list.add(new WordModel("selection"));
        list.add(new WordModel("tablespoon"));
        list.add(new WordModel("testify"));
        list.add(new WordModel("urban"));
        list.add(new WordModel("wander"));
        list.add(new WordModel("yield"));
        list.add(new WordModel("zone"));*/
    }


    static WordDataSource getInstance(List<Word> words) {
        return new WordDataSource(words);
    }

    public List<Word> getList() {
        return list;
    }

/*    static WordDataSource getInstance(String moduleName) {
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
    }*/
}
