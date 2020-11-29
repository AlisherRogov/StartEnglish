package ru.technopark.startenglish;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;

import ru.technopark.startenglish.db.AppDatabase;
import ru.technopark.startenglish.network.ApiRepo;

public class ApplicationModified extends Application {
    private ApiRepo apiRepo;

    @Override
    public void onCreate() {
        super.onCreate();
        apiRepo = new ApiRepo();
        AppDatabase.getDatabase(this);
    }

    public ApiRepo getApi() {
        return apiRepo;
    }

    @NonNull
    public static ApplicationModified from(Context context) {
        return (ApplicationModified) context.getApplicationContext();
    }
}
