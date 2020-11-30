package ru.technopark.startenglish;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import ru.technopark.startenglish.menu.AddWordFragment;
import ru.technopark.startenglish.modulesUI.ModulesFragment;
import ru.technopark.startenglish.modulesUI.OnModuleSelectedListener;
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
                // add module fragment
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
}