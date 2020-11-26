package ru.technopark.startenglish;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ru.technopark.startenglish.R;
import ru.technopark.startenglish.modules.ModulesFragment;
import ru.technopark.startenglish.modules.OnModuleSelectedListener;
import ru.technopark.startenglish.words.WordsFragment;

public class MainActivity extends AppCompatActivity implements OnModuleSelectedListener {
    private static String MODULENAME = "moduleName";
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
}