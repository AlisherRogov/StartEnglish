package ru.technopark.startenglish.module;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ModuleViewModel extends AndroidViewModel {
    private final ModuleRepo moduleRepo = new ModuleRepo(getApplication());
    public LiveData<Module> lastModule;
    public LiveData<List<Module>> allModules;

    public ModuleViewModel(@NonNull Application application) {
        super(application);
        lastModule = moduleRepo.lastModule;
        allModules = moduleRepo.allModules;
    }

    public void getAllModules() {
        allModules = moduleRepo.getAllModules();
    }

    public void getModule(String moduleName) {
        lastModule = moduleRepo.getModule(moduleName);
    }

    public void createModule(String moduleName) {
        moduleRepo.createModule(moduleName);
    }

}
