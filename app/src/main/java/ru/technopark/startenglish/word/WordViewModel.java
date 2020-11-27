package ru.technopark.startenglish.word;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;


public class WordViewModel extends AndroidViewModel {
    private final WordRepo wordRepo = new WordRepo(getApplication());
    public MutableLiveData<Word> lastWord;

    public WordViewModel(@NonNull Application application) {
        super(application);
        lastWord = wordRepo.word;
    }

    public void getWord(String word) {
        String last = lastWord.getValue().getWord();
        if (TextUtils.isEmpty(word) || !word.matches("[a-zA-Z]+")) {
            lastWord.setValue(null);
        } else if (last.equals(word)) {
            Log.w("WordViewModel", "Ignoring duplicate request with word");
        } else {
            Log.w("WordViewModel", "Requesting word");
            lastWord = wordRepo.findWord(word);
        }
    }
}
