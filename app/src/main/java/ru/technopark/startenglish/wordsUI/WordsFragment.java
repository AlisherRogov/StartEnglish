package ru.technopark.startenglish.wordsUI;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.technopark.startenglish.R;

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
        // System.out.println(moduleName);

        wordAdapter = new WordAdapter(moduleName);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.word_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.word_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(wordAdapter);
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
