package ru.technopark.startenglish.wordsUI;

import java.util.ArrayList;
import java.util.List;

import ru.technopark.startenglish.word.Definition;

public class WordCardDataSource {
    private final List<Definition> list;

    private WordCardDataSource(List<Definition> definitions) {
        list = new ArrayList<>(definitions.size());
//        System.out.println(definitions);
        list.addAll(definitions);
    }

    static WordCardDataSource getInstance(List<Definition> definitions) {
        return new WordCardDataSource(definitions);
    }

    public List<Definition> getList() {
        return list;
    }
}
