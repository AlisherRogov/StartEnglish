package ru.technopark.startenglish.word;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.technopark.startenglish.network.ApiRepo;
import ru.technopark.startenglish.network.DictionaryApi;

public class WordRepo {
    private final String API_ON_FAILURE_RESPONSE = "API not responding";
    private final String API_WORD_NOT_FOUND = "API can not find the word";
    private final String API_ACCESS_TOKEN = "Token 97c1baaec95afd5f61016eff7ec473ecfa29d3c6";
    private final DictionaryApi dictionaryApi;
    private final Context context;
    final MutableLiveData<Word> word = new MutableLiveData<>();
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public WordRepo(Context context) {
        this.context = context;
        dictionaryApi = ApiRepo.from(context).getDictionaryApi();
        word.setValue(new Word());
    }

    public MutableLiveData<Word> findWord(String wordToSearch) {
        request(wordToSearch);
        return word;
    }

    private void request(String requestWord) {
        executorService.execute(() -> dictionaryApi.get(requestWord, API_ACCESS_TOKEN).enqueue(new Callback<DictionaryApi.WordPlain>() {
            @Override
            public void onResponse(@NonNull Call<DictionaryApi.WordPlain> call, @NonNull Response<DictionaryApi.WordPlain> response) {
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

    private static Word transformWord(DictionaryApi.WordPlain plain) {
        Word result = new Word(plain.word, plain.pronunciation, map(plain.definitions));
        Log.d("WordRepo", "Loaded word = " + result.getWord());
        return result;
    }

    private static List<Word.Definition> map(List<DictionaryApi.DefinitionPlain> plains) {
        List<Word.Definition> result = new ArrayList<>();
        for (DictionaryApi.DefinitionPlain definitionPlain : plains) {
            result.add(new Word.Definition(definitionPlain.definition,
                    definitionPlain.example,
                    definitionPlain.type,
                    definitionPlain.image_url,
                    definitionPlain.emoji));
        }
        return result;
    }
}