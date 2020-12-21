package ru.technopark.startenglish.module;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.ArrayList;
import java.util.List;

import ru.technopark.startenglish.db.AppDatabase;
import ru.technopark.startenglish.db.ModuleDao;
import ru.technopark.startenglish.db.ModuleWithWords;

public class ModuleRepo {
    private final Context context;
    private final ModuleDao moduleDao;
    MutableLiveData<Module> lastModule = new MutableLiveData<>();
    MutableLiveData<List<Module>> allModules = new MutableLiveData<>();

    ModuleRepo(Context context) {
        this.context = context;
        AppDatabase db = AppDatabase.getDatabase(context);
        moduleDao = db.moduleDao();
        lastModule.setValue(new Module());
        allModules.setValue(new ArrayList<>());
    }

    LiveData<Module> getModule(String moduleName) {
        LiveData<ModuleWithWords> moduleWithWordsLiveData = moduleDao.getModuleWithWords(moduleName);
        moduleWithWordsLiveData.observeForever(moduleWithWords -> {
            if (moduleWithWords != null) {
                moduleWithWords.getModule().setWords(moduleWithWords.getWords());
                lastModule.setValue(moduleWithWords.getModule());
                Log.d("myDb", "From local db Module");
            } else {
                Toast.makeText(context, "Module not found", Toast.LENGTH_SHORT).show();
            }
//                moduleWithWordsLiveData.removeObserver(this);
        });
        return lastModule;
    }

    LiveData<List<Module>> getAllModules() {
        LiveData<List<Module>> allModulesWithWords = moduleDao.getAllModulesWithWords();
        allModulesWithWords.observeForever(moduleWithWords -> {
            if (moduleWithWords != null) {
                allModules.setValue(moduleWithWords);
                Log.d("ali", moduleWithWords.toString());
            } else {
                Toast.makeText(context, "No module not found", Toast.LENGTH_SHORT).show();
            }
        });
        return allModules;
    }

    void createModule(String moduleName) {
        Module module = new Module(moduleName, new ArrayList<>());
        AppDatabase.databaseWriteExecutor.execute(() -> {
            if (!moduleDao.isModuleExist(moduleName.toLowerCase())) {
                moduleDao.insert(module);
            }
        });
    }
}
