package ru.technopark.startenglish.wordsUI;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.technopark.startenglish.R;
import ru.technopark.startenglish.module.Module;
import ru.technopark.startenglish.module.ModuleViewModel;
import ru.technopark.startenglish.word.Word;
import ru.technopark.startenglish.word.WordViewModel;

public class WordsFragment extends Fragment {
    private final static String MODULENAME = "moduleName";
    private WordAdapter wordAdapter;
    private String moduleName = "def";
    static OnWordSelectedListener onWordSelectedListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null)
            moduleName = getArguments().getString(MODULENAME, "default");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.word_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.word_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        ModuleViewModel wvm = new ViewModelProvider(this).get(ModuleViewModel.class);
        wvm.getAllModules();
        wvm.getModule(moduleName);

        wvm.lastModule.observe(getViewLifecycleOwner(), new Observer<Module>() {
            @Override
            public void onChanged(Module module) {
                if (module.getWords() != null) {
                    System.out.println(module.getModuleName());
                    System.out.println("Null list in constructor of wordAdapter");

//                    for (Word w : module.getWords()) {
//                        System.out.println(w.getDefinitions().toString());
//                    }

                    wordAdapter = new WordAdapter(module.getWords());
                    recyclerView.setAdapter(wordAdapter);
                }
            }
        });

        recyclerView.setHasFixedSize(true);
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(MODULENAME, moduleName);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            onWordSelectedListener = (OnWordSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnWordSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onWordSelectedListener = null;
    }
}
