package ru.technopark.startenglish.network;

import android.content.Context;

import androidx.annotation.NonNull;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import ru.technopark.startenglish.ApplicationModified;

public class ApiRepo {
    private final String BASE_URL = "https://owlbot.info/api/v3/";
    private final String SOUND_URL = "https://dictionaryapi.com/api/v3/references/collegiate/json/";
    private final DictionaryApi dictionaryApi;
    private final SoundApi soundApi;

    public ApiRepo() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        dictionaryApi = retrofit.create(DictionaryApi.class);
        retrofit = new Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl(SOUND_URL)
                .build();
        soundApi = retrofit.create(SoundApi.class);
    }

    @NonNull
    public DictionaryApi getDictionaryApi() {
        return dictionaryApi;
    }

    @NonNull
    public SoundApi getSoundApi() {
        return soundApi;
    }

    @NonNull
    public static ApiRepo from(Context context) {
        return ApplicationModified.from(context).getApi();
    }
}
