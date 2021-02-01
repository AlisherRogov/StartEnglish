package ru.technopark.startenglish;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import ru.technopark.startenglish.menu.AddModuleFragment;
import ru.technopark.startenglish.menu.AddWordFragment;
import ru.technopark.startenglish.module.Module;
import ru.technopark.startenglish.module.ModuleViewModel;
import ru.technopark.startenglish.modulesUI.ModulesFragment;
import ru.technopark.startenglish.modulesUI.OnModuleSelectedListener;
import ru.technopark.startenglish.word.Word;
import ru.technopark.startenglish.word.WordViewModel;
import ru.technopark.startenglish.wordsUI.DefinitionAdapter;
import ru.technopark.startenglish.wordsUI.OnWordSelectedListener;
import ru.technopark.startenglish.wordsUI.WordCardFragment;
import ru.technopark.startenglish.wordsUI.WordsFragment;

public class MainActivity extends AppCompatActivity implements OnModuleSelectedListener, OnWordSelectedListener {
    private static String MODULENAME = "moduleName";
    private static String WORDNAME = "wordName";
    public static final String PREFS_NAME = "MyPrefsFile";
    private ModuleViewModel mvm;
    private WordViewModel wordViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mvm = new ViewModelProvider(this).get(ModuleViewModel.class);
        mvm.createModule("first");
        mvm.createModule("second");
        mvm.createModule("third");
        mvm.createModule("fourth");


        wordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
//        mvm.lastModule.observe(this, new Observer<Module>() {
//            @Override
//            public void onChanged(Module module) {
//                if (module.getModuleName().equals("first")) {
//
//                }
//            }
//        });
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        boolean isSaved = settings.getBoolean("isSaved", false);
        if (!isSaved) {
            ArrayList<String> m1 = new ArrayList<>();
            m1.add("apple");
            m1.add("banana");
            m1.add("Apricots");
            m1.add("Avocado");
            m1.add("Cherries");
            m1.add("Feijoa");
            m1.add("Grapefruit");
            m1.add("Grapes");
            m1.add("Lemon");
            m1.add("Mandarin");
            m1.add("Mango");
            m1.add("Orange");
//            m1.add("Papaya");
//            m1.add("Pear");
//            m1.add("Peaches");
//            m1.add("Raspberries");
//            m1.add("Pummelo");
//            m1.add("Strawberries");
//            m1.add("Watermelon");
            predefine(m1, "first");

            ArrayList<String> m2 = new ArrayList<>();
            m2.add("tomato");
            m2.add("potato");
            predefine(m2, "second");

            ArrayList<String> m3 = new ArrayList<>();
            m3.add("hello");
            m3.add("bye");
            predefine(m3, "third");

            ArrayList<String> m4 = new ArrayList<>();
            m4.add("computer");
            m4.add("phone");
            predefine(m4, "fourth");
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("isSaved", true);
            editor.apply();
        }

        if (getSupportFragmentManager().findFragmentById(R.id.fragment_container) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, new ModulesFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        System.out.println(item.getItemId());

        switch (item.getItemId()) {
            case R.id.action_add_word:
                onAddWordSelected();
                return true;
            case R.id.action_add_module:
                onAddModuleSelected();
                return true;
            case R.id.action_settings:
                // setting fragment
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onModuleSelected(String moduleName) {
        WordsFragment wordsFragment = new WordsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(MODULENAME, moduleName);
        wordsFragment.setArguments(bundle);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, wordsFragment)
                .addToBackStack(WordsFragment.class.getSimpleName())
                .commit();
    }

    @Override
    public void onWordSelected(String wordName) {
        WordCardFragment wordCardFragment = new WordCardFragment();

        Bundle bundle = new Bundle();
        bundle.putString(WORDNAME, wordName);
        wordCardFragment.setArguments(bundle);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, wordCardFragment)
                .addToBackStack(WordCardFragment.class.getSimpleName())
                .commit();
    }

    private void onAddWordSelected() {
        AddWordFragment addWordFragment = new AddWordFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, addWordFragment)
                .addToBackStack(AddWordFragment.class.getSimpleName())
                .commit();
    }

    private void onAddModuleSelected() {
        AddModuleFragment addModuleFragment = new AddModuleFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, addModuleFragment)
                .addToBackStack(AddWordFragment.class.getSimpleName())
                .commit();
    }

    private void predefine(ArrayList<String> words, String moduleName) {
        for (String word : words) {
            wordViewModel.getWord(word);
        }
        wordViewModel.lastWord.observe(this, new Observer<Word>() {
            @Override
            public void onChanged(Word word) {
                if (words.contains(word.getWord())) {
                    wordViewModel.saveWord(moduleName);
                    words.remove(word.getWord());
                } else {
                    if (words.isEmpty()) {
                        wordViewModel.lastWord.removeObserver(this);
                    }
                }
            }
        });
    }
}