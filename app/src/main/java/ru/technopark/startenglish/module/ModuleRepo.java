package ru.technopark.startenglish.module;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.ArrayList;

import ru.technopark.startenglish.db.AppDatabase;
import ru.technopark.startenglish.db.ModuleDao;
import ru.technopark.startenglish.db.ModuleWithWords;

public class ModuleRepo {
    private final Context context;
    private final ModuleDao moduleDao;
    MutableLiveData<Module> lastModule = new MutableLiveData<>();

    ModuleRepo(Context context) {
        this.context = context;
        AppDatabase db = AppDatabase.getDatabase(context);
        moduleDao = db.moduleDao();
        lastModule.setValue(new Module("Empty", null));
    }

    LiveData<Module> getModule(String moduleName) {
        LiveData<ModuleWithWords> moduleWithWordsLiveData = moduleDao.getModuleWithWords(moduleName);
        moduleWithWordsLiveData.observeForever(new Observer<ModuleWithWords>() {
            @Override
            public void onChanged(ModuleWithWords moduleWithWords) {
                if (moduleWithWords != null) {
                    moduleWithWords.getModule().setWords(moduleWithWords.getWords());
                    lastModule.setValue(moduleWithWords.getModule());
                    Log.d("myDb", "From local db Module");
                } else {
                    Toast.makeText(context, "Module not found", Toast.LENGTH_SHORT).show();
                }
                moduleWithWordsLiveData.removeObserver(this);
            }
        });
        return lastModule;
    }

    void createModule(String moduleName) {
        Module module = new Module(moduleName, new ArrayList<>());
        AppDatabase.databaseWriteExecutor.execute(() -> {
            if (!moduleDao.isModuleExist(moduleName)) {
                moduleDao.insert(module);
            }
        });
    }
}
