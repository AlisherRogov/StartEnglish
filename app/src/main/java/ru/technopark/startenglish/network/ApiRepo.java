package ru.technopark.startenglish.network;

import android.content.Context;

import androidx.annotation.NonNull;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import ru.technopark.startenglish.ApplicationModified;

public class ApiRepo {
    private final String BASE_URL = "https://owlbot.info/api/v3/";
    private final DictionaryApi dictionaryApi;

    public ApiRepo() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        dictionaryApi = retrofit.create(DictionaryApi.class);
    }

    @NonNull
    public DictionaryApi getDictionaryApi() {
        return dictionaryApi;
    }

    @NonNull
    public static ApiRepo from(Context context) {
        return ApplicationModified.from(context).getApi();
    }
}
