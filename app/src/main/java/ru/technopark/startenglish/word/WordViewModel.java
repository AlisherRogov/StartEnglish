package ru.technopark.startenglish.word;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import ru.technopark.startenglish.db.WordWithDefinition;
import ru.technopark.startenglish.module.Module;


public class WordViewModel extends AndroidViewModel {
    public static final String ERROR_SYMBOLS_ENTERED = "Error symbols entered";
    private final WordRepo wordRepo = new WordRepo(getApplication());
    public LiveData<Word> lastWord;
    public LiveData<String> soundUrl;

    public LiveData<List<WordWithDefinition>> words = wordRepo.words;

    public WordViewModel(@NonNull Application application) {
        super(application);
        lastWord = wordRepo.word;
        soundUrl = wordRepo.sound;
    }

    public void getWord(String word) {
        String last = lastWord.getValue().getWord();
        if (TextUtils.isEmpty(word) || !word.matches("[a-zA-Z]+")) {
            Toast.makeText(getApplication(), ERROR_SYMBOLS_ENTERED, Toast.LENGTH_SHORT).show();
        } else if (last.equals(word)) {
            Log.w("WordViewModel", "Ignoring duplicate request with word");
        } else {
            Log.w("WordViewModel", "Requesting word");
            lastWord = wordRepo.findWord(word);
            soundUrl = wordRepo.getWordSound(word);
        }
    }

    public void saveWord(Module moduleName) {
        wordRepo.saveWordToLocalDb(moduleName);
    }

}
