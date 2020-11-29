package ru.technopark.startenglish.word;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.technopark.startenglish.db.AppDatabase;
import ru.technopark.startenglish.db.DefinitionDao;
import ru.technopark.startenglish.db.WordDao;
import ru.technopark.startenglish.db.WordWithDefinition;
import ru.technopark.startenglish.network.ApiRepo;
import ru.technopark.startenglish.network.DictionaryApi;

class WordRepo {
    private final String API_ON_FAILURE_RESPONSE = "API not responding";
    private final String API_WORD_NOT_FOUND = "API can not find the word";
    private final String API_ACCESS_TOKEN = "Token 97c1baaec95afd5f61016eff7ec473ecfa29d3c6";
    private final DictionaryApi dictionaryApi;
    private final WordDao wordDao;
    private final DefinitionDao definitionDao;
    private final Context context;
    final MutableLiveData<Word> word = new MutableLiveData<>();
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    final LiveData<List<WordWithDefinition>> words;

    WordRepo(Context context) {
        this.context = context;
        dictionaryApi = ApiRepo.from(context).getDictionaryApi();
        AppDatabase db = AppDatabase.getDatabase(context);
        wordDao = db.wordDao();
        definitionDao = db.definitionDao();
        word.setValue(new Word());
        words = wordDao.getAllWords();
    }

    LiveData<Word> findWord(String wordToSearch) {
        request(wordToSearch);
        return word;
    }

    private void request(String requestWord) {
        LiveData<WordWithDefinition> wordWithDefinitionLiveData = wordDao.getWord(requestWord);
        wordWithDefinitionLiveData.observeForever(new Observer<WordWithDefinition>() {
            @Override
            public void onChanged(WordWithDefinition res) {
                if (res != null) {
                    res.getWord().setDefinitions(res.getDefinitions());
                    word.setValue(res.getWord());
                    Log.d("myDb", "From local db");
                } else {
                    executorService.execute(() -> dictionaryApi.get(requestWord, API_ACCESS_TOKEN)
                            .enqueue(new Callback<DictionaryApi.WordPlain>() {
                                @Override
                                public void onResponse(@NonNull Call<DictionaryApi.WordPlain> call,
                                                       @NonNull Response<DictionaryApi.WordPlain> response) {
                                    if (response.isSuccessful() && response.body() != null) {
                                        word.postValue(transformWord(response.body()));
                                    } else {
                                        Toast.makeText(context, API_WORD_NOT_FOUND, Toast.LENGTH_SHORT).show();
                                        Log.d("Done2", "nSuccess");
                                    }
                                }

                                @Override
                                public void onFailure(@NonNull Call<DictionaryApi.WordPlain> call, @NonNull Throwable t) {
                                    Toast.makeText(context, API_ON_FAILURE_RESPONSE, Toast.LENGTH_SHORT).show();
                                    Log.d("Done3", "nSuccess");
                                }
                            }));
                }
                wordWithDefinitionLiveData.removeObserver(this);
            }
        });
    }

    private static Word transformWord(DictionaryApi.WordPlain plain) {
        Word result = new Word(plain.word, plain.pronunciation, map(plain.definitions));
        Log.d("WordRepo", "Loaded word = " + result.getWord());
        return result;
    }

    private static List<Definition> map(List<DictionaryApi.DefinitionPlain> plains) {
        List<Definition> result = new ArrayList<>();
        for (DictionaryApi.DefinitionPlain definitionPlain : plains) {
            result.add(new Definition(definitionPlain.definition,
                    definitionPlain.example,
                    definitionPlain.type,
                    definitionPlain.image_url,
                    definitionPlain.emoji));
        }
        return result;
    }

    void saveWordToLocalDb() {
        Word w = word.getValue();
        if (w != null && w.getDefinitions() != null) {
            AppDatabase.databaseWriteExecutor.execute(() -> {
                if (!wordDao.isWordExist(w.getWord())) {
                    long id = wordDao.insert(w);
                    for (Definition def : w.getDefinitions()) {
                        def.setDefinitionsWordId(id);
                        definitionDao.insert(def);
                        Log.d("myDb", "Saved To local DB  " + id);
                    }
                }
            });
        }
    }
}