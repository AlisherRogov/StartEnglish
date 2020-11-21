package ru.technopark.startenglish;

import android.app.Application;
import android.content.Context;

import ru.technopark.startenglish.network.ApiRepo;

public class ApplicationModified extends Application {
    private ApiRepo apiRepo;

    @Override
    public void onCreate() {
        super.onCreate();
        apiRepo = new ApiRepo();
    }

    public ApiRepo getApi() {
        return apiRepo;
    }

    public static ApplicationModified from(Context context) {
        return (ApplicationModified) context.getApplicationContext();
    }
}
