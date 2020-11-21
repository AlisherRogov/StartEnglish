package ru.technopark.startenglish.word;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.technopark.startenglish.network.ApiRepo;
import ru.technopark.startenglish.network.DictionaryApi;

public class WordRepo {
    private final DictionaryApi dictionaryApi;
    public static Word word = new Word();   /// todo: Need to check whether word can be LiveData

    public WordRepo(Context context) {
            dictionaryApi = ApiRepo.from(context).getDictionaryApi();
    }

    public Word findWord(String wordToSearch) {
        /// todo: Add search from database
        Log.d("WTF",  word.getWord());
        request(wordToSearch);
        return word;
    }

    private void request(String requestWord) {
        dictionaryApi.get(requestWord, "Token 97c1baaec95afd5f61016eff7ec473ecfa29d3c6").enqueue(new Callback<DictionaryApi.WordPlain>() {
            @Override
            public void onResponse(Call<DictionaryApi.WordPlain> call, Response<DictionaryApi.WordPlain> response) {
                if (response.isSuccessful() && response.body() != null) {
                    word = transformWord(response.body());
                    Log.d("Done", "Success" + word.getWord());
                } else {
                    Log.d("Done", "nSuccess");
                }
            }
            @Override
            public void onFailure(Call<DictionaryApi.WordPlain> call, Throwable t) {
                Log.d("Done", "nSuccess");
            }
        });
    }

    private static Word transformWord(DictionaryApi.WordPlain plain) {
        Word result = new Word(plain.word, plain.pronunciation, map(plain.definitions));
        Log.e("WordRepo", "Loaded word = " + result.getWord());
        return result;
    }

    private static List<Word.Definition> map(List<DictionaryApi.Definition> plains) {
        List<Word.Definition> result = new ArrayList<>();
        for (DictionaryApi.Definition definitionPlain: plains) {
            result.add(new Word.Definition(definitionPlain.definition,
                    definitionPlain.example,
                    definitionPlain.type,
                    definitionPlain.image_url,
                    definitionPlain.emoji));
        }
        return result;
    }
}