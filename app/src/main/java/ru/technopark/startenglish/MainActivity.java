package ru.technopark.startenglish;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

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
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ModuleViewModel mvm = new ViewModelProvider(this).get(ModuleViewModel.class);
        mvm.createModule("first");
        mvm.createModule("second");
        mvm.createModule("third");
        mvm.createModule("fourth");


        WordViewModel wordViewModel = new ViewModelProvider(this).get(WordViewModel.class);

        wordViewModel.getWord("toy");
        mvm.getModule("first");
        wordViewModel.lastWord.observe(MainActivity.this, new Observer<Word>() {
            @Override
            public void onChanged(Word word) {
                if (word.getDefinitions() != null) {
                    wordViewModel.saveWord(mvm.lastModule.getValue());
                }
            }
        });

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
}