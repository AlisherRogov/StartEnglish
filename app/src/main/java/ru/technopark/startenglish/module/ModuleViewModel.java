package ru.technopark.startenglish.module;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ModuleViewModel extends AndroidViewModel {
    private final ModuleRepo moduleRepo = new ModuleRepo(getApplication());
    public LiveData<Module> lastModule;

    public ModuleViewModel(@NonNull Application application) {
        super(application);
        lastModule = moduleRepo.lastModule;
    }

    public void getModule(String moduleName) {
        moduleRepo.getModule(moduleName);
    }

    public void createModule(String moduleName) {
        moduleRepo.createModule(moduleName);
    }
}
