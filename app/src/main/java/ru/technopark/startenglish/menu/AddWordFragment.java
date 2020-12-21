package ru.technopark.startenglish.menu;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import ru.technopark.startenglish.R;
import ru.technopark.startenglish.module.Module;
import ru.technopark.startenglish.module.ModuleViewModel;
import ru.technopark.startenglish.word.Definition;
import ru.technopark.startenglish.word.Word;
import ru.technopark.startenglish.word.WordViewModel;

public class AddWordFragment extends Fragment {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_word, container, false);

        ModuleViewModel mvm = new ViewModelProvider(this).get(ModuleViewModel.class);
        MaterialSpinner spinner = v.findViewById(R.id.modules_spinner);
        mvm.getAllModules();
        mvm.allModules.observe(getViewLifecycleOwner(), modules -> {
            if (modules != null) {
                String[] spinner_words = new String[modules.size()];
                for (int i = 0; i < modules.size(); i++) {
                    spinner_words[i] = modules.get(i).getModuleName();
                }
                spinner.setItems(spinner_words);
            }
        });
        EditText editTextWord = v.findViewById(R.id.edit_text_word);
        EditText editTextDefinition = v.findViewById(R.id.edit_text_definition);
        Button findButton = v.findViewById(R.id.find_word_button);
        Button addButton = v.findViewById(R.id.add_word_button);
        WordViewModel wvm = new ViewModelProvider(this).get(WordViewModel.class);


        findButton.setOnClickListener(v1 -> {
            wvm.getWord(editTextWord.getText().toString());

            wvm.lastWord.observe(getViewLifecycleOwner(), word -> {
                if (word == null) {
                    System.out.println("NULL in observe lambda");
                } else {
                    if (!word.getWord().equals("Empty Word")) {
                        //word.getDefinitions().add(new Definition("toy", "", "", "", ""));
                        String definitions = word.getDefinitions().toString();
                        editTextDefinition.setText(definitions.substring(2, definitions.length() - 1));
                    }
                }
            });
        });

        addButton.setOnClickListener(v12 -> {
            wvm.getWord(editTextWord.getText().toString());
            wvm.lastWord.observe(getViewLifecycleOwner(), word -> {
                mvm.getModule(spinner.getText().toString());
                mvm.lastModule.observe(getViewLifecycleOwner(), module -> wvm.saveWord(module));
            });
        });

        spinner.setArrowColor(Color.BLUE);
        spinner.setTextColor(Color.BLACK);
//        String[] modules = new String[20];
//        for (int i = 0; i < 20; i++) {
//            modules[i] = "module " + i;
//        }
//        spinner.setItems(modules);
        spinner.setOnItemSelectedListener((MaterialSpinner.OnItemSelectedListener<String>)
                (view, position, id, item) -> Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show());
        return v;
    }
}
