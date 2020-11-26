package ru.technopark.startenglish.words;

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
    private static String MODULENAME = "moduleName";
    private WordAdapter wordAdapter;
    private String moduleName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            moduleName = savedInstanceState.getString(MODULENAME, "default");
        }

        wordAdapter = new WordAdapter(moduleName);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.word_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.word_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(wordAdapter);
        return view;
    }

}
