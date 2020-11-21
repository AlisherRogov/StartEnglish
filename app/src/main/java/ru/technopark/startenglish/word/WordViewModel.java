package ru.technopark.startenglish.word;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MediatorLiveData;


public class WordViewModel extends AndroidViewModel {
    private final WordRepo wordRepo = new WordRepo(getApplication());
    private final MediatorLiveData<WordState> wordState = new MediatorLiveData<>();
    private Word lastWord = new Word();

    public WordViewModel(@NonNull Application application) {
        super(application);
        wordState.setValue(WordState.NONE);
    }

    public Word getWord(String word) {
       String last = lastWord.getWord();
       if (TextUtils.isEmpty(word) || !word.matches("[a-zA-Z]+")){
            wordState.postValue(WordState.ERROR);
       } else if (last != null && last.equals(word)) {
           Log.w("WordViewModel", "Ignoring duplicate request with word");
       } else if (wordState.getValue() != WordState.IN_PROGRESS){
           requestWord(word);
       }
       return lastWord;
    }

    private void requestWord(String word) {
        wordState.postValue(WordState.IN_PROGRESS);
        wordRepo.findWord(word);
        final Word wordFromApi = WordRepo.word;
        if (wordFromApi != null) {
            lastWord = wordFromApi;
            wordState.postValue(WordState.SUCCESS);
        } else {
            lastWord = null;
            wordState.postValue(WordState.FAILED);
        }
    }

    enum WordState {
        FAILED, ERROR, NONE, IN_PROGRESS, SUCCESS
    }

}
